package com.example.sofra.repository;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.example.sofra.data.model.generalsource.GeneralSourceData;


public class FilteredRestListDataSourceFactory extends DataSource.Factory<Integer, GeneralSourceData> {
    private String keyword;
    private String regionId;

    @NonNull
    @Override
    public DataSource<Integer, GeneralSourceData> create() {
        return new FilteredRestDataSource(keyword, regionId);
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

}
