package com.example.myapplication.chat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.example.myapplication.R;

public class ChatStartUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_chat);

        //if permission not granted must ask for permission to access gallery
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},10);

        }

        EditText editText = findViewById(R.id.editText);

        //when button is clicked will go to chat activity
        findViewById(R.id.EnterButton)
                .setOnClickListener(v -> {

                    Intent intent = new Intent(this, ChatActivity.class);
                    intent.putExtra("name", editText.getText().toString());
                    startActivity(intent);
                });
    }
}