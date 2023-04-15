package com.example.nwravens.activities.home.details;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nwravens.R;
import com.example.nwravens.datamodels.NotificationData;
import com.example.nwravens.datarepository.DataRepository;
import com.example.nwravens.provider.ObjectProvider;

public class NotificationDetailActivity extends AppCompatActivity {

    private DataRepository dataRepo;
    private TextView titleView;
    private TextView descriptionView;
    private ImageView imageView;
    private NotificationData notification_data;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wc_notification2_description);

        dataRepo = ObjectProvider.getDataRepo(this);

        titleView = findViewById(R.id.notificationLabel);
        descriptionView = findViewById(R.id.description_text);
        imageView = findViewById(R.id.imageview);
        notification_data = (NotificationData) getIntent().getSerializableExtra("notification_data");
        titleView.setText(notification_data.title);
        descriptionView.setText(notification_data.description);
        markDataAsRed(notification_data);

    }

    private void markDataAsRed(NotificationData notification_data) {
        dataRepo.setMarkRead(notification_data.id, true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.read_status);
        if (dataRepo.getReadStatus(notification_data.id)) {
            item.setTitle(R.string.mark_as_unread);
        } else {
            item.setTitle(R.string.mark_as_read);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.read_status:
                Boolean readStatus = dataRepo.getReadStatus(notification_data.id);
                dataRepo.setMarkRead(notification_data.id, !readStatus);
                return true;
            case R.id.delete:
                dataRepo.setDeleted(notification_data.id);
                setResult(RESULT_OK);
                finish();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_OK);
        finish();
    }
}
