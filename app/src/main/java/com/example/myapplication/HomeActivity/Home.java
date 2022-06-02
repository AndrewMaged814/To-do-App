package com.example.myapplication.HomeActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import com.example.myapplication.HomeActivity.Notification.NotificationActivity;
import com.example.myapplication.HomeActivity.Notification.NotifyGenerator;
import com.example.myapplication.R;

import com.example.myapplication.chat.ChatStartUpActivity;
import com.example.myapplication.task.Activities.CreateNewTask;
import com.example.myapplication.task.Activities.DelayedActivity;
import com.example.myapplication.task.Activities.TodayActivity;
import com.example.myapplication.task.Calender.MainActivity;
import com.example.myapplication.user.UserLocalStore;
import com.example.myapplication.user.userTypes.User;


public class Home extends AppCompatActivity implements View.OnClickListener {
    CardView cvToday, cvUpcoming, cvChatRoom, cvDelayed, cvAddTask;
    ImageView userImg;
    private TextView name;
    private UserLocalStore userLocalStore;
    int i =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cvToday = findViewById(R.id.cardToday);
        cvUpcoming = findViewById(R.id.cardUpcoming);
        cvChatRoom = findViewById(R.id.cardChatRoom);
        cvDelayed = findViewById(R.id.cardDelayed);
        name = findViewById(R.id.tvHomename);
        cvAddTask = findViewById(R.id.cardAddtask);
        userImg = findViewById(R.id.img_user);
        cvToday.setOnClickListener(this);
        cvUpcoming.setOnClickListener(this);
        cvDelayed.setOnClickListener(this);
        cvChatRoom.setOnClickListener(this);
        cvAddTask.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);

        displayUserDetails();
       // MotivationalPopups();


    }


    @SuppressLint("SetTextI18n")
    private void displayUserDetails() {
        User user = userLocalStore.getLoggedInUser();
        name.setText("Welcome, " + user.getName());
        if (user.getGender().equals("male"))
            userImg.setImageResource(R.drawable.iconsuser);
        else if (user.getGender().equals("female"))
            userImg.setImageResource(R.drawable.icon_female);
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.icon_logout)
                .setTitle("Confirm logout")
                .setMessage("Are you sure you want logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardToday:
                startActivity(new Intent(this, TodayActivity.class));
                break;
            case R.id.cardUpcoming:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.cardDelayed:
                startActivity(new Intent(this, DelayedActivity.class));
                break;
            case R.id.cardChatRoom:
                startActivity(new Intent(this, ChatStartUpActivity.class));
                break;
            case R.id.cardAddtask:
                startActivity(new Intent(this, CreateNewTask.class));
                break;


        }

    }

}