package com.example.nwravens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RcNotificationSpecificActivity extends AppCompatActivity {
    private Button backButton;
    private TextView category1TextView;
    private TextView category2TextView;
    private TextView category3TextView;
    private TextView category4TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rc_notification_specific);
//        backButton = findViewById(R.id.button);
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(RcNotificationSpecificActivity.this, NotificationHomeActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
        category1TextView = findViewById(R.id.noti1);
        category1TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RcNotificationSpecificActivity.this,RcNotification1DescriptionActivity.class);
                startActivity(intent);
            }
        });
        category2TextView = findViewById(R.id.noti2);
        category2TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RcNotificationSpecificActivity.this, RcNotification2DescriptionActivity.class);
                startActivity(intent);
            }
        });
//        category3TextView = findViewById(R.id.noti3);
//        category3TextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(RcNotificationSpecificActivity.this, RcNotification3DescriptionActivity.class);
//                startActivity(intent);
//            }
//        });
//        category4TextView = findViewById(R.id.noti4);
//        category4TextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(RcNotificationSpecificActivity.this, RcNotification4DescriptionActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}