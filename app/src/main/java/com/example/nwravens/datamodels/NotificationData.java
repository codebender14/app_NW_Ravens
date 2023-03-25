package com.example.nwravens.datamodels;

import com.google.gson.annotations.SerializedName;

public class NotificationData {
    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String description;

    @SerializedName("image_url")
    public String imageUrl;

    @SerializedName("is_new")
    public boolean isNew;

    @SerializedName("is_deleted")
    public boolean isDeleted;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
