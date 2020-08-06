package com.example.sofra.repository;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;


import com.example.sofra.data.api.RetrofitClient;
import com.example.sofra.data.model.login.Login;
import com.example.sofra.view.resturantCycle.activity.homeCycle.RestFoodCategActivity;
import com.example.sofra.view.resturantCycle.activity.userCycle.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPassRepository {
    private static final String TAG = "ForgetPassRepository";
    private final Application application;
    private final OnResetEmailResponseListener onResetEmailResponseListener;

    public ForgetPassRepository(Application application
            , OnResetEmailResponseListener onResetEmailResponseListener) {
        this.application = application;
        this.onResetEmailResponseListener = onResetEmailResponseListener;
    }

    //Resturant
    public void checkRestResetPassEmail(String email) {
        RetrofitClient.getClient().setRestResetPassword(email)
                .enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                MutableLiveData<String> mutableLiveData = new MutableLiveData<>();
                                Toast.makeText(application, response.body().getMsg()
                                        , Toast.LENGTH_SHORT).show();
                                mutableLiveData.setValue(response.body().getMsg());
                                onResetEmailResponseListener.onResponse(mutableLiveData);
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

    public void checkRestNewPassDetails(String pinCode, String newPass, String newPassConfirm) {
        RetrofitClient.getClient().setRestNewPassword(pinCode, newPass, newPassConfirm)
                .enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                Toast.makeText(application, response.body().getMsg()
                                        , Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(application, RestFoodCategActivity.class);
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

    //Client
    public void checkClientResetPassEmail(String email) {
        RetrofitClient.getClient().setClientResetPassword(email)
                .enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                MutableLiveData<String> mutableLiveData = new MutableLiveData<>();
                                Toast.makeText(application, response.body().getMsg()
                                        , Toast.LENGTH_SHORT).show();
                                mutableLiveData.setValue(response.body().getMsg());
                                onResetEmailResponseListener.onResponse(mutableLiveData);
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

    public void checkClientNewPassDetails(String pinCode, String newPass, String newPassConfirm) {
        RetrofitClient.getClient().setClientNewPassword(pinCode, newPass, newPassConfirm)
                .enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                Toast.makeText(application, response.body().getMsg()
                                        , Toast.LENGTH_SHORT).show();
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

    public interface OnResetEmailResponseListener {
        void onResponse(MutableLiveData<String> msg);
    }


}
