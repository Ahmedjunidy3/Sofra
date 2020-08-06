package com.example.sofra.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;


import com.example.sofra.data.api.RetrofitClient;
import com.example.sofra.data.model.generalsource.GeneralSource;
import com.example.sofra.data.model.generalsource.GeneralSource2;
import com.example.sofra.data.model.generalsource.GeneralSource3;
import com.example.sofra.data.model.generalsource.GeneralSourceData;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestFoodCategRepository {
    private static final String TAG = "RestFoodCategRepository";
    private final Application application;
    private final OnInitRestCategoriesListener onInitRestCategoriesListener;


    public RestFoodCategRepository(Application application
            , OnInitRestCategoriesListener onInitRestCategoriesListener) {
        this.application = application;
        this.onInitRestCategoriesListener = onInitRestCategoriesListener;
    }

    public void sendNewFoodCategDetails(RequestBody name, MultipartBody.Part photo, RequestBody apiToken) {
        RetrofitClient.getClient().setNewCategory(name, photo, apiToken)
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
                        Toast.makeText(application, "Failure Msg: " + t.getMessage()
                                , Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void getCategoriesFromApi(String apiToken) {
        RetrofitClient.getClient().getRestCategories(apiToken)
                .enqueue(new Callback<GeneralSource>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource> call, @NonNull Response<GeneralSource> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                MutableLiveData<List<GeneralSourceData>> myCategMutableData = new MutableLiveData<>();
                                myCategMutableData.setValue(response.body().getData().getData());
                                onInitRestCategoriesListener.showCategoriesDetails(myCategMutableData);
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
//                (TAG, "Failure Msg: " + t.getMessage());
                    }
                });

    }

    public void getSelectedRestCategories(String restaurantId) {
        RetrofitClient.getClient().getSelectedRestCategories(restaurantId)
                .enqueue(new Callback<GeneralSource3>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource3> call, @NonNull Response<GeneralSource3> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                MutableLiveData<List<GeneralSourceData>> mutableCategoriesData = new MutableLiveData<>();
                                mutableCategoriesData.setValue(response.body().getData());
//                                Toast.makeText(application, "List Size: " +
//                                        mutableCategoriesData.getValue().size(), Toast.LENGTH_SHORT).show();
                                onInitRestCategoriesListener.showCategoriesDetails(mutableCategoriesData);
                            } else {
                                Toast.makeText(application, response.body().getMsg()
                                        , Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GeneralSource3> call, @NonNull Throwable t) {
                        Toast.makeText(application, "Failure Mag: " + t.getMessage()
                                , Toast.LENGTH_SHORT).show();
//                (TAG, "Failure Msg: " + t.getMessage());
                    }
                });

    }

    public void updateRestFoodCategDetails(RequestBody foodTypeReqBody, MultipartBody.Part photoReqBody
            , RequestBody apiTokenReqBody, RequestBody categId) {
        RetrofitClient.getClient().updateCategory(foodTypeReqBody, photoReqBody, apiTokenReqBody, categId)
                .enqueue(new Callback<GeneralSource>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource> call, @NonNull Response<GeneralSource> response) {
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
                    public void onFailure(@NonNull Call<GeneralSource> call, @NonNull Throwable t) {
                        Toast.makeText(application, "Failure Mag: " + t.getMessage()
                                , Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void deleteCategory(String apiToken, Integer categId) {
        RetrofitClient.getClient().deleteCategory(apiToken, categId)
                .enqueue(new Callback<GeneralSource>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource> call, @NonNull Response<GeneralSource> response) {
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
                    public void onFailure(@NonNull Call<GeneralSource> call, @NonNull Throwable t) {
                        Toast.makeText(application, "Failure Mag: " + t.getMessage()
                                , Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public interface OnInitRestCategoriesListener {
        void showCategoriesDetails(MutableLiveData<List<GeneralSourceData>> myCategMutableData);
    }


}