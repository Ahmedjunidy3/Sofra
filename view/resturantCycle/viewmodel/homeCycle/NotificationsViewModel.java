package com.example.sofra.view.resturantCycle.viewmodel.homeCycle;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.sofra.data.model.generalsource.GeneralSourceData;
import com.example.sofra.repository.NotificationsRepository;

import java.util.List;

public class NotificationsViewModel extends AndroidViewModel
        implements NotificationsRepository.OnGetNotificationsListener {
    private NotificationsRepository restNotificationsRepo;
    private MutableLiveData<List<GeneralSourceData>> mutableNotificationsLiveData = new MutableLiveData<>();

    public NotificationsViewModel(@NonNull Application application) {
        super(application);
        restNotificationsRepo = new NotificationsRepository(application, this);
    }

    //Rest
    public LiveData<List<GeneralSourceData>> getRestNotifications(String apiToken) {
        initRestNotifications(apiToken);
        return mutableNotificationsLiveData;
    }

    private void initRestNotifications(String apiToken) {
      restNotificationsRepo.getRestNotifications(apiToken);
    }

    //Client
    public LiveData<List<GeneralSourceData>> getClientNotifications(String apiToken) {
        initClientNotifications(apiToken);
        return mutableNotificationsLiveData;
    }

    private void initClientNotifications(String apiToken) {
        restNotificationsRepo.getClientNotifications(apiToken);
    }

    @Override
    public void showNotifications(MutableLiveData<List<GeneralSourceData>> mutableLiveData) {
        mutableNotificationsLiveData.setValue(mutableLiveData.getValue());
    }

}
