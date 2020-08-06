package com.example.sofra.view.resturantCycle.viewmodel.homeCycle;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sofra.data.model.generalsource.GeneralSourceData;
import com.example.sofra.repository.RestFoodCategRepository;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RestFoodCategViewModel extends AndroidViewModel implements RestFoodCategRepository.OnInitRestCategoriesListener {
    private RestFoodCategRepository restFoodCategRepo;
    private MutableLiveData<List<GeneralSourceData>> mutableCategData = new MutableLiveData<>();

    public RestFoodCategViewModel(@NonNull Application application) {
        super(application);
        restFoodCategRepo = new RestFoodCategRepository(application, this);
    }

    public void setRestNewFoodCateg(RequestBody name, MultipartBody.Part photo, RequestBody apiToken) {
        sendApiNewFoodCategDetails(name, photo, apiToken);
    }

    private void sendApiNewFoodCategDetails(RequestBody name, MultipartBody.Part photo, RequestBody apiToken) {
        restFoodCategRepo.sendNewFoodCategDetails(name, photo, apiToken);
    }

    public LiveData<List<GeneralSourceData>> getMyCategories(String apiToken) {
        init(apiToken);
        return mutableCategData;
    }

    private void init(String apiToken) {
        restFoodCategRepo.getCategoriesFromApi(apiToken);
    }

    public LiveData<List<GeneralSourceData>> getSelectedRestCategForClient(String restaurantId) {
        initCategForClient(restaurantId);
        return mutableCategData;
    }

    private void initCategForClient(String restaurantId) {
        restFoodCategRepo.getSelectedRestCategories(restaurantId);
    }

    @Override
    public void showCategoriesDetails(MutableLiveData<List<GeneralSourceData>> myCategMutableData) {
     mutableCategData.setValue(myCategMutableData.getValue());
    }

    public void UpdatedRestFoodCategData(RequestBody foodTypeReqBody, MultipartBody.Part photoReqBody
            , RequestBody apiTokenReqBody, RequestBody categId) {
        sendApiUpdatedRestFoodCategDetails(foodTypeReqBody, photoReqBody, apiTokenReqBody, categId);
    }

    private void sendApiUpdatedRestFoodCategDetails(RequestBody foodTypeReqBody
            , MultipartBody.Part photoReqBody, RequestBody apiTokenReqBody, RequestBody categId) {
    restFoodCategRepo.updateRestFoodCategDetails(foodTypeReqBody, photoReqBody, apiTokenReqBody, categId);
    }

    public void deleteOfferRest(String apiToken, Integer categId) {
        deletedRestCategFromApi(apiToken, categId);
    }

    private void deletedRestCategFromApi(String apiToken, Integer categId) {
     restFoodCategRepo.deleteCategory(apiToken, categId);
    }


}
