package com.example.nwravens.provider;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

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


    public static void setRecyclerViewDivider(RecyclerView recyclerView) {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                RecyclerView.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

}
