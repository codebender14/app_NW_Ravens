package com.example.nwravens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button loginButton = findViewById(R.id.login);

        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, NotificationHomeActivity.class);
            startActivity(intent);
        });
    }

}