package com.example.sofra.view.resturantCycle.viewmodel.userCycle;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sofra.data.model.generalsource.GeneralSourceData;
import com.example.sofra.repository.AuthRepository;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AuthViewModel extends AndroidViewModel
        implements AuthRepository.OnLoadListener {
    private AuthRepository authRepo;
    private MutableLiveData<List<GeneralSourceData>> citiesMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<GeneralSourceData>> regionsMutableLiveData = new MutableLiveData<>();

    public AuthViewModel(@NonNull Application application) {
        super(application);
        authRepo = new AuthRepository(application);
        authRepo.onLoadListener = this;
    }

    public void getLogin(String email, String password) {
        sendApiRestLoginDetails(email, password);
    }

    private void sendApiRestLoginDetails(String email, String password) {
        authRepo.getLogin(email, password);
    }

    public void setClientLoginDetails(String email, String password) {
        sendApiClientLoginDetails(email, password);
    }

    private void sendApiClientLoginDetails(String email, String password) {
        authRepo.checkClientLoginData(email, password);
    }

    //Rest-Sign Up
    public void setRestSignedUpData(RequestBody name, RequestBody email, RequestBody password, RequestBody passwordConfirm
            , RequestBody phone, RequestBody whatsApp, RequestBody regionId, RequestBody deliveryCost, RequestBody minCharger
            , MultipartBody.Part photo, RequestBody deliveryTime) {
        setApiRestSignedUpDetails(name, email, password
                , passwordConfirm, phone, whatsApp, regionId, deliveryCost
                , minCharger, photo, deliveryTime);
    }

    private void setApiRestSignedUpDetails(RequestBody name, RequestBody email, RequestBody password, RequestBody passwordConfirm
            , RequestBody phone, RequestBody whatsApp, RequestBody regionId, RequestBody deliveryCost, RequestBody minCharger
            , MultipartBody.Part photo, RequestBody deliveryTime) {
        authRepo.setRestSignedUpData(name, email, password
                , passwordConfirm, phone, whatsApp, regionId, deliveryCost, minCharger, photo
                , deliveryTime);
    }


    //Client-Sign Up
    public void setClientSignedUpDetails(RequestBody name, RequestBody email, RequestBody password
            , RequestBody passwordConfirm, RequestBody phone, RequestBody regionId
            , MultipartBody.Part profileImage) {
        setApiClientSignedUpDetails(name, email, password, passwordConfirm
                , phone, regionId, profileImage);
    }

    private void setApiClientSignedUpDetails(RequestBody name, RequestBody email, RequestBody password
            , RequestBody passwordConfirm, RequestBody phone, RequestBody regionId
            , MultipartBody.Part profileImage) {
        authRepo.setClientSignedUpData(name, email, password, passwordConfirm
                , phone, regionId, profileImage);
    }


    //Rest-Register Token
    public void setMyDeviceTokenRest(String token, String type, String apiToken) {
        sendApiRestDeviceToken(token, type, apiToken);
    }

    private void sendApiRestDeviceToken(String token, String type, String apiToken) {
        authRepo.setRestDeviceToken(token, type, apiToken);
    }

    //Rest-Remove Token
    public void removeMyDeviceTokenRest(String token, String apiToken) {
        sendApiRestDeviceToken(token, apiToken);
    }

    private void sendApiRestDeviceToken(String token, String apiToken) {
        authRepo.removeRestDeviceToken(token, apiToken);
    }

    //Client-Register Token
    public void setMyDeviceTokenClient(String token, String type, String apiToken) {
        sendApiClientDeviceToken(token, type, apiToken);
    }

    private void sendApiClientDeviceToken(String token, String type, String apiToken) {
        authRepo.setClientDeviceToken(token, type, apiToken);
    }

    //Client-Remove Token
    public void removeMyDeviceTokenClient(String token, String apiToken) {
        sendApiClientDeviceToken(token, apiToken);
    }

    private void sendApiClientDeviceToken(String token, String apiToken) {
        authRepo.removeClientDeviceToken(token, apiToken);
    }

    public LiveData<List<GeneralSourceData>> getAllCities() {
        initLoadCities();
        return citiesMutableLiveData;
    }

    private void initLoadCities() {
        authRepo.getCities();
    }
    @Override
    public void loadCities(MutableLiveData<List<GeneralSourceData>> mutableCitiesLiveData) {
        citiesMutableLiveData.setValue(mutableCitiesLiveData.getValue());
    }


    public LiveData<List<GeneralSourceData>> getAllRegions(String cityId) {
        initLoadRegions(cityId);
        return regionsMutableLiveData;
    }

    private void initLoadRegions(String cityId) {
        authRepo.getRegions(cityId);
    }


    @Override
    public void loadRegions(MutableLiveData<List<GeneralSourceData>> mutableRegionsLiveData) {
        regionsMutableLiveData.setValue(mutableRegionsLiveData.getValue());
    }


}
