package com.example.tokoin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tokoin.contracts.DeployContracts;
import com.example.tokoin.ui.login.LoginActivity;
import com.example.tokoin.contracts.Tokoin;


public class SolidityReview extends AppCompatActivity {

    private DeployContracts deployContracts = new DeployContracts();
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
            try {
                deployContracts.run();
                Toast.makeText(SolidityReview.this, "Smart Contract Deployed", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SolidityReview.this, LoginActivity.class);
                startActivity(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}

