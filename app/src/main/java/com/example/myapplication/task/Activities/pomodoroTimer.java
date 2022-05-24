
package com.example.myapplication.task.Activities;

import android.os.CountDownTimer;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.myapplication.R;

import java.util.Locale;

public class pomodoroTimer extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 15000;
    private static final long Break_TIME_IN_MILLIS = 3000;
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