package com.example.myapplication.user.Activity;

import android.content.SharedPreferences;
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
import com.example.myapplication.HomeActivity.Home;
import com.example.myapplication.task.Activities.PersonalityTest;
import com.example.myapplication.task.Tasks_Store;
import com.example.myapplication.user.UserLocalStore;
import com.example.myapplication.user.ValidatesLogin;
import com.example.myapplication.user.userTypes.Admin;
import com.example.myapplication.user.userTypes.NormalUser;
import com.example.myapplication.user.userTypes.User;

//view.OnClickListener allows clicks to make actions
public class Login extends AppCompatActivity implements View.OnClickListener, ValidatesLogin {
    private EditText etUsername, etPassword;
    private UserLocalStore userLocalStore;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btLogin = findViewById(R.id.btLogin);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        TextView registerLink = findViewById(R.id.tvRegisterLink);
        preferences = getSharedPreferences("userDetails", MODE_PRIVATE);


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
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                if (validateLogin(username, password)) {
                    if (checkAdminPrivileges(username, password)) {
                        startActivity(new Intent(this, AdminPanel.class));
                    } else {
                        User user = new NormalUser(username, password);
                        if (authenticate(user) && userLocalStore.getUserStatus()) {
                            Toast.makeText(Login.this, "Logged in successfully!", Toast.LENGTH_LONG).show();
                            loadTodaysList();
                            ShowPersonalityTest();

                        }
                        else if(!authenticate(user))
                            Toast.makeText(Login.this, "Incorrect Username or password", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Login.this, "Pending approval...", Toast.LENGTH_LONG).show();


                    }

                }


                break;
            case R.id.tvRegisterLink:
                startActivity(new Intent(this, Register.class));
                break;

        }

    }
    @Override
    public boolean checkAdminPrivileges(String username, String password) {
        boolean isAdmin = username.equals(Admin.getAdminUsername()) && password.equals(Admin.getAdminPassword());
        if (isAdmin) {
            Admin admin = new Admin();
            admin.setAdminprivilege(isAdmin);
        }
        return isAdmin;
    }

    private void ShowPersonalityTest() {
        boolean firstStart = preferences.getBoolean("firstStart", true);

        if (firstStart) {
            startActivity(new Intent(this, PersonalityTest.class));

        } else
            startActivity(new Intent(this, Home.class));

    }

    private void loadTodaysList() {
        Tasks_Store sharedPreferences;
        sharedPreferences = new Tasks_Store(this);
        sharedPreferences.getTask();
    }

    @Override
    public boolean authenticate(User user) {
        User userRegistered = userLocalStore.getLoggedInUser();
        return user.getUsername().equals(userRegistered.getUsername()) && user.getPassword().equals(userRegistered.getPassword());

    }

    @Override
    public boolean validateLogin(String username, String password) {
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
}