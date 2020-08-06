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

public class RestListRepository {
    private static final String TAG = "RestaurantsListRepos";
    private final Application application;
    public OnGetRestInfoListener onGetRestInfoListener;

    public RestListRepository(Application application) {
        this.application = application;
    }

    public void getSelectedRestInfo(Integer restaurantId) {
        RetrofitClient.getClient().getRestInfo(restaurantId)
                .enqueue(new Callback<GeneralSource2>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource2> call, @NonNull Response<GeneralSource2> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                MutableLiveData<GeneralSourceData> mutableRestaurantsData = new MutableLiveData<>();
                                mutableRestaurantsData.setValue(response.body().getData());
                                onGetRestInfoListener.showRestInfo(mutableRestaurantsData);
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
                    }
                });
    }

    public interface OnGetRestInfoListener {
        void showRestInfo(MutableLiveData<GeneralSourceData> mutableLiveData);
    }

}


