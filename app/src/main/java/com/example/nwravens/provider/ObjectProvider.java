package com.example.nwravens.provider;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ProgressBar;

import com.example.nwravens.datarepository.DataRepository;
import com.example.nwravens.firebase.FirebaseDataReader;

public class ObjectProvider {

    public static DataRepository getDataRepo(Context context) {
        return new DataRepository(context, new FirebaseDataReader());
    }

    public static ProgressDialog getProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading");
        return progressDialog;
    }

}
