package com.example.sofra.view.clientCycle.fragment.homeCycle.order;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.example.sofra.R;
import com.example.sofra.adpter.RestContainerAdapter;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.view.clientCycle.fragment.homeCycle.restaurant.RestItemFoodListFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RestContainerFragment extends Fragment {

    @BindView(R.id.tab_layout_frag_orders_container)
    TabLayout tabLayoutFragOrdersContainer;
    @BindView(R.id.vp_frag_orders_container)
    ViewPager vpFragOrdersContainer;
    private Unbinder unbinder;

    public RestContainerFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Integer restId = getArguments().getInt("SELECTED_REST_ID");
            SharedPreferenceManager.saveSelectedRestId(getActivity(), String.valueOf(restId));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders_container, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViewPager();
        return view;
    }

    private void initViewPager() {
        tabLayoutFragOrdersContainer.setupWithViewPager(vpFragOrdersContainer);
        RestContainerAdapter restContainerAdapter = new RestContainerAdapter(getActivity()
                , getChildFragmentManager(), 0);
        vpFragOrdersContainer.setAdapter(restContainerAdapter);
    }

    public static RestItemFoodListFragment getRestItemFoodListFragmentInstance(Activity activity) {
        Bundle bundle = new Bundle();
        bundle.putString("REST_ID", SharedPreferenceManager.loadSelectedRestId(activity));
        RestItemFoodListFragment itemFoodListFragment = new RestItemFoodListFragment();
        itemFoodListFragment.setArguments(bundle);

        return itemFoodListFragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
