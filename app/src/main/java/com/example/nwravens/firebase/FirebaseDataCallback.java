package com.example.nwravens.firebase;

import com.example.nwravens.datamodels.Notifications;

public interface FirebaseDataCallback {
    void showData(Notifications notifications);

    void showError(Throwable error);

    void showProgress();

    void hideProgress();
}
