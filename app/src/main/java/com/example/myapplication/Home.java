package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class Home extends AppCompatActivity implements View.OnClickListener {
    private TextView name;
    private UserLocalStore userLocalStore;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btLogout = findViewById(R.id.bntLogout);
        Button btnToday = findViewById(R.id.btnToday);
        name = findViewById(R.id.tvName);
        btLogout.setOnClickListener(this);
        btnToday.setOnClickListener(this);
        SharedPreferences preferences = getSharedPreferences("userDetails", MODE_PRIVATE);
        userLocalStore = new UserLocalStore(this);

        displayUserDetails();


    }
    private void displayUserDetails() {
        User user = userLocalStore.getLoggedInUser();
        YoYo.with(Techniques.FlipInX).duration(700).playOn(name);
        name.setText("Hello, "+user.name);

    }

    //this will return true if the user is logged in
    private boolean authenticate() {
        return userLocalStore.getUserLoggedIn();

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bntLogout) {
            //if the user logs out set the status of the user to be false
            userLocalStore.setUserLoggedIn(false);
            userLocalStore.logout();
            startActivity(new Intent(this, Login.class));

        }
        else if (view.getId() == R.id.btnToday){
            startActivity(new Intent(this, Today.class));
        }
    }


}