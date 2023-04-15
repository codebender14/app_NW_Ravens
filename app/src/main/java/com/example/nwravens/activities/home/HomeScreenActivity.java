package com.example.nwravens.activities.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.nwravens.LoginActivity;
import com.example.nwravens.activities.home.error.NoInternetActivity;
import com.example.nwravens.datamodels.Notifications;
import com.example.nwravens.datarepository.DataRepository;
import com.example.nwravens.firebase.FirebaseDataCallback;
import com.example.nwravens.provider.ObjectProvider;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nwravens.databinding.ActivityHomeScreenBinding;

import com.example.nwravens.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeScreenActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private DataRepository dataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        recyclerView = findViewById(R.id.home_recycler_view);
//        ObjectProvider.setRecyclerViewDivider(recyclerView);
        dataRepository = ObjectProvider.getDataRepo(this);

        progressDialog = ObjectProvider.getProgressDialog(this);

    }

    void setContent() {
        dataRepository.getNotifications(new FirebaseDataCallback() {
            @Override
            public void showData(Notifications notifications) {
                recyclerView.setAdapter(new HomeScreenNotificationsAdapter(HomeScreenActivity.this, notifications.notifications));

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

    @Override
    protected void onResume() {
        super.onResume();
        if (ObjectProvider.isOnline(this)) {
            setContent();
        } else {
            startActivity(new Intent(this, NoInternetActivity.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.sign_out) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        }
        return false;
    }
}
