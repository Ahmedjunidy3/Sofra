package com.example.sofra.view.resturantCycle.fragment.homeCycle.fooditems;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sofra.R;
import com.example.sofra.adpter.RestSwipeFoodItemAdapter;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.data.model.generalsource.GeneralSourceData;
import com.example.sofra.view.resturantCycle.viewmodel.homeCycle.RestFoodItemsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RestFoodItemsFragment extends Fragment
       implements RestSwipeFoodItemAdapter.OnDeleteRecyItemListener {

    @BindView(R.id.header_food_name_frag_food_items_rest)
    TextView headerFoodNameFragFoodItemsRest;
    @BindView(R.id.rv_frag_food_items_rest)
    RecyclerView rvFragFoodItemsRest;
    @BindView(R.id.float_btn_frag_food_items_rest)
    FloatingActionButton floatBtnFragFoodItemsRest;
    private RestFoodItemsViewModel restFoodItemsViewModel;
    private String apiToken;
    private int categId;
    private ArrayList<GeneralSourceData> foodItemsArrayList;
    private RestSwipeFoodItemAdapter foodItemsAdapter;
    private String foodCategType;
    private Unbinder unbinder;

    public RestFoodItemsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restFoodItemsViewModel = ViewModelProviders.of(this).get(RestFoodItemsViewModel.class);
        if (getArguments() != null) {
            categId = getArguments().getInt("FOOD_CATEGORY_ID");
            foodCategType = getArguments().getString("FOOD_CATEGORY_TYPE");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_items_rest, container, false);
        unbinder = ButterKnife.bind(this, view);
        headerFoodNameFragFoodItemsRest.setText(foodCategType);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        apiToken = SharedPreferenceManager.loadRestApiToken(getActivity());
        restFoodItemsViewModel.getMyFoodItems(apiToken, String.valueOf(categId))
                .observe(requireActivity(), foodItems -> {
                    foodItemsArrayList = new ArrayList<>(foodItems);
                    foodItemsAdapter = new RestSwipeFoodItemAdapter(getActivity()
                            , foodItemsArrayList, this);
                    rvFragFoodItemsRest.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rvFragFoodItemsRest.setAdapter(foodItemsAdapter);
                });
    }

    @Override
    public void deleteItem(int itemPosition, Integer itemId) {
        foodItemsArrayList.remove(itemPosition);
        foodItemsAdapter.notifyDataSetChanged();
        restFoodItemsViewModel.deleteOfferRest(apiToken, itemId);
    }

    @OnClick({R.id.float_btn_frag_food_items_rest})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.float_btn_frag_food_items_rest) {
            Fragment fragment = requireActivity().getSupportFragmentManager()
                    .findFragmentById(R.id.container_activity_rest_food_category);
            if (fragment != null) {
                fragment = new RestNewFoodItemFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("FOOD_CATEGORY_ID", categId);
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_activity_rest_food_category, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}

