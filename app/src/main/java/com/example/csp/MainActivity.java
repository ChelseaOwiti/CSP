package com.example.csp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {


    TextView registerBtn, driverBtn; //route to register page
    EditText editTextEmail, editTextPassword; //email and password
    Button login; //login btn
    ProgressBar progressBar;

    FirebaseAuth mAuth; //firebase


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        login = findViewById(R.id.login);
        registerBtn = findViewById(R.id.register);
        progressBar = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        driverBtn = findViewById(R.id.driver);
        //registerBtn.setOnClickListener(this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    editTextEmail.setError("Email is Required");
                    return;

                }
                if (TextUtils.isEmpty(password)) {
                    editTextPassword.setError("Password is required");
                    return;
                }
                if (password.length() < 6) {
                    editTextPassword.setError("Minimum password should be 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate the user
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        //Determine if login is successful or not
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        } else {
                            Toast.makeText(MainActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterUser.class));
            }
        });

        //route to driver side
        driverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DriverLogin.class));
            }
        });


    }
//route to register
}
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.register:
//                startActivity( new Intent(this, RegisterUser.class));
//                break;
//
//            case R.id.login:
//                userLogin();
//                break;
//        }
//
//    }

//    private void userLogin() {
//        String email = editTextEmail.getText().toString().trim();
//        String password = editTextPassword.getText().toString().trim();
//
//        if(email.isEmpty()){
//            editTextEmail.setError("Email is required");
//            editTextEmail.requestFocus();
//            return;
//        }
//        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            editTextEmail.setError("Please enter a valid email");
//            editTextEmail.requestFocus();
//            return;
//        }
//        if (password.isEmpty()){
//            editTextPassword.setError("Password is required");
//            editTextPassword.requestFocus();
//            return;
//
//        }if (password.length()< 6){
//            editTextPassword.setError("Min password is 6 characters");
//            editTextPassword.requestFocus();
//            return;
//        }
//        progressBar.setVisibility(View.VISIBLE);
//        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
//                if (task.isSuccessful()){
//                    //REDIRECT TO USER LAYOUT
//                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
//
//                }else {
//                    Toast.makeText(MainActivity.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//    }
//route you to register page

