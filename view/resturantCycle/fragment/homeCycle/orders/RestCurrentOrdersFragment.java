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

public class RestCurrentOrdersFragment extends Fragment
        implements OrdersAdapter.OnClickOrderStateListener
, OrdersAdapter.OnClickShowOrderDetailsListener {

    @BindView(R.id.rv_frag_accepted_orders_rest)
    RecyclerView rvFragAcceptedOrdersRest;
    @BindView(R.id.sr_frag_accepted_orders_rest)
    SwipeRefreshLayout srFragAcceptedOrdersRest;
    private OrdersViewModel ordersViewModel;
    private String apiToken;
    private Unbinder unbinder;
    private OrdersAdapter ordersAdapter;

    public RestCurrentOrdersFragment() {
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
        View view = inflater.inflate(R.layout.fragment_current_orders, container, false);
        unbinder = ButterKnife.bind(this, view);
        initRecyclerView();
        initMyAcceptedOrders();
        initSwipeRefreshLayout();
        return view;
    }

    private void initRecyclerView() {
        ordersAdapter = new OrdersAdapter(getActivity());
        ordersAdapter.onClickOrderStateListener = this;
        ordersAdapter.onClickShowOrderDetailsListener = this;
        rvFragAcceptedOrdersRest.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFragAcceptedOrdersRest.setAdapter(ordersAdapter);
    }

    private void initMyAcceptedOrders() {
        ordersViewModel.getMyOrdersRest(apiToken
                , "current").observe(getViewLifecycleOwner(), acceptedOrders -> {
            ordersAdapter.setDataListToAdapter(acceptedOrders);
            ordersAdapter.notifyDataSetChanged();
        });

    }

    private void initSwipeRefreshLayout() {
        srFragAcceptedOrdersRest.setEnabled(true);
        srFragAcceptedOrdersRest.setColorSchemeColors(Color.RED);
        srFragAcceptedOrdersRest.setOnRefreshListener(() -> {
            initMyAcceptedOrders();
            srFragAcceptedOrdersRest.setRefreshing(false);
        });
    }

    @Override
    public void changeRestOrderState(Integer orderId) {
        ordersViewModel.confirmCustomerOrderDelivery(apiToken, orderId);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
}
