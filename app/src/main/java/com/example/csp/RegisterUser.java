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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class RegisterUser extends AppCompatActivity {

     //TextView banner, registerUser;
    EditText fullNames, phoneNumber, passwordReg, emailReg;
    ProgressBar progressBar;
    TextView mLoginBtn;
    FirebaseAuth mAuth;
    Button signUpBtn;

    public RegisterUser() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();
//        banner = findViewById(R.id.banner);
//        banner.setOnClickListener(this);
//
//        registerUser = findViewById(R.id.signUp);
//        registerUser.setOnClickListener(this);

        fullNames = findViewById(R.id.fullName);
        phoneNumber = findViewById(R.id.phoneNumber);
        emailReg = findViewById(R.id.emailRegister);
        passwordReg = findViewById(R.id.passwordRegister);
        //reg button
        signUpBtn = findViewById(R.id.signUp);
        //login textview
        mLoginBtn = findViewById(R.id.login_two);

        progressBar = findViewById(R.id.progressBar2);

        if (mAuth.getCurrentUser()!= null){
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            finish();

        }

        //on click listener for register button
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //operations performed inside on click listener
                String fullName = fullNames.getText().toString().trim();
                String email = emailReg.getText().toString().trim();
                String phone = phoneNumber.getText().toString().trim();
                String password = passwordReg.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    emailReg.setError("Email is Required");
                    return;

                }
                if (TextUtils.isEmpty(password)) {
                    passwordReg.setError("Password is required");
                    return;
                }
                if (password.length() < 6) {
                    passwordReg.setError("Minimum password should be 6 characters");
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    phoneNumber.setError("Phone Number is required");
                    return;
                }
                if (fullName.isEmpty()) {
                    fullNames.setError("Full name is required");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                //Registering the user
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterUser.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        }else {
                            Toast.makeText(RegisterUser.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    public void loginPage(View view) {
//        Intent intent = new Intent(RegisterUser.this, MainActivity.class);
//        startActivity(intent);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
    //MIGHT DELETE
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.banner:
//                startActivity(new Intent(this, MainActivity.class));
//                break;
//            case R.id.registerUser:
//                registerUser();
//        }
//
//    }

//    private void registerUser() {
//        String fullName = fullNames.getText().toString().trim();
//        String email = emailReg.getText().toString().trim();
//        String phone = phoneNumber.getText().toString().trim();
//        String password = passwordReg.getText().toString().trim();
//
//        if (fullName.isEmpty()){
//            fullNames.setError("Full name is required");
//            fullNames.requestFocus();
//            return;
//        }
//        if (email.isEmpty()){
//            emailReg.setError("Email is required");
//            emailReg.requestFocus();
//            return;
//        }
//        if (phone.isEmpty()){
//            phoneNumber.setError("Phone Number is required");
//            phoneNumber.requestFocus();
//            return;
//        }
//        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//           emailReg.setError("Please provide valid email");
//           emailReg.requestFocus();
//           return;
//        }
//        if (password.isEmpty()){
//            passwordReg.setError("Password is required");
//            passwordReg.requestFocus();
//        }
//        if (password.length()<6){
//            passwordReg.setError("Min password should be 6 characters");
//            passwordReg.requestFocus();
//            return;
//        }

//        progressBar.setVisibility(View.VISIBLE);
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
//
//                        if(task.isSuccessful()){
//
//                            User user = new User(fullName, phone, email);
//
//                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull @NotNull Task<Void> task) {
//
//                                    if(task.isSuccessful()){
//                                        Toast.makeText(RegisterUser.this, "User has been registered successfully", Toast.LENGTH_LONG).show();
//                                        progressBar.setVisibility(View.VISIBLE);
//                                    }else{
//                                        Toast.makeText(RegisterUser.this, "Failed to register try again!", Toast.LENGTH_LONG).show();
//                                        progressBar.setVisibility(View.GONE);
//                                    }
//
//                                }
//                            });
//
//
//                        }else{
//                            Toast.makeText(RegisterUser.this, "Failed to register try again!", Toast.LENGTH_LONG).show();
//                            progressBar.setVisibility(View.GONE);
//                        }
//                    }
//                });

    }
