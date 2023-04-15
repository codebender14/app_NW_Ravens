package com.example.nwravens.firebase;

import android.content.Context;

import com.example.nwravens.datamodels.NotificationCategory;
import com.example.nwravens.datamodels.NotificationData;
import com.example.nwravens.datamodels.Notifications;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DataWriter {

    //this class will handle all the write operations

    private static String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("sample_json.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static void addFireStoreData(Context context) throws JSONException {
        // Get a Firestore instance
        Gson gson = new Gson();
        String jsonString = loadJSONFromAsset(context);
        Notifications notifications = gson.fromJson(jsonString, Notifications.class);


// Step 3: Convert the Java object to a Firestore document
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        for (NotificationCategory category : notifications.notifications) {
            Map<String, Object> categoryData = new HashMap<>();
            categoryData.put("category", category.category);
            categoryData.put("notification_count", category.notification_count);

            List<Map<String, Object>> notificationDataList = new ArrayList<>();
            for (NotificationData data : category.notification_data) {
                Map<String, Object> notificationData = new HashMap<>();
                notificationData.put("id", UUID.randomUUID().toString());
                notificationData.put("title", data.title);
                notificationData.put("description", data.description);
                notificationData.put("image_url", data.imageUrl);
                notificationData.put("is_new", data.is_new);
                notificationData.put("is_deleted", data.isDeleted);
                notificationDataList.add(notificationData);
            }

            categoryData.put("notification_data", notificationDataList);

            db.collection("notifications").document(category.category).set(categoryData);
        }
    }
}
