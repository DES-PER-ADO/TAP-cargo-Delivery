package com.example.tokoin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.tokoin.utils.network;
import com.example.tokoin.ui.login.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class SolidityReview extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solidity_review);

        Button confirmDeployButton = findViewById(R.id.confirmDeploy);

        confirmDeployButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){// Deploy smart contract
                new Thread(deploy).start();

//                Intent i = new Intent(SolidityReview.this, LoginActivity.class);
//                startActivity(i);
            }
        });
    }

    Runnable deploy = new Runnable() {
        @Override
        public void run() {
            EditText solEditText = findViewById(R.id.edtInput);
            String data = solEditText.getText().toString();
            String compiled = network.postSolidity(data);
            try {
                JSONObject obj = new JSONObject(compiled);
                String hash = obj.getJSONObject("result").getString("code");
                String res = network.postAbi(hash);
                obj = new JSONObject(res);
                String add = obj.getString("result");
                Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
//                deployContracts.run();
//                Toast.makeText(SolidityReview.this, "Smart Contract Deployed", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(SolidityReview.this, LoginActivity.class);
//                startActivity(i);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}

