package com.example.sofra.view.clientCycle.fragment.homeCycle.restaurant;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.sofra.R;
import com.example.sofra.adpter.RestItemFoodListHorizAdapter;
import com.example.sofra.adpter.RestItemFoodListVerticalAdapter;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.view.resturantCycle.viewmodel.homeCycle.RestFoodCategViewModel;
import com.example.sofra.view.resturantCycle.viewmodel.homeCycle.RestFoodItemsViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RestItemFoodListFragment extends Fragment
        implements RestItemFoodListHorizAdapter.OnFoodCategorySelectListener {
    @BindView(R.id.rv_horiz_frag_rest_item_food_list)
    RecyclerView rvHorizFragRestItemFoodList;
    @BindView(R.id.rv_vert_frag_rest_item_food_list)
    RecyclerView rvVertFragRestItemFoodList;
    @BindView(R.id.sr_frag_rest_item_food_list)
    SwipeRefreshLayout srFragRestItemFoodList;
    private Unbinder unbinder;
    private String restId;
    private RestFoodCategViewModel restFoodCategViewModel;
    private RestFoodItemsViewModel restFoodItemsViewModel;
    private Integer categoryId;
    private RestItemFoodListHorizAdapter horizAdapter;
    private RestItemFoodListVerticalAdapter vertAdapter;

    public RestItemFoodListFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restFoodCategViewModel = ViewModelProviders.of(this).get(RestFoodCategViewModel.class);
        restFoodItemsViewModel = ViewModelProviders.of(this).get(RestFoodItemsViewModel.class);
        restId = SharedPreferenceManager.loadSelectedRestId(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rest_item_food_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        initCategRecyclerView();
        setCategData();
        initFoodItemsRecyclerView();
        setFoodItemsData();
        initSwipeRefreshLayout();
        return view;
    }

    private void initSwipeRefreshLayout() {
        srFragRestItemFoodList.setEnabled(true);
        srFragRestItemFoodList.setColorSchemeColors(Color.RED);
        srFragRestItemFoodList.setOnRefreshListener(() -> {
            categoryId = 0;
            setFoodItemsData();
            srFragRestItemFoodList.setRefreshing(false);
        });
    }

    private void initCategRecyclerView() {
        horizAdapter = new RestItemFoodListHorizAdapter(getActivity()
                , this);
        LinearLayoutManager horizLayoutManager = new LinearLayoutManager(getActivity());
        horizLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rvHorizFragRestItemFoodList
                .setLayoutManager(horizLayoutManager);
        rvHorizFragRestItemFoodList.setAdapter(horizAdapter);
    }

    private void setCategData() {
        restFoodCategViewModel.getSelectedRestCategForClient(restId)
                .observe(getViewLifecycleOwner(), categories -> {
                    horizAdapter.setCategDataToAdapter(categories);
                    horizAdapter.notifyDataSetChanged();
                });
    }

    private void initFoodItemsRecyclerView() {
        vertAdapter = new RestItemFoodListVerticalAdapter(getActivity());
        LinearLayoutManager vertLayoutManager = new LinearLayoutManager(getActivity());
        vertLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvVertFragRestItemFoodList.setLayoutManager(vertLayoutManager);
        rvVertFragRestItemFoodList.setAdapter(vertAdapter);
    }

    private void setFoodItemsData() {
        restFoodItemsViewModel.getSelectedCategFoodItems(restId
                , String.valueOf(categoryId)).observe(getViewLifecycleOwner(), FoodList -> {
            vertAdapter.setFoodItemsDataToAdapter(FoodList);
            vertAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void showItemFoodList(Integer categoryId) {
        this.categoryId = categoryId;
        setFoodItemsData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
