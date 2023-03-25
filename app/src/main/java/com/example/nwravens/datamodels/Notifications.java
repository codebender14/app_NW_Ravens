package com.example.nwravens.datamodels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Notifications {
    @SerializedName("notifications")
    public List<NotificationCategory> notifications;

    public List<NotificationCategory> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationCategory> notifications) {
        this.notifications = notifications;
    }
}
