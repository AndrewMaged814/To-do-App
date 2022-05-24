package com.example.myapplication.user.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.myapplication.*;
import com.example.myapplication.user.PersonalityTest;
import com.example.myapplication.user.UserLocalStore;
import com.example.myapplication.user.userTypes.Admin;
import com.example.myapplication.user.userTypes.NormalUser;
import com.example.myapplication.user.userTypes.User;

//view.OnClickListener allows clicks to make actions
public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText etUsername, etPassword;
    private UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btLogin = findViewById(R.id.btLogin);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        TextView registerLink = findViewById(R.id.tvRegisterLink);


        btLogin.setOnClickListener(this);
        registerLink.setOnClickListener(this);
        userLocalStore = new UserLocalStore(Login.this);



    }


    //when a button gets clicks this method gets invoked
    @Override
    public void onClick(View view) {
        //this switch statement gets the id of the view which is clicked
        Admin admin = new Admin();
        switch (view.getId()) {
            case R.id.btLogin:
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if(userLocalStore.getLoggedInUser()!=null){
                if (validateLogin(username, password)) {
                    if(username.equals(admin.getUsername()) && password.equals(admin.getPassword())){
                        startActivity(new Intent(this, AdminPanel.class));
                    }
                    else{
                        User user = new NormalUser(username, password);
                        userLocalStore.setUserLoggedIn(true);
                        authenticate(user);
                    }

                 }
                }
                break;
            case R.id.tvRegisterLink:
                startActivity(new Intent(this, Register.class));
                break;

        }

    }

    private boolean validateLogin(String username, String password) {
        if (username.isEmpty()) {
            YoYo.with(Techniques.Bounce).duration(700).repeat(1).playOn(etUsername);
            etUsername.setError("Username is required");
            etUsername.requestFocus();
            return false;
        }
        if (password.isEmpty()) {
            YoYo.with(Techniques.Bounce).duration(700).repeat(1).playOn(etPassword);
            etPassword.setError("password is required");
            etPassword.requestFocus();
            return false;
        }
        if (password.length() < 5) {
            YoYo.with(Techniques.Bounce).duration(700).repeat(1).playOn(etPassword);
            etPassword.setError("Min password length should be 5 characters");
            etPassword.requestFocus();
            return false;
        }
        return true;
    }

    private void authenticate(User user) {
        User userRegistered = userLocalStore.getLoggedInUser();

        if (user.getUsername().equals(userRegistered.getUsername()) && user.getPassword().equals(userRegistered.getPassword())) {
            Toast.makeText(Login.this, "Logged in successfully!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, PersonalityTest.class));
        } else {
            Toast.makeText(Login.this, "Incorrect Username or password", Toast.LENGTH_LONG).show();

        }


    }
}