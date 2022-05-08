package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class PersonalityTest extends AppCompatActivity {
    private UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_test);

        SharedPreferences preferences = getSharedPreferences("userDetails",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        userLocalStore = new UserLocalStore(this);
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