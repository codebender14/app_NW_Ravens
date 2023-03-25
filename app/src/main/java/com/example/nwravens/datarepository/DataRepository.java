package com.example.nwravens.datarepository;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.ContactsContract;

import com.example.nwravens.firebase.FirebaseDataCallback;
import com.example.nwravens.firebase.FirebaseDataReader;

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
        //  sharedPreferences.edit().putBoolean()
    }

}
