package com.example.myapplication.HomeActivity.Notification;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;

public class NotificationActivity extends AppCompatActivity {

    int i =0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        final Handler handler = new Handler();
        Runnable runnable = new Runnable()
        {
            @Override
            public void run() {
                try
                {
                    String Title;
                    String Message;
                    NotifyGenerator MG= new NotifyGenerator();
                    Title = MG.TGenerator(i);
                    Message = MG.MGenerator(i);

                    AlertDialog.Builder builder = new AlertDialog.Builder(NotificationActivity.this);

                    builder.setCancelable(true);
                    builder.setTitle(Title);
                    builder.setMessage(Message);
                    builder.setNegativeButton("Okay", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.cancel();
                        }
                    });


                    if(i<2)
                    i++;
                    else
                        i=0;

                    builder.show();
                }
                catch (Exception e)
                {


                }
                finally
                {
                    handler.postDelayed(this,300000);


                }
            }
        };

        handler.post(runnable);

    }
}