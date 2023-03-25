package com.example.nwravens;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nwravens.activities.home.HomeScreenActivity;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button loginButton = findViewById(R.id.login);

        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, HomeScreenActivity.class);
            startActivity(intent);
        });

//        try {
//            DataWriter.addFireStoreData(this);
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            addFireStoreData();
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }
    }


}