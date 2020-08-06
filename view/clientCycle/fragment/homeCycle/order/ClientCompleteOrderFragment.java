package com.example.sofra.view.clientCycle.fragment.homeCycle.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


import com.example.sofra.R;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.data.local.room.FoodItem;
import com.example.sofra.utility.ConvertStringToOtherFormat;
import com.example.sofra.view.clientCycle.fragment.homeCycle.order.ClientOrdersViewPagerFragment;
import com.example.sofra.view.clientCycle.viewmodel.homeCycle.CartViewModel;
import com.example.sofra.view.resturantCycle.viewmodel.homeCycle.OrdersViewModel;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ClientCompleteOrderFragment extends Fragment {
    @BindView(R.id.note_et_frag_client_complete_order)
    EditText noteEtFragClientCompleteOrder;
    @BindView(R.id.address_et_frag_client_complete_order)
    TextView addressEtFragClientCompleteOrder;
    @BindView(R.id.pay_cash_chkbx_frag_client_complete_order)
    AppCompatCheckBox payCashChkbxFragClientCompleteOrder;
    @BindView(R.id.pay_online_chkbx_frag_client_complete_order)
    AppCompatCheckBox payOnlineChkbxFragClientCompleteOrder;
    @BindView(R.id.sum_frag_client_complete_order)
    TextView sumFragClientCompleteOrder;
    @BindView(R.id.delivery_cost_frag_client_complete_order)
    TextView deliveryCostFragClientCompleteOrder;
    @BindView(R.id.total_price_frag_client_complete_order)
    TextView totalPriceFragClientCompleteOrder;
    @BindView(R.id.order_confirm_frag_client_complete_order)
    Button orderConfirmFragClientCompleteOrder;
    private Unbinder unbinder;
    private String name;
    private String phone;
    private OrdersViewModel ordersViewModel;
    private double sumPrice = 0;
    private CartViewModel cartViewModel;
    private String payId;

    public ClientCompleteOrderFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ordersViewModel = ViewModelProviders.of(this).get(OrdersViewModel.class);
        cartViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        if (getArguments() != null) {
            name = getArguments().getString("CLIENT_NAME");
            phone = getArguments().getString("CLIENT_PHONE");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_complete_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        getData();
        return view;
    }

    private void getData() {
        cartViewModel.getMyFoodItems().observe(getViewLifecycleOwner(), foodItems -> {
            ArrayList<FoodItem> itemsArrayList = new ArrayList<>(foodItems);
            setData(itemsArrayList);
        });
    }

    private void setData(ArrayList<FoodItem> itemsArrayList) {
        for (int i = 0; i < itemsArrayList.size(); i++) {
            double price = ConvertStringToOtherFormat.getDoubleFromString(itemsArrayList.get(i).getPrice());
            sumPrice += price;
        }
        double deliveryCost = ConvertStringToOtherFormat.getDoubleFromString(itemsArrayList.get(0)
                .getDeliveryCost());
        double totalPrice = sumPrice + deliveryCost;
        sumFragClientCompleteOrder.setText(requireActivity()
                .getString(R.string.sum, sumPrice));
        deliveryCostFragClientCompleteOrder.setText(getActivity()
                .getString(R.string.delivery_charge2, deliveryCost));
        totalPriceFragClientCompleteOrder.setText(requireActivity()
                .getString((R.string.total_price2), totalPrice));
    }

    @OnClick({R.id.pay_cash_chkbx_frag_client_complete_order, R.id.pay_online_chkbx_frag_client_complete_order, R.id.order_confirm_frag_client_complete_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pay_cash_chkbx_frag_client_complete_order: {
                payId = "1";
                payOnlineChkbxFragClientCompleteOrder.setChecked(false);
                break;
            }
            case R.id.pay_online_chkbx_frag_client_complete_order: {
                 payId = "7";
                 payCashChkbxFragClientCompleteOrder.setChecked(false);
                break;
            }
            case R.id.order_confirm_frag_client_complete_order: {
                String apiToken = SharedPreferenceManager.loadClientApiToken(getActivity());
                String restId = SharedPreferenceManager.loadSelectedRestId(getActivity());

                cartViewModel.getMyFoodItems().observe(this, foodItems -> {
                    ArrayList<FoodItem> itemsArrayList = new ArrayList<>(foodItems);
                    confirmMyOrderDetails(itemsArrayList, apiToken, restId);
                });

                ordersViewModel.checkNewOrderSuccessMsg().observe(this, s -> {
                    if (s != null) {
                        Fragment fragment = requireActivity().getSupportFragmentManager()
                                .findFragmentById(R.id.container_activity_rest_list);
                        if (fragment != null) {
                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.container_activity_rest_list, new ClientOrdersViewPagerFragment())
                                    .addToBackStack(null)
                                    .commit();
                        }
                        cartViewModel.deleteOrderedFoodItemsFromCart();
                    }
                });
                break;
            }
        }
    }

    private void confirmMyOrderDetails(ArrayList<FoodItem> itemsArrayList, String apiToken
            , String restId) {
        if (itemsArrayList.size() == 1) {
            FoodItem item0 = itemsArrayList.get(0);
            ordersViewModel.setMyNewOrder(restId
                    , noteEtFragClientCompleteOrder.getText().toString().trim()
                    , addressEtFragClientCompleteOrder.getText().toString().trim()
                    , payId, phone, name, apiToken
                    , item0.getItemId(), item0.getQty(), item0.getNote()
                    , null, null, null
                    , null, null, null);
        }
        if (itemsArrayList.size() == 2) {
            FoodItem item0 = itemsArrayList.get(0);
            FoodItem item1 = itemsArrayList.get(1);
            ordersViewModel.setMyNewOrder(restId
                    , noteEtFragClientCompleteOrder.getText().toString().trim()
                    , addressEtFragClientCompleteOrder.getText().toString().trim()
                    , payId, phone, name, apiToken
                    , item0.getItemId(), item0.getQty(), item0.getNote()
                    , item1.getItemId(), item1.getQty(), item1.getNote()
                    , null, null, null);
        }
        if (itemsArrayList.size() == 3) {
            FoodItem item0 = itemsArrayList.get(0);
            FoodItem item1 = itemsArrayList.get(1);
            FoodItem item2 = itemsArrayList.get(2);
            ordersViewModel.setMyNewOrder(restId
                    , noteEtFragClientCompleteOrder.getText().toString().trim()
                    , addressEtFragClientCompleteOrder.getText().toString().trim()
                    , payId, phone, name, apiToken
                    , item0.getItemId(), item0.getQty(), item0.getNote()
                    , item1.getItemId(), item1.getQty(), item1.getNote()
                    , item2.getItemId(), item2.getQty(), item2.getNote());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
