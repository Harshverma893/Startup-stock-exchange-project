package com.example.startupstockexchange;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText username ;
    EditText password;
    FirebaseAuth user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        user = FirebaseAuth.getInstance();
    }

    public void goToHomePage(View view){
        String u = username.getText().toString();
        String p = password.getText().toString();

        if(u.isEmpty() || p.isEmpty() ){
            Toast.makeText(this, "Fill The Details!!", Toast.LENGTH_SHORT).show();
        }
        else {
            user.signInWithEmailAndPassword(u,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful() && user.getCurrentUser().isEmailVerified()){
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                    else if( !task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "SignUp Required!!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Email Not Verified!!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void loginToSignup(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}