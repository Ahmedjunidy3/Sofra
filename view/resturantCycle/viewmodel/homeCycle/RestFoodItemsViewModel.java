package com.example.sofra.view.resturantCycle.viewmodel.homeCycle;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.sofra.data.model.generalsource.GeneralSourceData;
import com.example.sofra.repository.RestFoodItemsRepository;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RestFoodItemsViewModel extends AndroidViewModel
        implements RestFoodItemsRepository.OnGetFoodItemsMutableData {
    private RestFoodItemsRepository restFoodItemsRepo;
    private MutableLiveData<List<GeneralSourceData>> myFoodItemsMutableData = new MutableLiveData<>();

    public RestFoodItemsViewModel(@NonNull Application application) {
        super(application);
        restFoodItemsRepo = new RestFoodItemsRepository(application, this);
    }

    public void setRestNewFoodItem(RequestBody description, RequestBody price
            , RequestBody name, MultipartBody.Part photo, RequestBody apiToken
            , RequestBody offerPrice, RequestBody categoryId) {
        sendApiNewFoodItemDetails(description, price, name, photo, apiToken
                , offerPrice, categoryId);
    }

    private void sendApiNewFoodItemDetails(RequestBody description, RequestBody price
            , RequestBody name, MultipartBody.Part photo, RequestBody apiToken
            , RequestBody offerPrice, RequestBody categoryId) {
        restFoodItemsRepo.sendNewFoodItemDetails(description, price, name, photo, apiToken
                , offerPrice, categoryId);
    }

    public LiveData<List<GeneralSourceData>> getMyFoodItems(String apiToken, String categoryId) {
        init(apiToken, categoryId);
        return myFoodItemsMutableData;
    }

    private void init(String apiToken, String categoryId) {
        restFoodItemsRepo.getRestFoodItems(apiToken, categoryId);
    }

    public LiveData<List<GeneralSourceData>> getSelectedCategFoodItems(String restaurantId, String categoryId) {
        initSelectedCateg(restaurantId, categoryId);
        return myFoodItemsMutableData;
    }

    private void initSelectedCateg(String restaurantId, String categoryId) {
        restFoodItemsRepo.getFoodItemsByCategId(restaurantId, categoryId);
    }

    public void UpdatedRestFoodItemData(RequestBody description, RequestBody price
            , RequestBody name, MultipartBody.Part photo, RequestBody apiToken
            , RequestBody offerPrice, RequestBody itemId, RequestBody categoryId) {
        sendApiUpdatedRestFoodItemDetails(description, price, name, photo, apiToken
                , offerPrice, itemId, categoryId);
    }

    private void sendApiUpdatedRestFoodItemDetails(RequestBody description, RequestBody price
            , RequestBody name, MultipartBody.Part photo, RequestBody apiToken
            , RequestBody offerPrice, RequestBody itemId, RequestBody categoryId) {
        restFoodItemsRepo.updateRestFoodItemDetails(description, price, name, photo, apiToken
                , offerPrice, itemId, categoryId);
    }

    public void deleteOfferRest(String apiToken, Integer itemId) {
        deletedRestFoodItemFromApi(apiToken, itemId);
    }

    private void deletedRestFoodItemFromApi(String apiToken, Integer itemId) {
        restFoodItemsRepo.deleteRestFoodItem(apiToken, itemId);
    }

    @Override
    public void showFoodItems(MutableLiveData<List<GeneralSourceData>> mutableLiveData) {
        myFoodItemsMutableData.setValue(mutableLiveData.getValue());
    }

}
