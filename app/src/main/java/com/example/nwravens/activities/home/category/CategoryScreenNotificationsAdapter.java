package com.example.nwravens.activities.home.category;

import static androidx.activity.result.ActivityResultCallerKt.registerForActivityResult;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nwravens.R;
import com.example.nwravens.activities.home.details.NotificationDetailActivity;
import com.example.nwravens.datamodels.NotificationData;

import java.util.List;

public class CategoryScreenNotificationsAdapter extends RecyclerView.Adapter<CategoryScreenNotificationsAdapter.HomeScreenNotificationsViewHolder> {

    private final List<NotificationData> list;
    private final ActivityResultLauncher<Intent> launchActivity;

    CategoryScreenNotificationsAdapter(ActivityResultLauncher<Intent> launchActivity, List<NotificationData> list) {
        this.launchActivity = launchActivity;
        this.list = list;
    }


    @NonNull
    @Override
    public HomeScreenNotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_screen_list_item, parent, false);
        return new HomeScreenNotificationsViewHolder(launchActivity, inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeScreenNotificationsViewHolder holder, int position) {
        holder.notificationData = list.get(position);
        holder.notificationName.setText(list.get(position).title);
        if (list.get(position).is_new) {
            holder.notificationStatus.setBackground(ContextCompat.getDrawable(holder.notificationStatus.getContext(), R.drawable.unred));
        } else {
            holder.notificationStatus.setBackground(null);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class HomeScreenNotificationsViewHolder extends RecyclerView.ViewHolder {

        private final TextView notificationName;
        private final TextView notificationStatus;

        private NotificationData notificationData;

        public HomeScreenNotificationsViewHolder(ActivityResultLauncher<Intent> launchActivity, @NonNull View itemView) {
            super(itemView);
            notificationName = itemView.findViewById(R.id.category_screen_notification_list_item_text);
            notificationStatus = itemView.findViewById(R.id.category_screen_notification_list_item_unred_status);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), NotificationDetailActivity.class);
                    intent.putExtra("notification_data", notificationData);
                    launchActivity.launch(intent);
                }
            });
        }
    }


}
