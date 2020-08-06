package com.example.sofra.view.resturantCycle.fragment.homeCycle.categories;

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
import com.example.sofra.adpter.RestSwipeFoodCategAdapter;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.view.resturantCycle.fragment.homeCycle.categories.CustomNewFoodCategDialog;
import com.example.sofra.view.resturantCycle.viewmodel.homeCycle.RestFoodCategViewModel;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RestFoodCategFragment extends Fragment
        implements RestSwipeFoodCategAdapter.OnDeleteRecyItemListener {
    @BindView(R.id.rv_frag_food_category_rest)
    RecyclerView rvFragFoodCategoryRest;
    @BindView(R.id.sr_frag_food_category_rest)
    SwipeRefreshLayout srFragFoodCategoryRest;
    private RestFoodCategViewModel restFoodCategViewModel;
    private RestSwipeFoodCategAdapter categAdapter;
    private String apiToken;
    private Unbinder unbinder;

    public RestFoodCategFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restFoodCategViewModel = ViewModelProviders.of(this).get(RestFoodCategViewModel.class);
        apiToken = SharedPreferenceManager.loadRestApiToken(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_categ_rest
                , container, false);
        unbinder = ButterKnife.bind(this, view);
        initRecyclerView();
        initFoodCategories();
        initSwipeRefreshLayout();
        return view;
    }

    private void initRecyclerView() {
        categAdapter = new RestSwipeFoodCategAdapter(getActivity()
                , this);
        rvFragFoodCategoryRest.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFragFoodCategoryRest.setAdapter(categAdapter);
    }

    private void initFoodCategories() {
        restFoodCategViewModel.getMyCategories(apiToken)
                .observe(requireActivity(), categories -> {
                    categAdapter.setDataToAdapter(categories);
                    categAdapter.notifyDataSetChanged();
                });
    }

    private void initSwipeRefreshLayout() {
        srFragFoodCategoryRest.setEnabled(true);
        srFragFoodCategoryRest.setColorSchemeColors(Color.RED);
        srFragFoodCategoryRest.setOnRefreshListener(() -> {
            initFoodCategories();
            srFragFoodCategoryRest.setRefreshing(false);
        });
    }

    @Override
    public void deleteItem(Integer categId) {
        categAdapter.notifyDataSetChanged();
        restFoodCategViewModel.deleteOfferRest(apiToken, categId);
    }

    @OnClick(R.id.float_btn_frag_food_category_rest)
    public void onViewClicked() {
        openCustomNewFoodCategDialog();
    }

    private void openCustomNewFoodCategDialog() {
        CustomNewFoodCategDialog customDialog = new CustomNewFoodCategDialog();
        customDialog.show(requireActivity().getSupportFragmentManager(), null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
