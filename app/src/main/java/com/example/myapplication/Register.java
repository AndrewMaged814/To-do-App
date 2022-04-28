package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private Button btRegister;
    private EditText etName, etUsername, etAge, etPassword;
    private UserLocalStore userLocalStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etName = findViewById(R.id.etName);
        etUsername = findViewById(R.id.etUsername);
        etAge = findViewById(R.id.etAge);
        etPassword = findViewById(R.id.etPassword);
        btRegister = findViewById(R.id.btnRegister);
        btRegister.setOnClickListener(this);
        userLocalStore = new UserLocalStore(Register.this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnRegister) {
            String name = etName.getText().toString();
            String Username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            String age = etAge.getText().toString();
            if (validateCheck(name, Username, password, age)) {
                User user = new User(name, Username, password, age);
                registerUser(user);
            }
        }
    }


    private boolean validateCheck(String name, String username, String password, String age) {
        if(name.isEmpty()){
            etName.setError("full name is required");
            etName.requestFocus();
            return false;
        }
        if(age.isEmpty()){
            etAge.setError("Age is required ");
            etAge.requestFocus();
            return false;
        }
        if(username.isEmpty()){
            etUsername.setError("Username is required");
            etUsername.requestFocus();
            return false;
        }
        if(password.isEmpty()){
            etPassword.setError("password is required");
            etPassword.requestFocus();
            return false;
        }
        if(password.length() < 6){
            etPassword.setError("Min password length should be 6 characters");
            etPassword.requestFocus();
            return false;
        }

        return true;
    }

    private void registerUser(User user) {
        userLocalStore.storeUserData(user);
        Toast.makeText(Register.this, "Registered successfully!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, Login.class));
    }
}