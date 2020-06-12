package com.example.tokoin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tokoin.ui.login.LoginActivity;

public class SolidityReview extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solidity_review);

        Button confirmDeployButton = findViewById(R.id.confirmDeploy);

        confirmDeployButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){// Deploy smart contract


                Intent i = new Intent(SolidityReview.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
