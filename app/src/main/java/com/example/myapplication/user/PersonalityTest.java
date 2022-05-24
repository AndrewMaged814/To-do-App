package com.example.myapplication.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.example.myapplication.HomeActivity.Home;
import com.example.myapplication.R;

public class PersonalityTest extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_test);

        SharedPreferences preferences = getSharedPreferences("userDetails",MODE_PRIVATE);
        boolean firstStart = preferences.getBoolean("firstStart", true);


        if(firstStart){
            showMainActivity();

        }else
            startActivity(new Intent(this, Home.class));


    }

    private void showMainActivity() {
        //store that the user has already toke the test so he won't take it again when login
        SharedPreferences preferences = getSharedPreferences("userDetails",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("firstStart",false);
        editor.apply();


    }
}