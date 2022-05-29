package com.example.myapplication.task.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.HomeActivity.Home;
import com.example.myapplication.R;
import com.example.myapplication.user.Personality.Active;
import com.example.myapplication.user.Personality.Personality;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public class PersonalityTest extends AppCompatActivity {

    String answer1;
    String answer2;
    String answer3;
    //String answer4;
    //String answer5;


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




        //int constant = 0;
        //INITIALIZING ALL CHOICES
        // Button NextQuestionButton = (Button) findViewById(R.id.NextButton);
        CheckBox Q1Option1 = (CheckBox) findViewById(R.id.Q1Option1);
        CheckBox Q1Option2 = (CheckBox) findViewById(R.id.Q1Option2);
        CheckBox Q1Option3 = (CheckBox) findViewById(R.id.Q1Option3);
        //TextView Question1 = (TextView) findViewById(R.id.Question1);
        CheckBox Q1Option4 = (CheckBox) findViewById(R.id.Q1Option4);


        Button Transition = (Button) findViewById(R.id.Hello);


        CheckBox Q2Option1 = (CheckBox) findViewById(R.id.Q2Option1);
        CheckBox Q2Option2 = (CheckBox) findViewById(R.id.Q2Option2);
        CheckBox Q2Option3 = (CheckBox) findViewById(R.id.Q2Option3);
        //CheckBox Q2Option4 = (CheckBox) findViewById(R.id.Q2Option4);

        CheckBox Q3Option1 = (CheckBox) findViewById(R.id.Q3Option1);
        CheckBox Q3Option2 = (CheckBox) findViewById(R.id.Q3Option2);
        CheckBox Q3Option3 = (CheckBox) findViewById(R.id.Q3Option3);
        //CheckBox Q3Option4 =(CheckBox) findViewById(R.id.Q3Option4);                          //There is still no option4 waiting to be added....


        CheckBox Q4Option1 = (CheckBox) findViewById(R.id.Q4Option1);
        CheckBox Q4Option2 = (CheckBox) findViewById(R.id.Q4Option2);
        CheckBox Q4Option3 = (CheckBox) findViewById(R.id.Q4Option3);
        //CheckBox Q4option4 =(CheckBox) findViewById(R.id.Q4Option4);                          //There is still no option4 waiting to be added....

        CheckBox Q5Option1 = (CheckBox) findViewById(R.id.Q5Option1);
        CheckBox Q5Option2 = (CheckBox) findViewById(R.id.Q5Option2);
        CheckBox Q5Option3 = (CheckBox) findViewById(R.id.Q5Option3);
        //CheckBox Q5Option4 =(CheckBox) findViewById(R.id.Q5Option4);                          //There is still no option4 waiting to be added....


        Q1Option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Q1Option2.setChecked(false);
                Q1Option3.setChecked(false);
                Q1Option4.setChecked(false);

            }

        });

        Q1Option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Q1Option1.setChecked(false);
                Q1Option3.setChecked(false);
                Q1Option4.setChecked(false);

            }

        });

        Q1Option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Q1Option1.setChecked(false);
                Q1Option2.setChecked(false);
                Q1Option4.setChecked(false);

            }

        });

        Q1Option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Q1Option1.setChecked(false);
                Q1Option2.setChecked(false);
                Q1Option3.setChecked(false);

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

        Q4Option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Q4Option2.setChecked(false);
                Q4Option3.setChecked(false);


            }

        });

        Q4Option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Q4Option1.setChecked(false);
                Q4Option3.setChecked(false);

            }

        });

        Q4Option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Q4Option1.setChecked(false);
                Q4Option2.setChecked(false);

            }

        });

        Q5Option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Q5Option2.setChecked(false);
                Q5Option3.setChecked(false);

            }

        });

        Q5Option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Q5Option1.setChecked(false);
                Q5Option3.setChecked(false);

            }

        });

        Q5Option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Q5Option1.setChecked(false);
                Q5Option2.setChecked(false);

            }

        });

        if(Q1Option1.isChecked())
        {
            //constant = 5;
        }

        Transition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonalityTest.this,pomodoroTimer.class));
                SharedPreferences.Editor prefsEditor;
                //Toast.makeText(MainActivity.this, "Pressed", Toast.LENGTH_SHORT).show();

                if(Q1Option1.isChecked()) {
                    answer1= Q1Option1.getText().toString();
                    AnswersList.add(answer1);
                }

                else if(Q1Option2.isChecked())
                {
                    answer1 = Q1Option2.getText().toString();
                    AnswersList.add(answer1);
                }

                else if(Q1Option3.isChecked())
                {
                    answer1 = Q1Option3.getText().toString();
                    AnswersList.add(answer1);
                }

//                else if(Q1Option4.isChecked())
//                {
//                    answer1= Integer.toString(Q1Option4.getId());
//                    AnswersList.add(answer1);
//                }



                if(Q2Option1.isChecked()) {
                    answer2 = Q2Option1.getText().toString();
                    AnswersList.add(answer2);
                }

                else if(Q2Option2.isChecked())
                {
                    answer2 = Q2Option2.getText().toString();
                    AnswersList.add(answer2);
                }

                else if(Q2Option3.isChecked())
                {
                    answer2 = Q2Option3.getText().toString();
                    AnswersList.add(answer2);
                }

//                else if(Q2Option4.isChecked())
//                {
//                    answer2[0] = Integer.toString(Q2Option4.getId());
//                    AnswersList.add(answer2[0]);
//                }
                if(Q3Option1.isChecked()) {
                    answer3 = Q3Option1.getText().toString();
                    AnswersList.add(answer3);
                }

                else if(Q3Option2.isChecked())
                {
                    answer3 = Q3Option2.getText().toString();
                    AnswersList.add(answer3);
                }

                else if(Q3Option3.isChecked())
                {
                    answer3 = Q3Option3.getText().toString();
                    AnswersList.add(answer3);
                }





                //SharedPreferences.Editor editor = sharedpreferences.edit();
                //editor.putStringSet("Personality", (Set<String>) AnswersList);


                if(AnswersList.get(0) == Q1Option1.getText().toString())
                {
                    // Personality personality = new LongBreaks();
                    //editor.putString("Personality", "LongBreaks");
                    //editor.commit();
                    Active UserPersonality = new Active();
                    UserPersonality.name = "Andrew";
                    SharedPreferences.Editor prefEditor = mPrefs.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(UserPersonality);
                    prefEditor.putString("MyObject",json);
                    prefEditor.apply();

                }


                if(Objects.equals(AnswersList.get(1), Q1Option2.getText().toString()))
                {
                    Active UserPersonality = new Active();
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

//    public void saveObject(Personality UserPersonality){
//        Gson gson = new Gson();
//        String json = gson.toJson(UserPersonality);
//        prefsEditor.putString("MyObject", json);
//        prefsEditor.commit();
//        //Toast.makeText(getApplicationContext(), "Object Stored", 1000).show();
//    }


//    public void DeterminePersonality(ArrayList<String>AnswersList)
//    {
//        if((AnswersList.get(1) == Q1Option1.getText().toString() &)
//        {
//
//        }
//    }






}