package com.example.nwravens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RcNotification1DescriptionActivity extends AppCompatActivity {

    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rc_notification1_description);
//        backButton = findViewById(R.id.button);

//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(RcNotification1DescriptionActivity.this, RcNotificationSpecificActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
    }
}