package com.example.nwravens.activities.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nwravens.activities.home.category.CategoryScreenActivity;
import com.example.nwravens.datamodels.NotificationCategory;
import com.example.nwravens.datamodels.NotificationData;
import com.example.nwravens.datarepository.DataRepository;
import com.example.nwravens.provider.ObjectProvider;
import com.example.nwravens.R;

import java.util.List;

public class HomeScreenNotificationsAdapter extends RecyclerView.Adapter<HomeScreenNotificationsAdapter.HomeScreenNotificationsViewHolder> {

    
    private final DataRepository dataRepo;
    private List<NotificationCategory> list;

    HomeScreenNotificationsAdapter(Context context, List<NotificationCategory> list) {


        dataRepo = ObjectProvider.getDataRepo(context);
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
        NotificationCategory notificationCategory = list.get(position);
        holder.setNotificationCategory(notificationCategory);
        holder.notificationName.setText(notificationCategory.category);

        int count = 0;
        for (NotificationData notification_datum : notificationCategory.notification_data) {
            if (!dataRepo.isDeleted(notification_datum.id)) {
                count++;
            }
        }

        holder.notificationCount.setText(String.valueOf(count));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class HomeScreenNotificationsViewHolder extends RecyclerView.ViewHolder {

        
        private TextView notificationCount;
        private TextView notificationName;

        private NotificationCategory notificationCategory;

        public HomeScreenNotificationsViewHolder(@NonNull View itemView) {
            super(itemView);
            notificationName = itemView.findViewById(R.id.home_screen_notification_list_item_text);
            notificationCount = itemView.findViewById(R.id.home_screen_notification_list_item_text_count);
            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), CategoryScreenActivity.class);
                intent.putExtra("category_name", notificationCategory.category);
                view.getContext().startActivity(intent);
            });
        }

        public void setNotificationCategory(NotificationCategory notificationCategory) {
            this.notificationCategory = notificationCategory;
        }
    }
}
