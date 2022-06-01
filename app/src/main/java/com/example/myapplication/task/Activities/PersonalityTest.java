package com.example.myapplication.task.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.R;
import com.example.myapplication.user.Personality.Energetic;
import com.example.myapplication.user.Personality.Personality;
import com.example.myapplication.user.Personality.Procrastinator;
import com.example.myapplication.user.Personality.Workaholic;
import com.example.myapplication.user.userTypes.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;

public class PersonalityTest extends AppCompatActivity {

    String answer1;
    String answer2;
    String answer3;
    int energeticCounter =0, procrastinatorCounter=0, workaholicCounter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_test2);
        //SharedPreferences SavedPersonality = getPreferences(MODE_PRIVATE);
        SharedPreferences mPrefs = getSharedPreferences("pre", 0);
        SharedPreferences preferences = getSharedPreferences("userDetails",MODE_PRIVATE);
        boolean firstStart = preferences.getBoolean("firstStart", true);


        if(firstStart)
            showMainActivity();

        ArrayList<String> AnswersList = new ArrayList<String>();

        CheckBox Q1Option1 = (CheckBox) findViewById(R.id.Q1Option1);
        CheckBox Q1Option2 = (CheckBox) findViewById(R.id.Q1Option2);
        CheckBox Q1Option3 = (CheckBox) findViewById(R.id.Q1Option3);

        Button Transition = (Button) findViewById(R.id.Hello);


        CheckBox Q2Option1 = (CheckBox) findViewById(R.id.Q2Option1);
        CheckBox Q2Option2 = (CheckBox) findViewById(R.id.Q2Option2);
        CheckBox Q2Option3 = (CheckBox) findViewById(R.id.Q2Option3);

        CheckBox Q3Option1 = (CheckBox) findViewById(R.id.Q3Option1);
        CheckBox Q3Option2 = (CheckBox) findViewById(R.id.Q3Option2);
        CheckBox Q3Option3 = (CheckBox) findViewById(R.id.Q3Option3);


        Q1Option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Q1Option2.setChecked(false);
                Q1Option3.setChecked(false);

            }

        });

        Q1Option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Q1Option1.setChecked(false);
                Q1Option3.setChecked(false);

            }

        });

        Q1Option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Q1Option1.setChecked(false);
                Q1Option2.setChecked(false);

            }

        });

        Q3Option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Q3Option2.setChecked(false);
                Q3Option3.setChecked(false);


            }

        });

        Q3Option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Q3Option1.setChecked(false);
                Q3Option3.setChecked(false);


            }

        });

        Q3Option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Q3Option1.setChecked(false);
                Q3Option2.setChecked(false);

            }

        });


        Transition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonalityTest.this,pomodoroTimer.class));
                SharedPreferences.Editor prefsEditor;
                //Toast.makeText(MainActivity.this, "Pressed", Toast.LENGTH_SHORT).show();

                if(Q1Option1.isChecked()) {
                    answer1= Q1Option1.getText().toString();
                    AnswersList.add(answer1);
                    procrastinatorCounter++;
                }

                else if(Q1Option2.isChecked())
                {
                    answer1 = Q1Option2.getText().toString();
                    AnswersList.add(answer1);
                    energeticCounter++;
                }

                else if(Q1Option3.isChecked())
                {
                    answer1 = Q1Option3.getText().toString();
                    AnswersList.add(answer1);
                    workaholicCounter++;
                }


                if(Q2Option1.isChecked()) {
                    answer2 = Q2Option1.getText().toString();
                    AnswersList.add(answer2);
                    procrastinatorCounter++;
                }

                else if(Q2Option2.isChecked())
                {
                    answer2 = Q2Option2.getText().toString();
                    AnswersList.add(answer2);
                    energeticCounter++;
                }

                else if(Q2Option3.isChecked())
                {
                    answer2 = Q2Option3.getText().toString();
                    AnswersList.add(answer2);
                    workaholicCounter++;
                }

                if(Q3Option1.isChecked()) {
                    answer3 = Q3Option1.getText().toString();
                    AnswersList.add(answer3);
                    energeticCounter++;
                }

                else if(Q3Option2.isChecked())
                {
                    answer3 = Q3Option2.getText().toString();
                    AnswersList.add(answer3);
                    workaholicCounter++;
                }

                else if(Q3Option3.isChecked())
                {
                    answer3 = Q3Option3.getText().toString();
                    AnswersList.add(answer3);
                    procrastinatorCounter++;
                }
                //energetic

//                if()
//                {
//                    Energetic UserPersonality = new Energetic();
//                    UserPersonality.name = "Andrew";
//                    SharedPreferences.Editor prefEditor = mPrefs.edit();
//                    Gson gson = new Gson();
//                    String json = gson.toJson(UserPersonality);
//                    prefEditor.putString("MyObject",json);
//                    prefEditor.apply();
//
//                }

                if (energeticCounter > workaholicCounter && energeticCounter > procrastinatorCounter) {
                    //Here you determine second biggest, but you know that a is largest
                    Personality UserPersonality = new Energetic();
                    UserPersonality.setName("Energetic");
                    SharedPreferences.Editor prefEditor = mPrefs.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(UserPersonality);
                    prefEditor.putString("Personality",json);
                    prefEditor.apply();
                }

                else if (workaholicCounter > energeticCounter && workaholicCounter > procrastinatorCounter) {
                    //Here you determine second biggest, but you know that b is largest
                    Personality UserPersonality = new Workaholic();
                    UserPersonality.setName("Workaholic");
                    SharedPreferences.Editor prefEditor = mPrefs.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(UserPersonality);
                    prefEditor.putString("Personality",json);

                    prefEditor.apply();
                }

                else  {
                    //Here you determine second biggest, but you know that c is largest
                    //this is the default
                    Personality UserPersonality = new Procrastinator();
                    UserPersonality.setName("Procrastinator");
                    SharedPreferences.Editor prefEditor = mPrefs.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(UserPersonality);
                    prefEditor.putString("Personality",json);

                    prefEditor.apply();
                }

            }
        });


    }
    private void showMainActivity() {
        //store that the user has already toke the test so he won't take it again when login
        SharedPreferences preferences = getSharedPreferences("userDetails",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("firstStart",false);
        editor.apply();


    }




}