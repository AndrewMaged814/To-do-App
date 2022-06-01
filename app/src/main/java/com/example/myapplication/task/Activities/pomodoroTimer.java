
package com.example.myapplication.task.Activities;

import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.myapplication.R;
import com.example.myapplication.user.Personality.Energetic;
import com.example.myapplication.user.Personality.Workaholic;
import com.example.myapplication.user.Personality.Personality;
import com.example.myapplication.user.Personality.Procrastinator;
import com.google.gson.Gson;

import java.util.Locale;

public class pomodoroTimer extends AppCompatActivity {
    private static long START_TIME_IN_MILLIS;
    private static long Break_TIME_IN_MILLIS;
    private int i=0;
    private TextView mTextViewCountDown, tvTimeSpent,tvTaskName,tvNoOfCycles;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private TextView mBreak;
    private ProgressBar mProgressBar;
    LottieAnimationView lottieAnimationView;
    int cycles=0;
    int noOfSeassions=0;
    long totalTimeSpent = 0;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;
    private boolean isBreak;
    long No_of_Seconds=0;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private long totalTimeCountInMilliseconds;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro_timer);

        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mBreak = findViewById(R.id.tvBreak);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);
        mProgressBar = findViewById(R.id.ProgressBar);
        tvTimeSpent = findViewById(R.id.tvTimeSpent);
        tvTaskName = findViewById(R.id.tvPomodoroTaskName);
        tvNoOfCycles = findViewById(R.id.tvNoOfCycles);
        lottieAnimationView = findViewById(R.id.lottieTimer);
        String TaskName = getIntent().getStringExtra("TaskNamePomodoro");
        tvTaskName.setText(TaskName);


        SharedPreferences mPrefs= getSharedPreferences("pre", 0);
        Gson gson = new Gson();
        String json = mPrefs.getString("Personality","");

           // obj =gson.fromJson(json, Energetic.class);
           //Energetic obj = new Energetic();
        if(json.contains("Energetic")) {
            Personality obj = gson.fromJson(json, Personality.class);
            DeterminingPersonality(obj);
        }


        else if(json.contains("Workaholic")) {
            Personality obj = gson.fromJson(json, Workaholic.class);
            DeterminingPersonality(obj);
        }
        else if(json.contains("Procrastinator")) {
            Personality obj = gson.fromJson(json, Procrastinator.class);

            DeterminingPersonality(obj);
        }


        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    setTimer();
                    startTimer();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateCountDownText();


    }
//    public Personality getObject(){
//        Gson gson = new Gson();
//        String json = mPrefs.getString("MyObject", "");
//        Personality obj = gson.fromJson(json, Personality.class);
//        return obj ;
//    }
    public void DeterminingPersonality(Personality x)
    {
//        if(x instanceof Workaholic)
//        {
////            START_TIME_IN_MILLIS =((LongBreak) x).noOfHours;
////            Break_TIME_IN_MILLIS =((LongBreak) x).TimerDuration;
//            Toast.makeText(pomodoroTimer.this, "longBreak", Toast.LENGTH_LONG).show();
//        }
//
//        if(x instanceof Procrastinator)
//        {
////            START_TIME_IN_MILLIS =((ShortBreaks) x).noOfHours;
////            Break_TIME_IN_MILLIS =((ShortBreaks) x).TimerDuration;
//            Toast.makeText(pomodoroTimer.this, "ShortBreak", Toast.LENGTH_LONG).show();
//        }
//
//        if(x instanceof Energetic)
//        {
//            Toast.makeText(pomodoroTimer.this, "Active", Toast.LENGTH_LONG).show();
////            START_TIME_IN_MILLIS =((Active) x).noOfHours;
////            Break_TIME_IN_MILLIS =((Active) x).TimerDuration;
//

        if(x instanceof Energetic){
            START_TIME_IN_MILLIS = ((Energetic)x).NO_OF_MIN;
            Break_TIME_IN_MILLIS = ((Energetic)x).TIMER_DURATION;
            Toast.makeText(pomodoroTimer.this, "energetic", Toast.LENGTH_LONG).show();
        }
        else if(x instanceof Workaholic){
            Toast.makeText(pomodoroTimer.this, "workaholic", Toast.LENGTH_LONG).show();
            START_TIME_IN_MILLIS = ((Workaholic)x).NO_OF_MIN;
            Break_TIME_IN_MILLIS = ((Workaholic)x).TIMER_DURATION;

        }
        else if(x instanceof Procrastinator){
            Toast.makeText(pomodoroTimer.this, "Procrastinator", Toast.LENGTH_LONG).show();
            START_TIME_IN_MILLIS = x.NO_OF_MIN;
            Break_TIME_IN_MILLIS =x.TIMER_DURATION;
        }
    }

    private void startTimer() {
        //mProgressBar.setProgress(i);
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1) {

            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();

                mProgressBar.setProgress((int) (millisUntilFinished));

            }

            @Override
            public void onFinish() {

                mTimerRunning = false;
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.INVISIBLE);
                mTimeLeftInMillis = Break_TIME_IN_MILLIS;
                i++;
                mProgressBar.setProgress(100);
                noOfSeassions++;
                if(noOfSeassions%4 == 0){
                    cycles++;
                    tvNoOfCycles.setText("No of cycles: "+cycles);
                }

                tvNoOfCycles.setText("No of cycles: "+cycles);


                startBreakTimer();





            }
        }.start();

       // lottieAnimationView.setVisibility(View.VISIBLE);
        mTimerRunning = true;
        mButtonStartPause.setText("Pause");
        lottieAnimationView.resumeAnimation();
        mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void startBreakTimer(){

        totalTimeSpent+= START_TIME_IN_MILLIS;
        long minutes = (totalTimeSpent / 1000) / 60;
        tvTimeSpent.setText("Total minutes: "+minutes);

        mProgressBar.setProgress(0);
        mBreak.setVisibility(View.VISIBLE);
        setBreakTimer();
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();

                mProgressBar.setProgress((int) (millisUntilFinished));

            }

            @Override
            public void onFinish() {

                mTimeLeftInMillis = START_TIME_IN_MILLIS;
                setTimer();
                startTimer();
                mButtonStartPause.setVisibility(View.VISIBLE);
                mBreak.setVisibility(View.INVISIBLE);


            }

        }.start();
        //lottieAnimationView.setVisibility(View.INVISIBLE);
    }



    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
        lottieAnimationView.pauseAnimation();
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
        i=0;
        mProgressBar.setProgress(i);
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }

    private void setTimer(){
        int time =(int) START_TIME_IN_MILLIS;

        totalTimeCountInMilliseconds =  time;
        mProgressBar.setMax( time);
    }

    private void setBreakTimer(){
        int time =(int) Break_TIME_IN_MILLIS;

        totalTimeCountInMilliseconds =  time;
        mProgressBar.setMax( time);
    }


}