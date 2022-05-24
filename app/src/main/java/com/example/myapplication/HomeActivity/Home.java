package com.example.myapplication.HomeActivity;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.myapplication.R;
import com.example.myapplication.task.Activities.CreateNewTask;
import com.example.myapplication.task.Activities.TodayActivity;
import com.example.myapplication.user.Activity.Login;
import com.example.myapplication.user.userTypes.User;
import com.example.myapplication.user.UserLocalStore;

public class Home extends AppCompatActivity implements View.OnClickListener {
    private TextView name;
    private UserLocalStore userLocalStore;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btLogout = findViewById(R.id.bntLogout);
        Button btnToday = findViewById(R.id.btnToday);
        Button btnCreate = findViewById(R.id.btnCreate);
        name = findViewById(R.id.tvName);
        btLogout.setOnClickListener(this);
        btnToday.setOnClickListener(this);
        btnCreate.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);

        displayUserDetails();






    }
    private void displayUserDetails() {
        User user = userLocalStore.getLoggedInUser();
        YoYo.with(Techniques.FlipInX).duration(700).playOn(name);
        name.setText("Hello, "+user.getName());

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bntLogout) {
            //if the user logs out set the status of the user to be false
            userLocalStore.setUserLoggedIn(false);
            startActivity(new Intent(this, Login.class));

        }
        else if (view.getId() == R.id.btnToday){
            startActivity(new Intent(this, TodayActivity.class));
        }
        else if (view.getId() == R.id.btnCreate){
            startActivity(new Intent(this, CreateNewTask.class));
        }
    }


}