package com.example.tokoin.ui.login;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.tokoin.R;
import com.example.tokoin.SolidityReview;
import com.example.tokoin.TokoinCreated;

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

        Button loginButton = findViewById(R.id.mint);
        Switch ethMode = findViewById(R.id.switch1);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ethMode.isChecked()){//ETH
                    Intent i = new Intent(LoginActivity.this, SolidityReview.class);
                    Bundle bundle = new Bundle();
                    Spinner spinner_opt = findViewById(R.id.spinner_option);
                    String opt = spinner_opt.getSelectedItem().toString();

                    startActivity(i);
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
