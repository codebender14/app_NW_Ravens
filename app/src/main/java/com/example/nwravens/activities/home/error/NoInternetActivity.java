package com.example.nwravens.activities.home.error;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.nwravens.R;

public class NoInternetActivity extends AppCompatActivity {

    // this is a default screen when the app is not connected to the internet
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);
    }
}
