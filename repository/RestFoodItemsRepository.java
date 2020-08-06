package com.example.sofra.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;


import com.example.sofra.data.api.RetrofitClient;
import com.example.sofra.data.model.generalsource.GeneralSource;
import com.example.sofra.data.model.generalsource.GeneralSource2;
import com.example.sofra.data.model.generalsource.GeneralSourceData;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestFoodItemsRepository {
    private static final String TAG = "RestFoodItemsRepository";
    private final Application application;
    private final OnGetFoodItemsMutableData onGetFoodItemsMutableData;

    public RestFoodItemsRepository(Application application
            , OnGetFoodItemsMutableData onGetFoodItemsMutableData) {
        this.application = application;
        this.onGetFoodItemsMutableData = onGetFoodItemsMutableData;
    }

    public void sendNewFoodItemDetails(RequestBody description, RequestBody price
            , RequestBody name
            , MultipartBody.Part photo, RequestBody apiToken
            , RequestBody offerPrice, RequestBody categoryId) {

        RetrofitClient.getClient().setRestNewFoodItem(description, price, name, photo, apiToken
                , offerPrice, categoryId).enqueue(new Callback<GeneralSource2>() {
            @Override
            public void onResponse(@NonNull Call<GeneralSource2> call, @NonNull Response<GeneralSource2> response) {
                try {
                    assert response.body() != null;
                    if (response.body().getStatus() == 1) {
                        Toast.makeText(application, response.body().getMsg()
                                , Toast.LENGTH_SHORT).show();
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
                Toast.makeText(application, "Failure Msg: " + t.getMessage()
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getRestFoodItems(String apiToken, String categoryId) {
        RetrofitClient.getClient().getRestFoodItems(apiToken, categoryId)
                .enqueue(new Callback<GeneralSource>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource> call, @NonNull Response<GeneralSource> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                MutableLiveData<List<GeneralSourceData>> myFoodItemsMutableData = new MutableLiveData<>();
                                myFoodItemsMutableData.setValue(response.body().getData().getData());
                                onGetFoodItemsMutableData.showFoodItems(myFoodItemsMutableData);
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

    public void getFoodItemsByCategId(String restaurantId, String categoryId) {
        RetrofitClient.getClient().getSelectedCategFoodItems(restaurantId, categoryId)
                .enqueue(new Callback<GeneralSource>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource> call, @NonNull Response<GeneralSource> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                MutableLiveData<List<GeneralSourceData>> mutableSelectedFoodItemsData = new MutableLiveData<>();
                                mutableSelectedFoodItemsData.setValue(response.body().getData().getData());
                                onGetFoodItemsMutableData.showFoodItems(mutableSelectedFoodItemsData);
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

    public void updateRestFoodItemDetails(RequestBody description, RequestBody price
            , RequestBody name, MultipartBody.Part photo, RequestBody apiToken
            , RequestBody offerPrice, RequestBody itemId, RequestBody categoryId) {
        RetrofitClient.getClient().updateRestFoodItem(description, price, name, photo, apiToken
                , offerPrice, itemId, categoryId)
                .enqueue(new Callback<GeneralSource2>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource2> call, @NonNull Response<GeneralSource2> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                Toast.makeText(application, response.body().getMsg()
                                        , Toast.LENGTH_SHORT).show();
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

    public void deleteRestFoodItem(String apiToken, Integer itemId) {
        RetrofitClient.getClient().deleteRestFoodItem(apiToken, itemId)
                .enqueue(new Callback<GeneralSource2>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource2> call, @NonNull Response<GeneralSource2> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                Toast.makeText(application, response.body().getMsg()
                                        , Toast.LENGTH_SHORT).show();
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

    public interface OnGetFoodItemsMutableData {
        void showFoodItems(MutableLiveData<List<GeneralSourceData>> mutableLiveData);
    }


}