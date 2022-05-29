package com.example.startupstockexchange;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class OwnerData extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore DB = FirebaseFirestore.getInstance();
    EditText name,age,aadhar,pan,bankAc,ifsc;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_data);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        aadhar = findViewById(R.id.aadhar);
        pan = findViewById(R.id.Pan);
        bankAc = findViewById(R.id.BankAccount);
        ifsc = findViewById(R.id.ifsc);
        btn = findViewById(R.id.saveButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }
    public void save(){
        Map<String,Object> u = new HashMap<>();
        u.put("Name",name.getText().toString());
        u.put("Age",age.getText().toString());
        u.put("Aadhar", aadhar.getText().toString());
        u.put("Pan",pan.getText().toString());
        u.put("Bank Account",bankAc.getText().toString());
        u.put("IFSC",ifsc.getText().toString());

        DocumentReference user1 = DB.collection("Owners").document(user.getEmail());
        user1.set(u);
        Toast.makeText(OwnerData.this, "Enjoy Tech :} ", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(OwnerData.this,HomeActivity.class);
        startActivity(intent);
    }
}