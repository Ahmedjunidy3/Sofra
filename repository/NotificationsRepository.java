package com.example.sofra.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;


import com.example.sofra.data.api.RetrofitClient;
import com.example.sofra.data.model.generalsource.GeneralSource;
import com.example.sofra.data.model.generalsource.GeneralSourceData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsRepository {
    private static final String TAG = "NotificationsRepo";
    private final Application application;
    private final OnGetNotificationsListener onGetNotificationsListener;

    public NotificationsRepository(Application application
            , OnGetNotificationsListener onGetNotificationsListener) {
        this.application = application;
        this.onGetNotificationsListener = onGetNotificationsListener;
    }

    //Rest
    public void getRestNotifications(String apiToken) {
        RetrofitClient.getClient().getRestNotifications(apiToken)
                .enqueue(new Callback<GeneralSource>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource> call, @NonNull Response<GeneralSource> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                MutableLiveData<List<GeneralSourceData>> mutableNotificationData = new MutableLiveData<>();
                                mutableNotificationData.setValue(response.body().getData().getData());
                                onGetNotificationsListener.showNotifications(mutableNotificationData);
                            } else {
                                Toast.makeText(application, response.body().getMsg()
                                        , Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GeneralSource> call, @NonNull Throwable t) {
                        Toast.makeText(application, "Failure Mag: " + t.getMessage()
                                , Toast.LENGTH_SHORT).show();
                    }
                });

    }
    
    //Client
    public void getClientNotifications(String apiToken) {
        RetrofitClient.getClient().getClientNotifications(apiToken)
                .enqueue(new Callback<GeneralSource>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource> call, @NonNull Response<GeneralSource> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                MutableLiveData<List<GeneralSourceData>> mutableNotificationData = new MutableLiveData<>();
                                mutableNotificationData.setValue(response.body().getData().getData());
                                onGetNotificationsListener.showNotifications(mutableNotificationData);
                            } else {
                                Toast.makeText(application, response.body().getMsg()
                                        , Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GeneralSource> call, @NonNull Throwable t) {
                        Toast.makeText(application, "Failure Mag: " + t.getMessage()
                                , Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public interface OnGetNotificationsListener {
        void showNotifications(MutableLiveData<List<GeneralSourceData>> mutableLiveData);
    }

}
