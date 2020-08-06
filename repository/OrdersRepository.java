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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdersRepository {
    private static final String TAG = "OrdersRepository";
    private final Application application;
    private final OnGetOrdersListener onGetOrdersListener;

    public OrdersRepository(Application application, OnGetOrdersListener onGetOrdersListener) {
        this.application = application;
        this.onGetOrdersListener = onGetOrdersListener;
    }

    //Rest
    public void getRestOrders(String apiToken, String state) {
        RetrofitClient.getClient().getRestOrders(apiToken, state)
                .enqueue(new Callback<GeneralSource>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource> call, @NonNull Response<GeneralSource> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                MutableLiveData<List<GeneralSourceData>> myOrdersMutableData = new MutableLiveData<>();
                                myOrdersMutableData.setValue(response.body().getData().getData());
                                onGetOrdersListener.loadOrders(myOrdersMutableData);
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

    public void showRestOrder(String apiToken, Integer orderId) {
        RetrofitClient.getClient().showRestOrder(apiToken, orderId)
                .enqueue(new Callback<GeneralSource2>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource2> call, @NonNull Response<GeneralSource2> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                MutableLiveData<GeneralSourceData> selectedOrderMutableData = new MutableLiveData<>();
                                selectedOrderMutableData.setValue(response.body().getData());
                                onGetOrdersListener.loadSelectedOrder(selectedOrderMutableData);
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

    public void restAcceptOrder(String apiToken, Integer orderId) {
        RetrofitClient.getClient().restAcceptOrder(apiToken, orderId)
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

    public void restRejectOrder(String apiToken, Integer orderId, String refuseReason) {
        RetrofitClient.getClient().restRejectOrder(apiToken, orderId, refuseReason)
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

    public void restConfirmOrderDelivery(String apiToken, Integer orderId) {
        RetrofitClient.getClient().restConfirmOrderDelivery(apiToken, orderId)
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

    public void getCustomerPayingMethod(String apiToken) {
        RetrofitClient.getClient().getPaymentMethod(apiToken)
                .enqueue(new Callback<GeneralSource3>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource3> call, @NonNull Response<GeneralSource3> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                MutableLiveData<List<GeneralSourceData>> payMethodMutableData = new MutableLiveData<>();
                                payMethodMutableData.setValue(response.body().getData());
                                onGetOrdersListener.loadPaymentMethod(payMethodMutableData);
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
                    }
                });
    }

    //Client
    public void setClientNewOrder(String restaurantId, String note, String address,
                                  String paymentMethodId, String phone, String name, String apiToken
            , Integer item0, String quantities0, String notes0, Integer item1, String quantities1
            , String notes1, Integer item2, String quantities2, String notes2) {
        RetrofitClient.getClient().setClientNewOrder(restaurantId, note, address, paymentMethodId
                , phone, name, apiToken, item0, quantities0, notes0, item1, quantities1, notes1
                , item2, quantities2, notes2)
                .enqueue(new Callback<GeneralSource2>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource2> call, @NonNull Response<GeneralSource2> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                Toast.makeText(application, response.body().getMsg()
                                        , Toast.LENGTH_SHORT).show();
                                MutableLiveData<String> msgMutableData = new MutableLiveData<>();
                                msgMutableData.setValue(response.body().getMsg());
                                onGetOrdersListener.sendNewOrderSuccessMsg(msgMutableData);
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

    public void getClientOrders(String apiToken, String state) {
        RetrofitClient.getClient().getClientOrders(apiToken, state)
                .enqueue(new Callback<GeneralSource>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource> call, @NonNull Response<GeneralSource> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                MutableLiveData<List<GeneralSourceData>> myOrdersMutableData = new MutableLiveData<>();
                                myOrdersMutableData.setValue(response.body().getData().getData());
                                onGetOrdersListener.loadOrders(myOrdersMutableData);
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

    public void showClientOrderDetails(String apiToken, Integer orderId) {
        RetrofitClient.getClient().showClientOrderDetails(apiToken, orderId)
                .enqueue(new Callback<GeneralSource2>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource2> call, @NonNull Response<GeneralSource2> response) {
                        try {
                            assert response.body() != null;
                            if (response.body().getStatus() == 1) {
                                MutableLiveData<GeneralSourceData> selectedOrderMutableData = new MutableLiveData<>();
                                selectedOrderMutableData.setValue(response.body().getData());
                                onGetOrdersListener.loadSelectedOrder(selectedOrderMutableData);
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

    public void clientDeclineOrder(String apiToken, Integer orderId) {
        RetrofitClient.getClient().setClientDeclineOrder(apiToken, orderId)
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

    public void clientConfirmOrderDelivery(String apiToken, Integer orderId) {
        RetrofitClient.getClient().setClientOrderDeliveryConfirm(apiToken, orderId)
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

    public interface OnGetOrdersListener {
        void loadOrders(MutableLiveData<List<GeneralSourceData>> mutableLiveData);

        void loadSelectedOrder(MutableLiveData<GeneralSourceData> mutableLiveData);

        void loadPaymentMethod(MutableLiveData<List<GeneralSourceData>> mutableLiveData);

        void sendNewOrderSuccessMsg(MutableLiveData<String> mutableLiveData);
    }


}
