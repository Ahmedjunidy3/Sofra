package com.example.sofra.view.resturantCycle.viewmodel.homeCycle;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.sofra.data.model.generalsource.GeneralSourceData;
import com.example.sofra.repository.MoreRepository;

import java.util.List;

public class MoreViewModel extends AndroidViewModel
        implements MoreRepository.OnGetCustomersReviewsListener {
    private MoreRepository moreRepo;
    private MutableLiveData<List<GeneralSourceData>> mutableReviewsLiveData = new MutableLiveData<>();


    public MoreViewModel(@NonNull Application application) {
        super(application);
        moreRepo = new MoreRepository(application, this);
    }
    public void setMyContactDetails(String name, String email, String phone, String type, String content) {
        sendApiUserContactDetails(name, email, phone, type, content);
    }

    private void sendApiUserContactDetails(String name, String email, String phone, String type, String content) {
        moreRepo.setContactDetails(name, email, phone, type, content);
    }

    //Rest-Reviews
    public LiveData<List<GeneralSourceData>> getMyReviews(String apiToken, Integer restaurantId) {
        initReviews(apiToken, restaurantId);
        return mutableReviewsLiveData;
    }

    private void initReviews(String apiToken, Integer restaurantId) {
        moreRepo.getRestReviews(apiToken, restaurantId);
    }

    //Rest-ChangePassword
    public void setMyNewPassRest(String apiToken, String oldPass, String newPass, String newPassConfirm) {
        sendApiRestChangedPassDetails(apiToken, oldPass, newPass, newPassConfirm);
    }

    private void sendApiRestChangedPassDetails(String apiToken, String oldPass, String newPass, String newPassConfirm) {
        moreRepo.setRestChangedPassDetails(apiToken, oldPass, newPass, newPassConfirm);
    }

    //Client-Review
    public void addClientReview(String rate, String comment, String restaurantId, String apiToken) {
        initClientReview(rate, comment, restaurantId, apiToken);
    }

    private void initClientReview(String rate, String comment, String restaurantId, String apiToken) {
        moreRepo.sendClientReview(rate, comment, restaurantId, apiToken);
    }

    //Client-ChangePassword
    public void setMyNewPassClient(String apiToken, String oldPass, String newPass, String newPassConfirm) {
        sendApiClientChangedPassDetails(apiToken, oldPass, newPass, newPassConfirm);
    }

    private void sendApiClientChangedPassDetails(String apiToken, String oldPass, String newPass, String newPassConfirm) {
        moreRepo.setClientChangedPassDetails(apiToken, oldPass, newPass, newPassConfirm);
    }

    @Override
    public void showReviews(MutableLiveData<List<GeneralSourceData>> mutableLiveData) {
        mutableReviewsLiveData.setValue(mutableLiveData.getValue());
    }


}
