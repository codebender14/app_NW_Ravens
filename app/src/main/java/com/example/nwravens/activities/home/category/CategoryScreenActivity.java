package com.example.nwravens.activities.home.category;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nwravens.R;
import com.example.nwravens.datamodels.NotificationCategory;
import com.example.nwravens.datamodels.NotificationData;
import com.example.nwravens.datamodels.Notifications;
import com.example.nwravens.datarepository.DataRepository;
import com.example.nwravens.firebase.FirebaseDataCallback;
import com.example.nwravens.provider.ObjectProvider;

import java.util.function.Predicate;

public class CategoryScreenActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private DataRepository dataRepository;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        recyclerView = findViewById(R.id.home_recycler_view);
        dataRepository = ObjectProvider.getDataRepo(this);
        progressDialog = ObjectProvider.getProgressDialog(this);
        setTitle(getIntentCategory());
        setContent();
    }

    void setContent() {
        dataRepository.getNotifications(new FirebaseDataCallback() {
            @Override
            public void showData(Notifications notifications) {
                NotificationCategory selectedCategory = findSelectedCategory(notifications, getIntentCategory());
                if (selectedCategory != null) {
                    recyclerView.setAdapter(new CategoryScreenNotificationsAdapter(launchActivity, selectedCategory.notification_data));
                }

            }

            @Override
            public void showError(Throwable error) {

            }

            @Override
            public void showProgress() {
                progressDialog.show();
            }

            @Override
            public void hideProgress() {
                progressDialog.dismiss();
            }
        });
    }

    private NotificationCategory findSelectedCategory(Notifications notifications, String intentCategory) {
        for (NotificationCategory notification : notifications.notifications) {
            if (notification.category.equalsIgnoreCase(intentCategory)) {
                for (NotificationData notification_datum : notification.notification_data) {
                    notification_datum.is_new = !dataRepository.getReadStatus(notification_datum.id);
                }
                return notification;
            }
        }
        return null;
    }

    private String getIntentCategory() {
        return getIntent().getStringExtra("category_name");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    private final ActivityResultLauncher<Intent> launchActivity = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        setContent();
    });

}