package com.example.nwravens.datarepository;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.nwravens.firebase.FirebaseDataCallback;
import com.example.nwravens.firebase.FirebaseDataReader;

import java.io.Serializable;

public class DataRepository {

    private final Context context;
    private final FirebaseDataReader firebaseDataReader;
    private final SharedPreferences sharedPreferences;

    private static final String DATA_PREF = "data_prefs";

    public DataRepository(Context context, FirebaseDataReader firebaseDataReader) {

        this.context = context;
        this.firebaseDataReader = firebaseDataReader;

        sharedPreferences = context.getSharedPreferences(DATA_PREF, Context.MODE_PRIVATE);
    }


    public void getNotifications(FirebaseDataCallback firebaseDataCallback) {
        firebaseDataReader.readNotifications(firebaseDataCallback);
    }

    public void setDeleted(String id) {
        String readStatus = String.format("del_%s", id);
        sharedPreferences.edit().putBoolean(readStatus, true).apply();
    }

    public void setMarkRead(String id, Boolean status) {
        String readStatus = String.format("read_%s", id);
        sharedPreferences.edit().putBoolean(readStatus, status).apply();
    }

    public Boolean getReadStatus(String id) {
        String readStatus = String.format("read_%s", id);
        return sharedPreferences.getBoolean(readStatus, false);
    }
}

