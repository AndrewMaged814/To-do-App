package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private EditText etName, etUsername, etAge, etPassword,etConfirmPassword;
    private UserLocalStore userLocalStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etName = findViewById(R.id.etName);
        etUsername = findViewById(R.id.etUsername);
        etAge = findViewById(R.id.etAge);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        Button btRegister = findViewById(R.id.btnRegister);
        btRegister.setOnClickListener(this);
        userLocalStore = new UserLocalStore(Register.this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnRegister) {
            String name = etName.getText().toString();
            String Username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            String ConfirmedPassword = etConfirmPassword.getText().toString();
            String age = etAge.getText().toString();
            //validate each field that is taken as input
            if (validateCheck(name, Username, password, age,ConfirmedPassword)) {
                //if it validates then register the user as a new user and store their details into the database
                User user = new User(name, Username, password, age);
                registerUser(user);
            }
        }
    }


    private boolean validateCheck(String name, String username, String password, String age,String confirmedPassword) {
        if(name.isEmpty()){
            YoYo.with(Techniques.Bounce).duration(700).repeat(1).playOn(etName);
            etName.setError("full name is required");
            etName.requestFocus();
            return false;
        }
        if(age.isEmpty()){
            YoYo.with(Techniques.Bounce).duration(700).repeat(1).playOn(etAge);
            etAge.setError("Age is required ");
            etAge.requestFocus();
            return false;
        }
        if(username.isEmpty()){
            YoYo.with(Techniques.Bounce).duration(700).repeat(1).playOn(etUsername);
            etUsername.setError("Username is required");
            etUsername.requestFocus();
            return false;
        }
        if(password.isEmpty()){
            YoYo.with(Techniques.Bounce).duration(700).repeat(1).playOn(etPassword);
            etPassword.setError("password is required");
            etPassword.requestFocus();
            return false;
        }
        if(password.length() < 6){
            YoYo.with(Techniques.Bounce).duration(700).repeat(1).playOn(etPassword);
            etPassword.setError("Min password length should be 6 characters");
            etPassword.requestFocus();
            return false;
        }
        if(!password.equals(confirmedPassword)){
            YoYo.with(Techniques.Bounce).duration(700).repeat(1).playOn(etConfirmPassword);
            etConfirmPassword.setError("Password doesn't match");
            etConfirmPassword.requestFocus();
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