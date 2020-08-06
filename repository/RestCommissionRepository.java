package com.example.sofra.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;


import com.example.sofra.data.api.RetrofitClient;
import com.example.sofra.data.model.generalsource.GeneralSource2;
import com.example.sofra.data.model.generalsource.GeneralSourceData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RestCommissionRepository {
    private static final String TAG = "RestCommissionRepo";
    private final Application application;
    private final OnGetCommissionListener onGetCommissionListener;

    public RestCommissionRepository(Application application, OnGetCommissionListener onGetCommissionListener) {
        this.application = application;
        this.onGetCommissionListener = onGetCommissionListener;
    }

    public void getCommission(String apiToken) {
        RetrofitClient.getClient().getRestCommission(apiToken)
                .enqueue(new Callback<GeneralSource2>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource2> call, @NonNull Response<GeneralSource2> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                MutableLiveData<GeneralSourceData> commissionMutableData = new MutableLiveData<>();
                                commissionMutableData.setValue(response.body().getData());
                                onGetCommissionListener.showCommission(commissionMutableData);
                            } else {
                                Toast.makeText(application, response.body().getMsg()
                                        , Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GeneralSource2> call, @NonNull Throwable t) {
                        Toast.makeText(application, "Failure Mag: " + t.getMessage()
                                , Toast.LENGTH_SHORT).show();
//                (TAG, "Failure Msg: " + t.getMessage());
                    }
                });

    }


    public interface OnGetCommissionListener {
        void showCommission(MutableLiveData<GeneralSourceData> mutableLiveData);
    }


}
