package com.example.nwravens.activities.home;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.nwravens.datamodels.Notifications;
import com.example.nwravens.datarepository.DataRepository;
import com.example.nwravens.firebase.FirebaseDataCallback;
import com.example.nwravens.provider.ObjectProvider;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nwravens.databinding.ActivityHomeScreenBinding;

import com.example.nwravens.R;

public class HomeScreenActivity extends AppCompatActivity {


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
        setContent();
    }

    void setContent() {
        dataRepository.getNotifications(new FirebaseDataCallback() {
            @Override
            public void showData(Notifications notifications) {
                recyclerView.setAdapter(new HomeScreenNotificationsAdapter(notifications.notifications));

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


}