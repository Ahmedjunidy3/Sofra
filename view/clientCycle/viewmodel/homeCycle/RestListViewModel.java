package com.example.sofra.view.clientCycle.viewmodel.homeCycle;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.sofra.data.model.generalsource.GeneralSourceData;
import com.example.sofra.repository.FilteredRestDataSource;
import com.example.sofra.repository.FilteredRestListDataSourceFactory;
import com.example.sofra.repository.RestListDataSource;
import com.example.sofra.repository.RestListDataSourceFactory;
import com.example.sofra.repository.RestListRepository;


public class RestListViewModel extends AndroidViewModel
        implements RestListRepository.OnGetRestInfoListener {
    private RestListRepository restListRepo;
    private LiveData<PagedList<GeneralSourceData>> restPagedList;
    private LiveData<PagedList<GeneralSourceData>> filteredRestPagedList;
    private MutableLiveData<GeneralSourceData> mutableRestInfoData = new MutableLiveData<>();
    private FilteredRestListDataSourceFactory filteredDataSourceFactory;

    public RestListViewModel(@NonNull Application application) {
        super(application);
        restListRepo = new RestListRepository(application);
        restListRepo.onGetRestInfoListener = this;
        initRestPagedList();
        initFilteredRestPagedList();
    }

    private void initRestPagedList() {
        RestListDataSourceFactory dataSourceFactory = new RestListDataSourceFactory();
        restPagedList = new LivePagedListBuilder<>(dataSourceFactory
                , RestListDataSource.PAGE_SIZE).build();
    }

    private void initFilteredRestPagedList() {
        filteredDataSourceFactory = new FilteredRestListDataSourceFactory();
        filteredRestPagedList = new LivePagedListBuilder<>(filteredDataSourceFactory
                , FilteredRestDataSource.PAGE_SIZE).build();
    }

    public LiveData<PagedList<GeneralSourceData>> getRestPagedList() {
        return restPagedList;
    }

    public LiveData<PagedList<GeneralSourceData>> getFilteredRestPagedList(String keyword, String regionId) {
        filteredDataSourceFactory.setKeyword(keyword);
        filteredDataSourceFactory.setRegionId(regionId);
        return filteredRestPagedList;
    }

    public LiveData<GeneralSourceData> getRestInfoForClient(Integer restaurantId) {
        initInfo(restaurantId);
        return mutableRestInfoData;
    }

    private void initInfo(Integer restaurantId) {
        restListRepo.getSelectedRestInfo(restaurantId);
    }


    @Override
    public void showRestInfo(MutableLiveData<GeneralSourceData> mutableLiveData) {
        mutableRestInfoData.setValue(mutableLiveData.getValue());
    }


}
