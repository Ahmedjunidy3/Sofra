package com.example.sofra.repository;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;


import com.example.sofra.data.api.RetrofitClient;
import com.example.sofra.data.model.generalsource.GeneralSource;
import com.example.sofra.data.model.generalsource.GeneralSourceData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilteredRestDataSource extends PageKeyedDataSource<Integer, GeneralSourceData> {
    private final int FIRST_PAGE = 1;
    public static final int PAGE_SIZE = 10;
    private final String keyword;
    private final String regionId;

    public FilteredRestDataSource(String keyword, String regionId) {
        this.keyword = keyword;
        this.regionId = regionId;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, GeneralSourceData> callback) {
        RetrofitClient.getClient().getRestFiltered(keyword, regionId)
                .enqueue(new Callback<GeneralSource>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource> call, @NonNull Response<GeneralSource> response) {
                        GeneralSource apiResponse = response.body();
                        if (apiResponse != null) {
                            if (apiResponse.getStatus() == 1) {
                                callback.onResult(apiResponse.getData().getData(), null
                                        , FIRST_PAGE + 1);
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GeneralSource> call, @NonNull Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, GeneralSourceData> callback) {
        RetrofitClient.getClient().getRestFiltered(keyword, regionId)
                .enqueue(new Callback<GeneralSource>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource> call, @NonNull Response<GeneralSource> response) {
                        GeneralSource apiResponse = response.body();
                        if (apiResponse != null) {
                            if (apiResponse.getStatus() == 1) {
                                int key;
                                if (params.key > 1) {
                                    key = params.key - 1;
                                } else {
                                    key = 0;
                                }
                                callback.onResult(apiResponse.getData().getData(), key);
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GeneralSource> call, @NonNull Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, GeneralSourceData> callback) {
        RetrofitClient.getClient().getRestFiltered(keyword, regionId)
                .enqueue(new Callback<GeneralSource>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource> call, @NonNull Response<GeneralSource> response) {
                        GeneralSource apiResponse = response.body();
                        if (apiResponse != null) {
                            if (apiResponse.getStatus() == 1) {
                                callback.onResult(apiResponse.getData().getData()
                                        , params.key + 1);
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GeneralSource> call, @NonNull Throwable t) {

                    }
                });
    }


}
