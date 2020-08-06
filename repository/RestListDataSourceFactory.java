package com.example.sofra.repository;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.example.sofra.data.model.generalsource.GeneralSourceData;


public class RestListDataSourceFactory extends DataSource.Factory<Integer, GeneralSourceData> {

    @NonNull
    @Override
    public DataSource<Integer, GeneralSourceData> create() {
        return new RestListDataSource();
    }

}
