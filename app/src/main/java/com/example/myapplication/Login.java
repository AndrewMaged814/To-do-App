package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//TODO implement remember me checkbox
//view.OnClickListener allows clicks to make actions
public class Login extends AppCompatActivity implements View.OnClickListener {
    private Button btLogin;
    private EditText etUsername, etPassword;
    private TextView registerLink;
    private UserLocalStore userLocalStore;
    CheckBox cbRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btLogin = findViewById(R.id.btLogin);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        registerLink = findViewById(R.id.tvRegisterLink);
        cbRememberMe = findViewById(R.id.cbRememberme);

        btLogin.setOnClickListener(this);
        registerLink.setOnClickListener(this);
        userLocalStore = new UserLocalStore(Login.this);
    }

    //when a button gets clicks this method gets invoked
    @Override
    public void onClick(View view) {
        //this switch statement gets the id of the view which is clicked
        switch (view.getId()) {
            case R.id.btLogin:
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if(userLocalStore.getLoggedInUser()!=null){
                if (validateLogin(username, password)) {
                    User user = new User(username, password);
                    userLocalStore.setUserLoggedIn(true);
                    authenticate(user);
                }
                }else {
                    Toast.makeText(Login.this, "You need to register first", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tvRegisterLink:
                startActivity(new Intent(this, Register.class));
                break;


        }

    }

    private boolean validateLogin(String username, String password) {
        if (username.isEmpty()) {
            etUsername.setError("Username is required");
            etUsername.requestFocus();
            return false;
        }
        if (password.isEmpty()) {
            etPassword.setError("password is required");
            etPassword.requestFocus();
            return false;
        }
        if (password.length() < 6) {
            etPassword.setError("Min password length should be 6 characters");
            etPassword.requestFocus();
            return false;
        }
        return true;
    }

    private void authenticate(User user) {
        User userRegistered = userLocalStore.getLoggedInUser();

        if (user.username.equals(userRegistered.username) && user.password.equals(userRegistered.password)) {
            Toast.makeText(Login.this, "Logged in successfully!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));
        } else {
            Toast.makeText(Login.this, "Incorrect Username or password", Toast.LENGTH_LONG).show();

        }


    }
}