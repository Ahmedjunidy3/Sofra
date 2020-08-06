package com.example.sofra.view.resturantCycle.viewmodel.homeCycle;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.sofra.data.model.generalsource.GeneralSourceData;
import com.example.sofra.repository.OrdersRepository;

import java.util.List;

public class OrdersViewModel extends AndroidViewModel
        implements OrdersRepository.OnGetOrdersListener {
    private OrdersRepository ordersRepo;
    private MutableLiveData<List<GeneralSourceData>> ordersMutableData = new MutableLiveData<>();
    private MutableLiveData<GeneralSourceData> selectedOrderMutableData = new MutableLiveData<>();
    private MutableLiveData<List<GeneralSourceData>> payMehtodMutableData = new MutableLiveData<>();
    private MutableLiveData<String> msgMutableData = new MutableLiveData<>();

    public OrdersViewModel(@NonNull Application application) {
        super(application);
        ordersRepo = new OrdersRepository(application, this);
    }

    //Rest
    public LiveData<List<GeneralSourceData>> getMyOrdersRest(String apiToken, String state) {
        initRestOrders(apiToken, state);
        return ordersMutableData;
    }

    private void initRestOrders(String apiToken, String state) {
        ordersRepo.getRestOrders(apiToken, state);
    }

    public MutableLiveData<GeneralSourceData> showCustomerOrder(String apiToken, Integer orderId) {
        getFromApiOrderDetails(apiToken, orderId);
        return selectedOrderMutableData;
    }

    private void getFromApiOrderDetails(String apiToken, Integer orderId) {
        ordersRepo.showRestOrder(apiToken, orderId);
    }

    public void acceptCustomerOrder(String apiToken, Integer orderId) {
        sendApiOrderAcceptStatus(apiToken, orderId);
    }

    private void sendApiOrderAcceptStatus(String apiToken, Integer orderId) {
        ordersRepo.restAcceptOrder(apiToken, orderId);
    }

    public void rejectCustomerOrder(String apiToken, Integer orderId, String refuseReason) {
        sendApiOrderRejectStatus(apiToken, orderId, refuseReason);
    }

    private void sendApiOrderRejectStatus(String apiToken, Integer orderId, String refuseReason) {
        ordersRepo.restRejectOrder(apiToken, orderId, refuseReason);
    }

    public void confirmCustomerOrderDelivery(String apiToken, Integer orderId) {
        sendApiOrderConfirmStatus(apiToken, orderId);
    }

    private void sendApiOrderConfirmStatus(String apiToken, Integer orderId) {
        ordersRepo.restConfirmOrderDelivery(apiToken, orderId);
    }

    public LiveData<List<GeneralSourceData>> getPayingMethod(String apiToken) {
        getFromApiPaymentMethods(apiToken);
        return payMehtodMutableData;
    }

    private void getFromApiPaymentMethods(String apiToken) {
        ordersRepo.getCustomerPayingMethod(apiToken);
    }

    //Client
    public void setMyNewOrder(String restaurantId, String note, String address,
                              String paymentMethodId, String phone, String name, String apiToken
            , Integer item0, String quantities0, String notes0, Integer item1, String quantities1
            , String notes1, Integer item2, String quantities2, String notes2) {
        sendApiClientNewOrder(restaurantId, note, address, paymentMethodId, phone, name, apiToken
                , item0, quantities0, notes0, item1, quantities1, notes1, item2, quantities2, notes2);
    }

    private void sendApiClientNewOrder(String restaurantId, String note, String address,
                                       String paymentMethodId, String phone, String name, String apiToken
            , Integer item0, String quantities0, String notes0, Integer item1, String quantities1
            , String notes1, Integer item2, String quantities2, String notes2) {
        ordersRepo.setClientNewOrder(restaurantId, note, address, paymentMethodId, phone, name
                , apiToken, item0, quantities0, notes0, item1, quantities1, notes1, item2, quantities2
                , notes2);
    }

    public LiveData<List<GeneralSourceData>> getMyOrdersClient(String apiToken, String state) {
        initClientOrders(apiToken, state);
        return ordersMutableData;
    }

    private void initClientOrders(String apiToken, String state) {
        ordersRepo.getClientOrders(apiToken, state);
    }

    public MutableLiveData<GeneralSourceData> showMyOrderClient(String apiToken, Integer orderId) {
        getFromApiClientOrderDetails(apiToken, orderId);
        return selectedOrderMutableData;
    }

    private void getFromApiClientOrderDetails(String apiToken, Integer orderId) {
        ordersRepo.showClientOrderDetails(apiToken, orderId);
    }

    public void declineMyOrder(String apiToken, Integer orderId) {
        sendApiClientDecliningOrderStatus(apiToken, orderId);
    }

    private void sendApiClientDecliningOrderStatus(String apiToken, Integer orderId) {
        ordersRepo.clientDeclineOrder(apiToken, orderId);
    }

    public void confirmMyOrderDeliveryClient(String apiToken, Integer orderId) {
        sendApiClientConfirmOrderDeliveryStatus(apiToken, orderId);
    }

    private void sendApiClientConfirmOrderDeliveryStatus(String apiToken, Integer orderId) {
        ordersRepo.clientConfirmOrderDelivery(apiToken, orderId);
    }

    public LiveData<String> checkNewOrderSuccessMsg() {
        return msgMutableData;
    }

    @Override
    public void loadOrders(MutableLiveData<List<GeneralSourceData>> mutableLiveData) {
        ordersMutableData.setValue(mutableLiveData.getValue());
    }

    @Override
    public void loadSelectedOrder(MutableLiveData<GeneralSourceData> mutableLiveData) {
        selectedOrderMutableData.setValue(mutableLiveData.getValue());
    }

    @Override
    public void loadPaymentMethod(MutableLiveData<List<GeneralSourceData>> mutableLiveData) {
        payMehtodMutableData.setValue(mutableLiveData.getValue());
    }

    @Override
    public void sendNewOrderSuccessMsg(MutableLiveData<String> mutableLiveData) {
        msgMutableData.setValue(mutableLiveData.getValue());
    }


}
