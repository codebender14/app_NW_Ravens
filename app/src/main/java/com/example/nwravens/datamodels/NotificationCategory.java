package com.example.nwravens.datamodels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificationCategory {
    //this is a notification category code to handle notification data
    @SerializedName("category")
    public String category;
    @SerializedName("notification_count")
    public int notification_count;
    @SerializedName("notification_data")
    public List<NotificationData> notification_data;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNotification_count() {
        return notification_count;
    }

    public void setNotification_count(int notification_count) {
        this.notification_count = notification_count;
    }

    public List<NotificationData> getNotification_data() {
        return notification_data;
    }

    public void setNotification_data(List<NotificationData> notification_data) {
        this.notification_data = notification_data;
    }
}
