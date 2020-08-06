package com.example.sofra.repository;


import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.sofra.data.api.RetrofitClient;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.data.model.generalsource.GeneralSource;
import com.example.sofra.data.model.generalsource.GeneralSource2;
import com.example.sofra.data.model.generalsource.GeneralSource3;
import com.example.sofra.data.model.generalsource.GeneralSourceData;
import com.example.sofra.data.model.login.Login;


import com.example.sofra.view.clientCycle.activity.homeCycle.RestListActivity;
import com.example.sofra.view.resturantCycle.activity.homeCycle.RestFoodCategActivity;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private static final String TAG = "AuthRepository";
    private final Application application;
    public OnLoadListener onLoadListener;

    public AuthRepository(Application application) {
        this.application = application;
    }

    //Restaurant
    public void getLogin(String email, String password) {
        RetrofitClient.getClient().getLogin(email, password).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {
                try {
                    assert response.body() != null;
                    if (response.body().getStatus() == 1) {
                        Toast.makeText(application, response.body().getMsg()
                                , Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(application, RestFoodCategActivity.class);
                        application.startActivity(intent);
                        SharedPreferenceManager.saveRestApiToken(application
                                , response.body().getLoginData().getApiToken());
                        SharedPreferenceManager.saveRestId(application
                                , String.valueOf(response.body().getLoginData().getUser().getId()));
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

    public void setRestSignedUpData(RequestBody name, RequestBody email, RequestBody password, RequestBody passwordConfirm
            , RequestBody phone, RequestBody whatsApp, RequestBody regionId, RequestBody deliveryCost, RequestBody minCharger
            , MultipartBody.Part photo, RequestBody deliveryTime) {
        RetrofitClient.getClient().setSignedUpDetails(name, email, password, passwordConfirm
                , phone, whatsApp, regionId, deliveryCost, minCharger, photo, deliveryTime)
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
                        Toast.makeText(application, "Failure Msg: " + t.getMessage()
                                , Toast.LENGTH_SHORT).show();

                    }
                });

    }

    public void setRestDeviceToken(String token, String type, String apiToken) {
        RetrofitClient.getClient()
                .registerRestToken(token, type, apiToken).enqueue(new Callback<GeneralSource2>() {
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

    public void removeRestDeviceToken(String token, String apiToken) {
        RetrofitClient.getClient()
                .removeRestToken(token, apiToken).enqueue(new Callback<GeneralSource2>() {
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


    //Client
    public void checkClientLoginData(String email, String password) {
        RetrofitClient.getClient().checkClientLoginDetails(email, password).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {
                try {
                    assert response.body() != null;
                    if (response.body().getStatus() == 1) {
                        Toast.makeText(application, response.body().getMsg()
                                , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(application, RestListActivity.class);
                        intent.putExtra("CLIENT_NAME", response.body().getLoginData()
                                .getUser().getName());
                        intent.putExtra("CLIENT_PHONE", response.body().getLoginData()
                                .getUser().getPhone());
                        application.startActivity(intent);
                        SharedPreferenceManager.saveClientApiToken(application
                                , response.body().getLoginData().getApiToken());
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

    public void setClientSignedUpData(RequestBody name, RequestBody email, RequestBody password
            , RequestBody passwordConfirm, RequestBody phone, RequestBody regionId
            , MultipartBody.Part profileImage) {
        RetrofitClient.getClient().setClientSignedUpDetails(name, email, password, passwordConfirm
                , phone, regionId, profileImage)
                .enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                Toast.makeText(application, response.body().getMsg()
                                        , Toast.LENGTH_SHORT).show();
                                SharedPreferenceManager.setSharedPreferences(application);
                                SharedPreferenceManager.saveData(application, "CLIENT_API_TOKEN",
                                        response.body().getLoginData().getApiToken());
                                Intent intent = new Intent(application, RestListActivity.class);
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
                        Toast.makeText(application, "Failure Msg: " + t.getMessage()
                                , Toast.LENGTH_SHORT).show();

                    }
                });

    }


    public void setClientDeviceToken(String token, String type, String apiToken) {
        RetrofitClient.getClient()
                .registerClientToken(token, type, apiToken).enqueue(new Callback<GeneralSource2>() {
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

    public void removeClientDeviceToken(String token, String apiToken) {
        RetrofitClient.getClient()
                .removeClientToken(token, apiToken).enqueue(new Callback<GeneralSource2>() {
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


    public void getCities() {
        RetrofitClient.getClient().getCities().enqueue(new Callback<GeneralSource>() {
            @Override
            public void onResponse(@NonNull Call<GeneralSource> call, @NonNull Response<GeneralSource> response) {
                try {
                    assert response.body() != null;
                    if (response.body().getStatus() == 1) {
                        MutableLiveData<List<GeneralSourceData>> mutableCitiesLiveData = new MutableLiveData<>();
                        mutableCitiesLiveData.setValue(response.body().getData().getData());
                        onLoadListener.loadCities(mutableCitiesLiveData);
                    }
                } catch (Exception e) {
                            e.printStackTrace();
                        }
            }

            @Override
            public void onFailure(@NonNull Call<GeneralSource> call, @NonNull Throwable t) {
                Toast.makeText(application, "Failure Msg: " + t.getMessage()
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getRegions(String cityId) {
        RetrofitClient.getClient().getRegions(cityId).enqueue(new Callback<GeneralSource3>() {
            @Override
            public void onResponse(@NonNull Call<GeneralSource3> call, @NonNull Response<GeneralSource3> response) {
                try {
                    assert response.body() != null;
                    if (response.body().getStatus() == 1) {
                        MutableLiveData<List<GeneralSourceData>> mutableRegionsLiveData = new MutableLiveData<>();
                        mutableRegionsLiveData.setValue(response.body().getData());
                        onLoadListener.loadRegions(mutableRegionsLiveData);
                    }
                } catch (Exception e) {
                            e.printStackTrace();
                        }
            }

            @Override
            public void onFailure(@NonNull Call<GeneralSource3> call, @NonNull Throwable t) {
                Toast.makeText(application, "Failure Msg: " + t.getMessage()
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }

    public interface OnLoadListener {
        void loadCities(MutableLiveData<List<GeneralSourceData>> mutableCitiesLiveData);

        void loadRegions(MutableLiveData<List<GeneralSourceData>> mutableRegionsLiveData);
    }


}
