package com.example.sofra.view.resturantCycle.viewmodel.homeCycle;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sofra.data.model.generalsource.GeneralSourceData;
import com.example.sofra.repository.ProfileRepository;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ProfileViewModel extends AndroidViewModel
        implements ProfileRepository.OnGetProfileDetailsListener {
    private ProfileRepository restProfileRepo;
    private MutableLiveData<GeneralSourceData> mutableProfileLiveData = new MutableLiveData<>();

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        restProfileRepo = new ProfileRepository(application);
        restProfileRepo.onGetProfileDetailsListener = this;
    }

    //Rest
    public void setMyProfileDetailsRest(RequestBody email
            , RequestBody name, RequestBody phone, RequestBody regionId, RequestBody deliveryCost
            , RequestBody minimumCharger, RequestBody availability, MultipartBody.Part photo
            , RequestBody apiToken, RequestBody deliveryTime) {
        sendApiRestUpdatedProfileDetails(email, name, phone, regionId, deliveryCost
                , minimumCharger, availability, photo, apiToken, deliveryTime);
    }

    private void sendApiRestUpdatedProfileDetails(RequestBody email
            , RequestBody name, RequestBody phone, RequestBody regionId, RequestBody deliveryCost
            , RequestBody minimumCharger, RequestBody availability, MultipartBody.Part photo
            , RequestBody apiToken, RequestBody deliveryTime) {
        restProfileRepo.sendRestProfileDetails(email, name, phone, regionId, deliveryCost
                , minimumCharger, availability, photo, apiToken, deliveryTime);
    }

//    public void setMyChangedState(String apiToken, String state) {
//        sendApiChangedState(apiToken, state);
//    }
//
//    private void sendApiChangedState(String apiToken, String state) {
//        restProfileRepo.nameTvRecycItemOrderRest(apiToken, state);
//    }

    public LiveData<GeneralSourceData> getMyAvailabilityState(Integer restaurantId) {
        init(restaurantId);
        return mutableProfileLiveData;
    }

    private void init(Integer restaurantId) {
        restProfileRepo.getRestAvailability(restaurantId);
    }

    //Client
    public LiveData<GeneralSourceData> getClientDetails(String apiToken) {
        initClientDetails(apiToken);
        return mutableProfileLiveData;
    }

    private void initClientDetails(String apiToken) {
        restProfileRepo.getClientProfileDetails(apiToken);
    }

    public void setClientProfileDetails(RequestBody apiToken, RequestBody name
            , RequestBody phone, RequestBody email, RequestBody regionId, MultipartBody.Part profileImage) {
        sendApiUpdatedClientProfileDetails(apiToken, name, phone, email, regionId, profileImage);
    }

    private void sendApiUpdatedClientProfileDetails(RequestBody apiToken, RequestBody name
            , RequestBody phone, RequestBody email, RequestBody regionId, MultipartBody.Part profileImage) {
        restProfileRepo.sendClientProfileDetails(apiToken, name, phone, email, regionId, profileImage);
    }

    @Override
    public void showRestAvailability(MutableLiveData<GeneralSourceData> mutableLiveData) {
        mutableProfileLiveData.setValue(mutableLiveData.getValue());
    }

    @Override
    public void showClientProfileDetails(MutableLiveData<GeneralSourceData> mutableLiveData) {
        mutableProfileLiveData.setValue(mutableLiveData.getValue());
    }

}
