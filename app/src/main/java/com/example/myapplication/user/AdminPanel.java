package com.example.myapplication.user;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.R;

public class AdminPanel extends AppCompatActivity {
    private UserLocalStore userLocalStore;
    TextView tvName;
    TextView tvAge;
    TextView tvUsername;
    TextView tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        tvName = findViewById(R.id.tvName);
        tvAge = findViewById(R.id.tvAge);
        tvUsername = findViewById(R.id.tvUsername);
        tvPassword = findViewById(R.id.tvPassword);
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
    }

    private void loadUserDetails() {
        User userRegistered = userLocalStore.getLoggedInUser();
        tvName.setText("Name: "+userRegistered.getName());
        tvAge.setText("Age: "+userRegistered.getAge());
        tvUsername.setText("Username: "+userRegistered.getUsername());
        tvPassword.setText("Password: "+userRegistered.getPassword());

    }
}