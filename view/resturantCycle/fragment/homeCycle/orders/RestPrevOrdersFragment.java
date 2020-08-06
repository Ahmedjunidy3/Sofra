package com.example.sofra.view.resturantCycle.fragment.homeCycle.orders;

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
import com.example.sofra.adpter.OrdersAdapter;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.data.model.generalsource.GeneralSourceData;
import com.example.sofra.view.resturantCycle.viewmodel.homeCycle.OrdersViewModel;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RestPrevOrdersFragment extends Fragment
        implements OrdersAdapter.OnClickShowOrderDetailsListener {

    @BindView(R.id.rv_frag_confirmed_delivery_orders_rest)
    RecyclerView rvFragConfirmedDeliveryOrdersRest;
    @BindView(R.id.sr_frag_confirmed_delivery_orders_rest)
    SwipeRefreshLayout srFragConfirmedDeliveryOrdersRest;
    private OrdersViewModel ordersViewModel;
    private Unbinder unbinder;
    private OrdersAdapter ordersAdapter;
    private String apiToken;

    public RestPrevOrdersFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ordersViewModel = ViewModelProviders.of(this).get(OrdersViewModel.class);
        apiToken = SharedPreferenceManager.loadRestApiToken(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prev_orders
                , container, false);
        unbinder = ButterKnife.bind(this, view);
        initRecyclerView();
        initMyCompletedOrders();
        initSwipeRefreshLayout();
        return view;
    }

    private void initRecyclerView() {
        ordersAdapter = new OrdersAdapter(getActivity());
        ordersAdapter.onClickShowOrderDetailsListener = this;
        rvFragConfirmedDeliveryOrdersRest.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFragConfirmedDeliveryOrdersRest.setAdapter(ordersAdapter);
    }

    private void initMyCompletedOrders() {
        ordersViewModel.getMyOrdersRest(apiToken
                , "completed").observe(getViewLifecycleOwner(), confirmedOrders -> {
            ordersAdapter.setDataListToAdapter(confirmedOrders);
            ordersAdapter.notifyDataSetChanged();
        });
    }

    private void initSwipeRefreshLayout() {
        srFragConfirmedDeliveryOrdersRest.setEnabled(true);
        srFragConfirmedDeliveryOrdersRest.setColorSchemeColors(Color.RED);
        srFragConfirmedDeliveryOrdersRest.setOnRefreshListener(() -> {
            initMyCompletedOrders();
            srFragConfirmedDeliveryOrdersRest.setRefreshing(false);
        });
    }

    @Override
    public void showDetails(Integer orderId) {
        ordersViewModel.showCustomerOrder(apiToken, orderId)
                .observe(this, (GeneralSourceData orderDetails) -> {
                    Fragment fragment = requireActivity().getSupportFragmentManager()
                            .findFragmentById(R.id.container_activity_rest_food_category);
                    if (fragment != null) {
                        fragment = new RestOrderDetailsFragment();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("ORDER_DETAILS", orderDetails);
                        fragment.setArguments(bundle);
                        (getActivity()).getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container_activity_rest_food_category, fragment)
                                .addToBackStack(null)
                                .commit();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
