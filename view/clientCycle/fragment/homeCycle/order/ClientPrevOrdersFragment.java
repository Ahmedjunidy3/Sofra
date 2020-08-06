package com.example.sofra.view.clientCycle.fragment.homeCycle.order;

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
import com.example.sofra.view.resturantCycle.viewmodel.homeCycle.OrdersViewModel;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ClientPrevOrdersFragment extends Fragment
        implements OrdersAdapter.OnClientConfirmOrderDeliveryListener
, OrdersAdapter.OnClickShowOrderDetailsListener {

    @BindView(R.id.rv_frag_confirmed_delivery_orders_rest)
    RecyclerView rvFragConfirmedDeliveryOrdersRest;
    @BindView(R.id.sr_frag_confirmed_delivery_orders_rest)
    SwipeRefreshLayout srFragConfirmedDeliveryOrdersRest;
    private OrdersViewModel ordersViewModel;
    private Unbinder unbinder;
    private OrdersAdapter ordersAdapter;
    private String apiToken;

    public ClientPrevOrdersFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ordersViewModel = ViewModelProviders.of(this).get(OrdersViewModel.class);
        apiToken = SharedPreferenceManager.loadClientApiToken(getActivity());
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
        ordersAdapter.onClientConfirmOrderDeliveryListener = this;
        ordersAdapter.onClickShowOrderDetailsListener = this;
        rvFragConfirmedDeliveryOrdersRest.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFragConfirmedDeliveryOrdersRest.setAdapter(ordersAdapter);
    }

    private void initMyCompletedOrders() {
        ordersViewModel.getMyOrdersClient(apiToken
                , "completed").observe(this, confirmedOrders -> {
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void confirmOrderDelivery(Integer orderId) {
        ordersViewModel.confirmMyOrderDeliveryClient(apiToken, orderId);
    }

    @Override
    public void showDetails(Integer orderId) {
        ordersViewModel.showMyOrderClient(apiToken, orderId).observe(this, orderDetails -> {
            Fragment fragment = requireActivity().getSupportFragmentManager()
                    .findFragmentById(R.id.container_activity_rest_list);
            if (fragment != null) {
                fragment = new ClientOrderDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("ORDER_DETAILS", orderDetails);
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_activity_rest_list, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
