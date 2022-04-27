package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//view.OnClickListener allows clicks to make actions
public class Login extends AppCompatActivity implements View.OnClickListener {
    Button btLogin;
    EditText etUsername,etPassword;
    TextView registerLink;
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btLogin = (Button)findViewById(R.id.btLogin);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        registerLink = (TextView) findViewById(R.id.tvRegisterLink);

        btLogin.setOnClickListener(this);
        registerLink.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);
    }

    //when a button gets clicks this method gets invoked
    @Override
    public void onClick(View view) {
        //this switch statement gets the id of the view which is clicked
        switch (view.getId()){
            case R.id.btLogin:
                String username= etUsername.getText().toString();
                String password= etPassword.getText().toString();
                User user = new User(username,password);
                authenticate(user);
                userLocalStore.storeUserData(user);
                userLocalStore.setUserLoggedIn(true);
                loggedIn(user);
                break;
            case R.id.tvRegisterLink:
                startActivity(new Intent(this,Register.class));
                break;


        }

    }

    private void loggedIn(User user) {
        startActivity(new Intent(this,MainActivity.class));
    }

    private void authenticate(User user) {
        //
    }
}