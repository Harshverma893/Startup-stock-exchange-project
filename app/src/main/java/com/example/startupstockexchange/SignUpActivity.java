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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    FirebaseAuth register;
    EditText usernameSignup;
    Button signUp;
    EditText password;
    EditText confirmPassword ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        register = FirebaseAuth.getInstance();
        usernameSignup = findViewById(R.id.usernameSignup);
        signUp = findViewById(R.id.signUpbutton);
        password = findViewById(R.id.password1);
        confirmPassword = findViewById(R.id.confirmPassword);
    }

//    public void verifyEmail(View view){
//        u.sendEmailVerification()
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(SignUpActivity.this, "Email Sent! ", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }
    public void signUpDone(View view) {
        String user = usernameSignup.getText().toString();
        String pass1 = password.getText().toString();
        String pass2 = confirmPassword.getText().toString();
        if(user.isEmpty() || pass1.isEmpty() || pass2.isEmpty()){
            Toast.makeText(this, "Fill the Details!!", Toast.LENGTH_SHORT).show();
        }
        else if(!pass1.equals(pass2)){
            Toast.makeText(this, "Different passwords!!", Toast.LENGTH_SHORT).show();
        }
        else {
            register.createUserWithEmailAndPassword(user,pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
//                        if(u.isEmailVerified()){
                            Toast.makeText(getApplicationContext(),"Registration Successful :)",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                            startActivity(intent);
//                        }
//                        else{
//                            Toast.makeText(getApplicationContext(), "Verify you email!!", Toast.LENGTH_SHORT).show();
//                        }
                    }
                    else{
                        Toast.makeText(SignUpActivity.this, "Registration Failed!!", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}