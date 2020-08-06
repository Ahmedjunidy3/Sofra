package com.example.sofra.view.resturantCycle.viewmodel.homeCycle;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.sofra.data.model.generalsource.GeneralSourceData;
import com.example.sofra.repository.OffersRepository;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class OffersViewModel extends AndroidViewModel
        implements OffersRepository.OnLoadOffersListener {
    private OffersRepository offersRepo;
    private MutableLiveData<List<GeneralSourceData>> offersMutableData = new MutableLiveData<>();
    private MutableLiveData<GeneralSourceData> offerMutableData = new MutableLiveData<>();

    public OffersViewModel(@NonNull Application application) {
        super(application);
        offersRepo = new OffersRepository(application, this);
    }


    //Rest-Set New Offer
    public void setMyNewOfferRest(RequestBody description, RequestBody price, RequestBody startingAt
            , RequestBody name, MultipartBody.Part photo, RequestBody endingAt, RequestBody offerPrice
            , RequestBody apiToken) {
        sendApiNewOfferDetails(description, price, startingAt, name
                , photo, endingAt, offerPrice, apiToken);
    }

    private void sendApiNewOfferDetails(RequestBody description, RequestBody price, RequestBody startingAt
            , RequestBody name, MultipartBody.Part photo, RequestBody endingAt, RequestBody offerPrice
            , RequestBody apiToken) {
        offersRepo.sendRestNewOfferDetails(description, price, startingAt, name
                , photo, endingAt, offerPrice, apiToken);
    }

    //Rest-My Offers
    public LiveData<List<GeneralSourceData>> getMyOffersRest(String apiToken) {
        initRestOffers(apiToken);
        return offersMutableData;
    }

    private void initRestOffers(String apiToken) {
        offersRepo.getRestOffers(apiToken);
    }

    //Rest-Update Offer
    public void editMyOfferRest(RequestBody description, RequestBody price, RequestBody startingAt
            , RequestBody name, MultipartBody.Part photo, RequestBody endingAt, RequestBody offerId
            , RequestBody apiToken) {
        sendApiUpdatedRestOfferDetails(description, price, startingAt, name, photo, endingAt
                , offerId, apiToken);
    }

    private void sendApiUpdatedRestOfferDetails(RequestBody description, RequestBody price, RequestBody startingAt
            , RequestBody name, MultipartBody.Part photo, RequestBody endingAt, RequestBody offerId
            , RequestBody apiToken) {
        offersRepo.updateRestOfferDetails(description, price, startingAt, name, photo, endingAt
                , offerId, apiToken);
    }

    //Rest-Delete Offer
    public void deleteOfferRest(String apiToken, Integer itemId) {
        deletedRestOfferFromApi(apiToken, itemId);
    }

    private void deletedRestOfferFromApi(String apiToken, Integer itemId) {
        offersRepo.deleteRestOffer(apiToken, itemId);
    }

    //Client-Selected Offers
    //Rest-My Offers
    public LiveData<List<GeneralSourceData>> getOffersForSelectedRest(String restaurantId) {
        initSelectedRestOffers(restaurantId);
        return offersMutableData;
    }

    private void initSelectedRestOffers(String restaurantId) {
        offersRepo.getSelectedRestOffers(restaurantId);
    }

    public LiveData<GeneralSourceData> getOfferDetailsClient(Integer offerId) {
        initSelectedOffer(offerId);
        return offerMutableData;
    }

    private void initSelectedOffer(Integer offerId) {
        offersRepo.getOfferDetails(offerId);
    }

    @Override
    public void loadOffers(MutableLiveData<List<GeneralSourceData>> mutableLiveData) {
        offersMutableData.setValue(mutableLiveData.getValue());
    }

    @Override
    public void loadOffer(MutableLiveData<GeneralSourceData> mutableLiveData) {
        offerMutableData.setValue(mutableLiveData.getValue());
    }


}
