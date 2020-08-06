package com.example.sofra.repository;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;


import com.example.sofra.data.api.RetrofitClient;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.data.model.generalsource.GeneralSource;
import com.example.sofra.data.model.generalsource.GeneralSource2;
import com.example.sofra.data.model.generalsource.GeneralSourceData;
import com.example.sofra.data.model.login.Login;
import com.example.sofra.view.resturantCycle.activity.userCycle.LoginActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoreRepository {
    private static final String TAG = "MoreRepository";
    private final Application application;
    private final OnGetCustomersReviewsListener onGetCustomersReviewsListener;

    public MoreRepository(Application application
            , OnGetCustomersReviewsListener onGetCustomersReviewsListener) {
        this.application = application;
        this.onGetCustomersReviewsListener = onGetCustomersReviewsListener;

    }

    public void setContactDetails(String name, String email, String phone, String type, String content) {
        RetrofitClient.getClient().contactManagement(name, email, phone, type, content)
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
                        Toast.makeText(application, t.getMessage()
                                , Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //Rest-ChangePassword
    public void setRestChangedPassDetails(String apiToken, String oldPass, String newPass, String newPassConfirm) {
        RetrofitClient.getClient().setRestChangedPassword(apiToken, oldPass, newPass, newPassConfirm)
                .enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                Toast.makeText(application, response.body().getMsg()
                                        , Toast.LENGTH_SHORT).show();
                                SharedPreferenceManager.removeRestEmail(application);
                                SharedPreferenceManager.removeRestPassword(application);
                                Intent intent = new Intent(application, LoginActivity.class);
                                application.startActivity(intent);
                            } else {
                                Toast.makeText(application, response.body().getMsg()
                                        , Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Login> call, @NonNull Throwable t) {
                        Toast.makeText(application, t.getMessage()
                                , Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //Rest-Reviews
    public void getRestReviews(String apiToken, Integer restaurantId) {
        RetrofitClient.getClient().getMyReviews(apiToken, restaurantId)
                .enqueue(new Callback<GeneralSource>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource> call, @NonNull Response<GeneralSource> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                MutableLiveData<List<GeneralSourceData>> mutableReviewsLiveData = new MutableLiveData<>();
                                mutableReviewsLiveData.setValue(response.body().getData().getData());
                                onGetCustomersReviewsListener.showReviews(mutableReviewsLiveData);
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
                        Toast.makeText(application, t.getMessage()
                                , Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //Client-ChangePassword
    public void setClientChangedPassDetails(String apiToken, String oldPass, String newPass, String newPassConfirm) {
        RetrofitClient.getClient().setClientChangedPassword(apiToken, oldPass, newPass, newPassConfirm)
                .enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                Toast.makeText(application, response.body().getMsg()
                                        , Toast.LENGTH_SHORT).show();
                                SharedPreferenceManager.removeClientEmail(application);
                                SharedPreferenceManager.removeClientPassword(application);
                            } else {
                                Toast.makeText(application, response.body().getMsg()
                                        , Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Login> call, @NonNull Throwable t) {
                        Toast.makeText(application, t.getMessage()
                                , Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //Client-Review
    public void sendClientReview(String rate, String comment, String restaurantId, String apiToken) {
        RetrofitClient.getClient().setClientReview(rate, comment, restaurantId, apiToken)
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
                        Toast.makeText(application, t.getMessage()
                                , Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public interface OnGetCustomersReviewsListener {
        void showReviews(MutableLiveData<List<GeneralSourceData>> mutableLiveData);
    }


}
