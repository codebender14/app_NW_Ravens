package com.example.nwravens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NWNewsNotificationSpecificActivity extends AppCompatActivity {
    private Button backButton;
    private TextView category1TextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nwnews_notification_specific);
        backButton = findViewById(R.id.button);
        category1TextView = findViewById(R.id.noti1);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NWNewsNotificationSpecificActivity.this, NotificationHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        category1TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NWNewsNotificationSpecificActivity.this,WcNotification1DescriptionActivity.class);
                startActivity(intent);
            }
        });
    }
}