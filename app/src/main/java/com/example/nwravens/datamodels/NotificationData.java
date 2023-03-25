package com.example.nwravens.datamodels;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class NotificationData implements Serializable {
    @SerializedName("id")
    public String id;
    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String description;

    @SerializedName("image_url")
    public String imageUrl;

    @SerializedName("is_new")
    public boolean is_new;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
