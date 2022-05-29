package com.example.startupstockexchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    RadioButton investor ;
    RadioButton owner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        investor = findViewById(R.id.radioButtonInvestor);
        owner = findViewById(R.id.radioButtonOwner);

    }

    public void proceed(View view){
        if(investor.isChecked() && owner.isChecked()){
            investor.setChecked(false);
            owner.setChecked(false);
            Toast.makeText(this, "Choose only one!!", Toast.LENGTH_SHORT).show();
        }
        else if(!investor.isChecked() && !owner.isChecked()){
            Toast.makeText(this, "Select one!!", Toast.LENGTH_SHORT).show();
        }
        else if(investor.isChecked()){
            Intent intent = new Intent(this,InvestorData.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, OwnerData.class);
            startActivity(intent);
        }
    }
}