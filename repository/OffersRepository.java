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

public class OffersRepository {
    private static final String TAG = "RestOffersRepo";
    private final Application application;
    private final OnLoadOffersListener onLoadOffersListener;

    public OffersRepository(Application application, OnLoadOffersListener onLoadOffersListener) {
        this.application = application;
        this.onLoadOffersListener = onLoadOffersListener;
    }

    //Rest
    public void sendRestNewOfferDetails(RequestBody description, RequestBody price, RequestBody startingAt
            , RequestBody name, MultipartBody.Part photo, RequestBody endingAt, RequestBody offerPrice
            , RequestBody apiToken) {

        RetrofitClient.getClient().setNewOffer(description, price, startingAt, name
                , photo, endingAt, offerPrice, apiToken).enqueue(new Callback<GeneralSource2>() {
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

    public void getRestOffers(String apiToken) {
        RetrofitClient.getClient().getRestOffers(apiToken)
                .enqueue(new Callback<GeneralSource>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource> call, @NonNull Response<GeneralSource> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                MutableLiveData<List<GeneralSourceData>> offersMutableData = new MutableLiveData<>();
                                offersMutableData.setValue(response.body().getData().getData());
                                onLoadOffersListener.loadOffers(offersMutableData);
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

    public void updateRestOfferDetails(RequestBody description, RequestBody price, RequestBody startingAt
            , RequestBody name, MultipartBody.Part photo, RequestBody endingAt, RequestBody offerId
            , RequestBody apiToken) {
        RetrofitClient.getClient().updateOffer(description, price, startingAt, name, photo, endingAt
        , offerId, apiToken)
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

    public void deleteRestOffer(String apiToken, Integer itemId) {
        RetrofitClient.getClient().deleteOffer(apiToken, itemId)
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

    //Client
    public void getSelectedRestOffers(String restaurantId) {
        RetrofitClient.getClient().getSelectedRestOffers(restaurantId)
                .enqueue(new Callback<GeneralSource>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource> call, @NonNull Response<GeneralSource> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                MutableLiveData<List<GeneralSourceData>> offersMutableData = new MutableLiveData<>();
                                offersMutableData.setValue(response.body().getData().getData());
                                onLoadOffersListener.loadOffers(offersMutableData);
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

    public void getOfferDetails(Integer offerId) {
        RetrofitClient.getClient().getSelectedOfferDetails(offerId)
                .enqueue(new Callback<GeneralSource2>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource2> call, @NonNull Response<GeneralSource2> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                MutableLiveData<GeneralSourceData> offerMutableData = new MutableLiveData<>();
                                offerMutableData.setValue(response.body().getData());
                                onLoadOffersListener.loadOffer(offerMutableData);
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

    public interface OnLoadOffersListener {
        void loadOffers(MutableLiveData<List<GeneralSourceData>> mutableLiveData);
        void loadOffer(MutableLiveData<GeneralSourceData> mutableLiveData);
    }


}

