package com.example.nwravens;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NotificationHomeActivity extends AppCompatActivity {

    private TextView wellnessCenter;
    private TextView RecreationCenter;
    private TextView NW_Events;
    private TextView NW_News;
    private TextView Academics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_home);

        wellnessCenter = findViewById(R.id.wc);
        wellnessCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(NotificationHomeActivity.this, WcNotificationSpecificActivity.class);
                startActivity(intent1);
            }
        });
        RecreationCenter = findViewById(R.id.rc);
        RecreationCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(NotificationHomeActivity.this, RcNotificationSpecificActivity.class);
                startActivity(intent2);
            }
        });
        NW_Events = findViewById(R.id.nwe);
        NW_Events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(NotificationHomeActivity.this, NWEventsNotificationSpecificActivity.class);
                startActivity(intent4);
            }
        });
        NW_News = findViewById(R.id.nwn);
        NW_News.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(NotificationHomeActivity.this, NWNewsNotificationSpecificActivity.class);
                startActivity(intent5);
            }
        });
        Academics = findViewById(R.id.aca);
        Academics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6 = new Intent(NotificationHomeActivity.this, NWAcademicsNotificationSpecificActivity.class);
                startActivity(intent6);
            }
        });


    }
}
