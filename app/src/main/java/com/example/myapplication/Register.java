package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity implements View.OnClickListener {
    Button btRegister;
    EditText etName, etUsername,etAge, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etName = (EditText) findViewById(R.id.etName);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etAge = (EditText) findViewById(R.id.etAge);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btRegister = (Button) findViewById(R.id.btnRegister);
        btRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegister:
                String name = etName.getText().toString();
                String Username = etUsername.getText().toString();
                String pasword = etPassword.getText().toString();
                int age = Integer.parseInt(etAge.getText().toString());
                User user = new User(name,Username,pasword,age);
                registerUser(user);
                break;
        }
    }

    private void registerUser(User user) {
        startActivity(new Intent(this,MainActivity.class));
    }
}