package com.example.nwravens;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nwravens.activities.home.HomeScreenActivity;
import com.example.nwravens.datamodels.NotificationCategory;
import com.example.nwravens.datamodels.NotificationData;
import com.example.nwravens.datamodels.Notifications;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
//            addFireStoreData();
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }
    }


}