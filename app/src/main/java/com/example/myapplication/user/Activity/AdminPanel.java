package com.example.myapplication.user.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.R;
import com.example.myapplication.user.UserLocalStore;
import com.example.myapplication.user.userTypes.NormalUser;
import com.example.myapplication.user.userTypes.User;

public class AdminPanel extends AppCompatActivity {
    private UserLocalStore userLocalStore;
    TextView tvName;
    TextView tvAge;
    TextView tvUsername;
    TextView tvPassword;
    Button btnAprove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        tvName = findViewById(R.id.tvName);
        tvAge = findViewById(R.id.tvAge);
        tvUsername = findViewById(R.id.tvUsername);
        tvPassword = findViewById(R.id.tvPassword);
        btnAprove = findViewById(R.id.btnAprove);
        userLocalStore = new UserLocalStore(AdminPanel.this);
        loadUserDetails();
        Button btnRemoveUser = findViewById(R.id.btnRemoveUser);
        btnRemoveUser.setOnClickListener(view -> {
            userLocalStore.RemoveUser();
            tvName.setText("");
            tvAge.setText("");
            tvUsername.setText("");
            tvPassword.setText("");
            Toast.makeText(AdminPanel.this, "User removed from database", Toast.LENGTH_LONG).show();
            startActivity(new Intent(AdminPanel.this, Login.class));

        });
        btnAprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NormalUser.setStatus(true);
                userLocalStore.setUserStatus(NormalUser.getStatus());
                Toast.makeText(AdminPanel.this, "User approved", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadUserDetails() {
        User userRegistered = userLocalStore.getLoggedInUser();
        tvName.setText("Name: "+userRegistered.getName());
        tvAge.setText("Age: "+userRegistered.getAge());
        tvUsername.setText("Username: "+userRegistered.getUsername());
        tvPassword.setText("Password: "+userRegistered.getPassword());

    }
}