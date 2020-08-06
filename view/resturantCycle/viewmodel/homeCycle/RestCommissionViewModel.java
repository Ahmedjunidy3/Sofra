package com.example.sofra.view.resturantCycle.viewmodel.homeCycle;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.sofra.data.model.generalsource.GeneralSourceData;
import com.example.sofra.repository.RestCommissionRepository;


public class RestCommissionViewModel extends AndroidViewModel
        implements RestCommissionRepository.OnGetCommissionListener {
    private RestCommissionRepository restCommissionRepo;
    private MutableLiveData<GeneralSourceData> mutableCommissiData = new MutableLiveData<>();

    public RestCommissionViewModel(@NonNull Application application) {
        super(application);
        restCommissionRepo = new RestCommissionRepository(application, this);
    }

    public MutableLiveData<GeneralSourceData> getRestCommission(String apiToken) {
        initRestCommission(apiToken);
        return mutableCommissiData;
    }

    private void initRestCommission(String apiToken) {
        restCommissionRepo.getCommission(apiToken);
    }


    @Override
    public void showCommission(MutableLiveData<GeneralSourceData> mutableLiveData) {
        mutableCommissiData.setValue(mutableLiveData.getValue());
    }
}
