package com.example.sofra.view.resturantCycle.fragment.homeCycle.orders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


import com.example.sofra.R;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.data.model.generalsource.GeneralSourceData;
import com.example.sofra.utility.FormatDate;
import com.example.sofra.utility.MediaLoader;
import com.example.sofra.view.resturantCycle.viewmodel.homeCycle.OrdersViewModel;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class RestOrderDetailsFragment extends Fragment {

    @BindView(R.id.iv_frag_order_details)
    CircleImageView ivFragOrderDetails;
    @BindView(R.id.rest_name_tv_frag_order_details)
    TextView restNameTvFragOrderDetails;
    @BindView(R.id.order_details_tv_frag_order_details)
    TextView orderDetailsTvFragOrderDetails;
    @BindView(R.id.date_tv_frag_order_details)
    TextView dateTvFragOrderDetails;
    @BindView(R.id.address_tv_frag_order_details)
    TextView addressTvFragOrderDetails;
    @BindView(R.id.meal_item_01_tv_frag_order_details)
    TextView mealItem01TvFragOrderDetails;
    @BindView(R.id.meal_item_02_tv_frag_order_details)
    TextView mealItem02TvFragOrderDetails;
    @BindView(R.id.meal_item_03_tv_frag_order_details)
    TextView mealItem03TvFragOrderDetails;
    @BindView(R.id.order_cost_tv_frag_order_details)
    TextView orderCostTvFragOrderDetails;
    @BindView(R.id.delivery_cost_tv_frag_order_details)
    TextView deliveryCostTvFragOrderDetails;
    @BindView(R.id.total_cost_tv_frag_order_details)
    TextView totalCostTvFragOrderDetails;
    @BindView(R.id.paying_method_tv_frag_order_details)
    TextView payingMethodTvFragOrderDetails;
    @BindView(R.id.call_btn_frag_order_details)
    Button callBtnFragOrderDetails;
    @BindView(R.id.accept_btn_frag_order_details)
    Button acceptBtnFragOrderDetails;
    @BindView(R.id.reject_btn_frag_order_details)
    Button rejectBtnFragOrderDetails;
    private String restName;
    private String address;
    private String deliveryCost;
    private String totalCost;
    private List<GeneralSourceData> itemsList;
    private String cost;
    private String paymentMethodId;
    private OrdersViewModel ordersViewModel;
    private String photoUrl;
    private String apiToken;
    private int orderId;
    private String dateFormatted;
    private Unbinder unbinder;
    private String state;
    private String phone;

    public RestOrderDetailsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MediaLoader.initAlbum(getActivity());
        ordersViewModel = ViewModelProviders.of(this).get(OrdersViewModel.class);
        apiToken = SharedPreferenceManager.loadRestApiToken(getActivity());
        if (getArguments() != null) {
            GeneralSourceData orderDetails = getArguments().getParcelable("ORDER_DETAILS");
            if (orderDetails != null) {
                photoUrl = orderDetails.getPhotoUrl();
                restName = orderDetails.getRestaurant().getName();
                dateFormatted = FormatDate.getDate(orderDetails.getCreatedAt(), Locale.getDefault());
                address = orderDetails.getAddress();
                itemsList = orderDetails.getItems();
                cost = orderDetails.getCost();
                deliveryCost = orderDetails.getDeliveryCost();
                totalCost = orderDetails.getTotal();
                paymentMethodId = orderDetails.getPaymentMethodId();
                orderId = orderDetails.getId();
                state = orderDetails.getState();
                phone = orderDetails.getClient().getPhone();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_details, container, false);
        unbinder = ButterKnife.bind(this, view);
        setData();
        hideView();
        return view;
    }

    private void hideView() {
        if (state.equals("accepted")) {
            callBtnFragOrderDetails.setVisibility(View.GONE);
            acceptBtnFragOrderDetails.setText(requireActivity()
                    .getString(R.string.confirm_delivery));
            rejectBtnFragOrderDetails.setPadding(0,0,110,0);
            rejectBtnFragOrderDetails.setCompoundDrawablePadding(-100);
        }
        if (state.equals("declined") || state.equals("rejected")) {
            callBtnFragOrderDetails.setVisibility(View.GONE);
            acceptBtnFragOrderDetails.setVisibility(View.GONE);
            rejectBtnFragOrderDetails.setText(requireActivity()
                    .getString(R.string.order_cancelled));
            rejectBtnFragOrderDetails.setBackgroundResource(R.drawable.btn_cornered);
            rejectBtnFragOrderDetails.setBackgroundColor(getActivity().getResources()
                    .getColor(R.color.colorPrimary));
            rejectBtnFragOrderDetails.setGravity(Gravity.CENTER);
            rejectBtnFragOrderDetails.setCompoundDrawablePadding(0);
            rejectBtnFragOrderDetails.setPadding(0, 0, 0, 0);
            rejectBtnFragOrderDetails.setCompoundDrawables(null, null, null, null);
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                    , 100);
            rejectBtnFragOrderDetails.setLayoutParams(params);
        }
        if (state.equals("delivered")) {
            callBtnFragOrderDetails.setVisibility(View.GONE);
            rejectBtnFragOrderDetails.setVisibility(View.GONE);
            acceptBtnFragOrderDetails.setText(requireActivity()
                    .getString(R.string.order_completed));
            acceptBtnFragOrderDetails.setGravity(Gravity.CENTER);
            acceptBtnFragOrderDetails.setCompoundDrawablePadding(0);
            acceptBtnFragOrderDetails.setPadding(0, 0, 0, 0);
            acceptBtnFragOrderDetails.setCompoundDrawables(null, null, null, null);
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
            acceptBtnFragOrderDetails.setLayoutParams(params);
        }
    }

    private void setData() {
        MediaLoader.getInstance().load(ivFragOrderDetails, photoUrl);
        restNameTvFragOrderDetails.setText(restName);
        dateTvFragOrderDetails.setText(requireActivity()
                .getString(R.string.day, dateFormatted));
        addressTvFragOrderDetails.setText(getActivity().getString(R.string.address, address));
        orderCostTvFragOrderDetails.setText(getActivity().getString(R.string.order_price, cost));
        deliveryCostTvFragOrderDetails.setText(getActivity().getString(R.string.delivery_charge
                , deliveryCost));
        totalCostTvFragOrderDetails.setText(getActivity().getString(R.string.total_price, totalCost));
        ordersViewModel.getPayingMethod(apiToken).observe(getViewLifecycleOwner(), paymentMethods -> {
            for (int i = 0; i < paymentMethods.size(); i++) {
                if (paymentMethods.get(i).getId() == Integer.parseInt(paymentMethodId)) {
                    String payMethodName = paymentMethods.get(i).getName();
                    payingMethodTvFragOrderDetails.setText(getActivity().getString(R.string.paying_method
                            , payMethodName));
                    break;
                }
            }
        });

        for (int i = 0; i < itemsList.size(); i++) {
            if (i == 0) {
                mealItem01TvFragOrderDetails.setText(requireActivity()
                        .getString(R.string.meal_details
                                , itemsList.get(i).getName()
                                , itemsList.get(i).getPrice()
                                , getActivity().getString(R.string.saudi_riyal)));
            }
            if (i == 1) {
                mealItem02TvFragOrderDetails.setText(getActivity().getString(R.string.meal_details
                        , itemsList.get(i).getName()
                        , itemsList.get(i).getPrice()
                        , getActivity().getString(R.string.saudi_riyal)));
            }
            if (i == 2) {
                mealItem03TvFragOrderDetails.setText(getActivity().getString(R.string.meal_details
                        , itemsList.get(i).getName()
                        , itemsList.get(i).getPrice()
                        , getActivity().getString(R.string.saudi_riyal)));
            }
        }


    }


    @OnClick({R.id.call_btn_frag_order_details, R.id.accept_btn_frag_order_details
            , R.id.reject_btn_frag_order_details})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.call_btn_frag_order_details: {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phone));
                startActivity(intent);
                break;
            }
            case R.id.accept_btn_frag_order_details: {
                if (state.equals("pending")) {
                    ordersViewModel.acceptCustomerOrder(apiToken, orderId);
                } else if (state.equals("accepted")) {
                    ordersViewModel.confirmCustomerOrderDelivery(apiToken, orderId);
                }
                break;
            }
            case R.id.reject_btn_frag_order_details: {
                if (state.equals("pending ") || state.equals("accepted")) {
                    CustomRejectOrderDialog rejectOrderDailog = new CustomRejectOrderDialog(orderId);
                    rejectOrderDailog
                            .show(requireActivity().getSupportFragmentManager()
                                    , null);
                }
                break;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
