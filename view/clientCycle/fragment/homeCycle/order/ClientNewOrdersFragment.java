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

public class ClientNewOrdersFragment extends Fragment
        implements OrdersAdapter.OnClientDeclineOrderListener
        , OrdersAdapter.OnClickShowOrderDetailsListener {

    @BindView(R.id.rv_frag_pending_orders_rest)
    RecyclerView rvFragPendingOrdersRest;
    @BindView(R.id.sr_frag_pending_orders_rest)
    SwipeRefreshLayout srFragPendingOrdersRest;
    private Unbinder unbinder;
    private OrdersViewModel ordersViewModel;
    private OrdersAdapter ordersAdapter;
    private String apiToken;

    public ClientNewOrdersFragment() {
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
        View view = inflater.inflate(R.layout.fragment_new_orders, container, false);
        unbinder = ButterKnife.bind(this, view);
        initRecyclerView();
        initMyPendingOrders();
        initSwipeRefreshLayout();
        return view;
    }

    private void initRecyclerView() {
        ordersAdapter = new OrdersAdapter(getActivity());
        ordersAdapter.onClientDeclineOrderListener = this;
        ordersAdapter.onClickShowOrderDetailsListener = this;
        rvFragPendingOrdersRest.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFragPendingOrdersRest.setAdapter(ordersAdapter);
    }

    private void initMyPendingOrders() {
        ordersViewModel.getMyOrdersClient(apiToken
                , "pending").observe(getViewLifecycleOwner(), pendingOrders -> {
            ordersAdapter.setDataListToAdapter(pendingOrders);
            ordersAdapter.notifyDataSetChanged();
        });

    }

    private void initSwipeRefreshLayout() {
        srFragPendingOrdersRest.setEnabled(true);
        srFragPendingOrdersRest.setColorSchemeColors(Color.RED);
        srFragPendingOrdersRest.setOnRefreshListener(() -> {
            initMyPendingOrders();
            srFragPendingOrdersRest.setRefreshing(false);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void declineOrder(Integer orderId) {
        ordersViewModel.declineMyOrder(apiToken, orderId);
    }

    @Override
    public void showDetails(Integer orderId) {
        ordersViewModel.showMyOrderClient(apiToken, orderId).observe(this, orderDetails -> {
            Fragment fragment = ClientNewOrdersFragment.this.requireActivity().getSupportFragmentManager()
                    .findFragmentById(R.id.container_activity_rest_list);
            if (fragment != null) {
                fragment = new ClientOrderDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("ORDER_DETAILS", orderDetails);
                fragment.setArguments(bundle);
                ClientNewOrdersFragment.this.getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_activity_rest_list, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

    }


}
