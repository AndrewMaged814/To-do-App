package com.example.myapplication.user.Activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.myapplication.R;
import com.example.myapplication.user.UserLocalStore;
import com.example.myapplication.user.ValideRegister;
import com.example.myapplication.user.userTypes.NormalUser;
import com.example.myapplication.user.userTypes.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Register extends AppCompatActivity implements View.OnClickListener, ValideRegister {
    private EditText etName, etUsername, etAge, etPassword,etConfirmPassword;
    private CardView cardMale,cardFemale;
    private UserLocalStore userLocalStore;
    ImageView imageView;
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etName = findViewById(R.id.etName);
        etUsername = findViewById(R.id.etUsername);
        etAge = findViewById(R.id.etAge);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        Button btRegister = findViewById(R.id.btnRegister);
        cardMale = findViewById(R.id.card_male);
        cardFemale = findViewById(R.id.card_female);
        btRegister.setOnClickListener(this);
        userLocalStore = new UserLocalStore(Register.this);
        cardMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardMale.setCardBackgroundColor(Color.parseColor("#309397"));
                cardFemale.setCardBackgroundColor(Color.parseColor("#fff9ec"));
                gender = "male";
            }
        });
        cardFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardFemale.setCardBackgroundColor(Color.parseColor("#e46472"));
                cardMale.setCardBackgroundColor(Color.parseColor("#fff9ec"));
                gender = "female";
            }
        });


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnRegister) {
            final String name = etName.getText().toString();
            final String Username = etUsername.getText().toString();
            final String password = etPassword.getText().toString();
            final String ConfirmedPassword = etConfirmPassword.getText().toString();
            final int age = Integer.parseInt(etAge.getText().toString());
            //validate each field that is taken as input
            if (ValideRegister(name, Username, password, String.valueOf(age),ConfirmedPassword)) {
                //if it validates then register the user as a new user and store their details into the database
                User user = new NormalUser(name, Username, password, age,gender);
                registerUser(user);


            }

            }
        }

@Override
    public boolean ValideRegister(String name, String username, String password, String age, String confirmedPassword) {
        if(name.isEmpty()){
            YoYo.with(Techniques.Bounce).duration(700).repeat(1).playOn(etName);
            etName.setError("full name is required");
            etName.requestFocus();
            return false;
        }
        if(age.isEmpty()){
            YoYo.with(Techniques.Bounce).duration(700).repeat(1).playOn(etAge);
            etAge.setError("Age is required ");
            etAge.requestFocus();
            return false;
        }
        if(username.isEmpty()){
            YoYo.with(Techniques.Bounce).duration(700).repeat(1).playOn(etUsername);
            etUsername.setError("Username is required");
            etUsername.requestFocus();
            return false;
        }
        if(password.isEmpty()){
            YoYo.with(Techniques.Bounce).duration(700).repeat(1).playOn(etPassword);
            etPassword.setError("password is required");
            etPassword.requestFocus();
            return false;
        }
        if(password.length() < 5){
            YoYo.with(Techniques.Bounce).duration(700).repeat(1).playOn(etPassword);
            etPassword.setError("Min password length should be 5 characters");
            etPassword.requestFocus();
            return false;
        }
        if(!password.equals(confirmedPassword)){
            YoYo.with(Techniques.Bounce).duration(700).repeat(1).playOn(etConfirmPassword);
            etConfirmPassword.setError("Password doesn't match");
            etConfirmPassword.requestFocus();
            return false;
        }

        return true;
    }
@Override
     public void registerUser(User user) {
        userLocalStore.storeUserData(user);
        Toast.makeText(Register.this, "Registered successfully!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, Login.class));
    }
}