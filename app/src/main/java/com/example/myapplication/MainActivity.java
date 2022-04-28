package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btLogout;
    private EditText etName, etUsername, etAge;

    private UserLocalStore userLocalStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.etName);
        etUsername = findViewById(R.id.etUsername);
        etAge = findViewById(R.id.etAge);
        btLogout = findViewById(R.id.bntLogout);
        btLogout.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);

    }

    //everytime activity starts it needs to authenticate the user is logged in
    @Override
    protected void onStart() {
        super.onStart();
        if (authenticate() == true) {
            displayUserDetails();
        } else
            startActivity(new Intent(this, Login.class));

    }

    private void displayUserDetails() {
        User user = userLocalStore.getLoggedInUser();
        etUsername.setText(user.username);
        etAge.setText(user.age + "");
        etName.setText(user.name);

    }

    //this will return true if the user is logged in
    private boolean authenticate() {
        return userLocalStore.getUserLoggedIn();

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bntLogout) {
            //when user logs out clear data
            // userLocalStore.logout();
            userLocalStore.setUserLoggedIn(false);
            startActivity(new Intent(this, Login.class));
        }
    }


}