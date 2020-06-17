package com.example.tokoin.ui.login;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.web3j.crypto.Credentials;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.tokoin.R;
import com.example.tokoin.SolidityReview;
import com.example.tokoin.TokoinCreated;
import com.example.tokoin.contracts.Tokoin;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private Spinner activeTokoinSpinner = null;
    private Spinner optionSpinner = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        //Button buttonLogin = findViewById(R.id.login);

        Button deployButton = findViewById(R.id.deploy);
        deployButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(LoginActivity.this, SolidityReview.class);
                startActivity(i);
            }
        });

        Button confirmButton = findViewById(R.id.mint);
        Switch ethMode = findViewById(R.id.switch1);
        Spinner optSpinner = findViewById(R.id.spinner_option);
        Spinner addressSpinner = findViewById(R.id.spinner_activeTokoin);
        Spinner tidSpinner = findViewById(R.id.spinner_tid);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ethMode.isChecked()){//ETH
                    Web3j web3j = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/fa43c71be4304c2c921ffa47cf288b1f"));
                    Credentials credentials = Credentials.create(addressSpinner.getSelectedItem().toString());
                    ContractGasProvider contractGasProvider = new DefaultGasProvider();
                    Tokoin contract = Tokoin.load("0xefc116eda4114f734a7ca902124b38856dab83be",web3j,credentials,contractGasProvider);
                    String opt = optSpinner.getSelectedItem().toString();
                    if(opt=="Create Tokoin"){
                        EditText holderEditText = findViewById(R.id.editTextHolder);
                        EditText itemIdEditText = findViewById(R.id.editText)
                        contract._mint(holderEditText.getText(),)
                    }else if(opt=="Modify Tokoin"){

                    }else if(opt=="Transfer Tokoin"){

                    }else if(opt=="Delete Tokoin"){

                    }
                }else {//Go
                    Intent i = new Intent(LoginActivity.this, TokoinCreated.class);
                    startActivity(i);
                }
            }
        });

//        activeTokoinSpinner = (Spinner) findViewById(R.id.spinner_activeTokoin);
//        optionSpinner = (Spinner) findViewById(R.id.spinner_option);
//
//        String[] activeTokoinList = {"0x6d1AC11C2C2E74bb66eAA1aA6A512a738557D553"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.activity_list_item, activeTokoinList);
//
//        activeTokoinSpinner.setAdapter(adapter);

//        activeTokoinSpinner.setOnItemSelectedListener(this);
//        optionSpinner.setOnItemSelectedListener(this);
    }
//
//    @Override
//    public void onItemSelected(AdapterView<> parent, View view, int position, long id) {
//        String content = parent.getItemAtPosition(position).toString();
//        switch (parent.getId()) {
//            case R.id.spinner_activeTokoin:
//                Toast.makeText(LoginActivity.this, "Tokoin "+content+" is selected", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.spinner_option:
//                Toast.makeText(LoginActivity.this, "Tokoin "+content+" is selected", Toast.LENGTH_SHORT).show();
//                break;
//            default:
//                break;
//        }
//    }
}
