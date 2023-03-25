package com.example.nwravens.activities.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nwravens.R;
import com.example.nwravens.datamodels.NotificationCategory;

import java.util.List;

public class HomeScreenNotificationsAdapter extends RecyclerView.Adapter<HomeScreenNotificationsAdapter.HomeScreenNotificationsViewHolder> {

    private List<NotificationCategory> list;

    HomeScreenNotificationsAdapter(List<NotificationCategory> list) {

        this.list = list;
    }

    @NonNull
    @Override
    public HomeScreenNotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_screen_list_item, parent, false);
        return new HomeScreenNotificationsViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeScreenNotificationsViewHolder holder, int position) {
        holder.notificationName.setText(list.get(position).category);
        holder.notificationCount.setText(String.valueOf(list.get(position).notification_count));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class HomeScreenNotificationsViewHolder extends RecyclerView.ViewHolder {

        private TextView notificationName;
        private TextView notificationCount;

        public HomeScreenNotificationsViewHolder(@NonNull View itemView) {
            super(itemView);
            notificationName = itemView.findViewById(R.id.home_screen_notification_list_item_text);
            notificationCount = itemView.findViewById(R.id.home_screen_notification_list_item_text_count);
        }
    }
}