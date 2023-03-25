package com.example.nwravens.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.nwravens.datamodels.NotificationCategory;
import com.example.nwravens.datamodels.Notifications;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class FirebaseDataReader {

    private FirebaseFirestore database = FirebaseFirestore.getInstance();


    private static final String COLLECTION_NAME = "notifications";


    public void readNotifications(FirebaseDataCallback firebaseDataCallback) {
        firebaseDataCallback.showProgress();
        CollectionReference collection = database.collection(COLLECTION_NAME);
        collection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                ArrayList<NotificationCategory> list = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    // Handle each document here
                    list.add(document.toObject(NotificationCategory.class));
                }
                Notifications notifications = new Notifications();
                notifications.setNotifications(list);
                firebaseDataCallback.showData(notifications);
                firebaseDataCallback.hideProgress();
            } else {
                firebaseDataCallback.showError(task.getException());
                firebaseDataCallback.hideProgress();
            }
        });
    }

}
