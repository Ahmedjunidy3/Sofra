package com.example.sofra.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.sofra.data.api.RetrofitClient;
import com.example.sofra.data.model.generalsource.GeneralSource2;
import com.example.sofra.data.model.generalsource.GeneralSourceData;

import java.util.Objects;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileRepository {
    private static final String TAG = "ProfileRepository";
    private final Application application;
    public OnGetProfileDetailsListener onGetProfileDetailsListener;

    public ProfileRepository(Application application) {
        this.application = application;
    }

    //Rest
    public void sendRestProfileDetails(RequestBody email
            , RequestBody name, RequestBody phone, RequestBody regionId, RequestBody deliveryCost
            , RequestBody minimumCharger, RequestBody availability, MultipartBody.Part photo
            , RequestBody apiToken, RequestBody deliveryTime) {
        RetrofitClient.getClient().setProfileDetails(email, name, phone, regionId, deliveryCost
                , minimumCharger, availability, photo, apiToken, deliveryTime)
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

   // public void nameTvRecycItemOrderRest(String apiToken, String state) {
//        RetrofitClient.getClient().changeState(apiToken, state)
//                .enqueue(new Callback<GeneralSource2>() {
//                    @Override
//                    public void onResponse(@NonNull Call<GeneralSource2> call, @NonNull Response<GeneralSource2> response) {
//                        try {
//                            assert response.body() != null;
//                            if (response.body().getStatus() == 0) {
//                                Toast.makeText(application, response.body().getMsg()
//                                        , Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(@NonNull Call<GeneralSource2> call, @NonNull Throwable t) {
//                        Toast.makeText(application, "Failure Msg: " + t.getMessage()
//                                , Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//    }

    public void getRestAvailability(Integer restaurantId) {
        RetrofitClient.getClient().getAvailabilityState(restaurantId)
                .enqueue(new Callback<GeneralSource2>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource2> call, @NonNull Response<GeneralSource2> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                MutableLiveData<GeneralSourceData> mutableStateLiveData = new MutableLiveData<>();
                                mutableStateLiveData.setValue(response.body().getData());
                                onGetProfileDetailsListener.showRestAvailability(mutableStateLiveData);
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

    //Client
    public void getClientProfileDetails(String apiToken) {
        RetrofitClient.getClient().getClientProfileData(apiToken)
                .enqueue(new Callback<GeneralSource2>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource2> call, @NonNull Response<GeneralSource2> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                MutableLiveData<GeneralSourceData> clientDetailsMutableData = new MutableLiveData<>();
                                clientDetailsMutableData.setValue(response.body().getData());
                                onGetProfileDetailsListener.showClientProfileDetails(clientDetailsMutableData);
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

    public void sendClientProfileDetails(RequestBody apiToken, RequestBody name
            , RequestBody phone, RequestBody email, RequestBody regionId, MultipartBody.Part profileImage) {
        RetrofitClient.getClient().editClientProfile(apiToken, name, phone, email, regionId, profileImage)
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


    public interface OnGetProfileDetailsListener {
        void showRestAvailability(MutableLiveData<GeneralSourceData> mutableLiveData);

        void showClientProfileDetails(MutableLiveData<GeneralSourceData> mutableLiveData);
    }


}
