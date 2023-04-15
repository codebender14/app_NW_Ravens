package com.example.nwravens.activities.home.category;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nwravens.R;
import com.example.nwravens.actions.SwipeActionsCallback;
import com.example.nwravens.datamodels.NotificationCategory;
import com.example.nwravens.datamodels.NotificationData;
import com.example.nwravens.datamodels.Notifications;
import com.example.nwravens.datarepository.DataRepository;
import com.example.nwravens.firebase.FirebaseDataCallback;
import com.example.nwravens.provider.ObjectProvider;

import java.util.Iterator;
import java.util.function.Predicate;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class CategoryScreenActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private CategoryScreenNotificationsAdapter adapter;
    private RecyclerView recyclerView;
    private DataRepository dataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        recyclerView = findViewById(R.id.home_recycler_view);
        adapter = new CategoryScreenNotificationsAdapter(this, launchActivity);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
        ObjectProvider.setRecyclerViewDivider(recyclerView);
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
                    adapter.updateList(selectedCategory.notification_data);
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

                for (Iterator<NotificationData> iterator = notification.notification_data.iterator(); iterator.hasNext();) {
                    NotificationData notification_datum = iterator.next();
                    notification_datum.is_new = !dataRepository.getReadStatus(notification_datum.id);
                    if (dataRepository.isDeleted(notification_datum.id)) {
//                        notification.notification_data.remove(notification_datum);
                        iterator.remove();
                    }
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

    ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            // Take action for the swiped item
            if (direction == ItemTouchHelper.LEFT) {
                adapter.markAsRead(viewHolder.getLayoutPosition());
            } else if (direction == ItemTouchHelper.RIGHT) {
                adapter.deleteItem(viewHolder.getLayoutPosition());
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(R.color.mark_as_red)
                    .addSwipeRightBackgroundColor(R.color.delete_red)
                    //                    .setSwipeLeftActionIconTint(R.color.white)
                    //                    .setSwipeRightActionIconTint(R.color.white)
                    .addSwipeLeftActionIcon(R.drawable.baseline_remove_red_eye_24)
                    .addSwipeRightActionIcon(R.drawable.baseline_delete_sweep_24)
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

}
