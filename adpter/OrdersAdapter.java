package com.example.sofra.adpter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.sofra.R;
import com.example.sofra.data.model.generalsource.GeneralSourceData;
import com.example.sofra.utility.MediaLoader;
import com.example.sofra.view.resturantCycle.fragment.homeCycle.orders.CustomRejectOrderDialog;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrdersAdapter extends RecyclerSwipeAdapter<OrdersAdapter.OrdersViewHolder> {
    private final Context context;
    private ArrayList<GeneralSourceData> pendingOrdersArrayList;
    public OnClickOrderStateListener onClickOrderStateListener;
    public OnClickShowOrderDetailsListener onClickShowOrderDetailsListener;
    public OnClientDeclineOrderListener onClientDeclineOrderListener;
    public OnClientConfirmOrderDeliveryListener onClientConfirmOrderDeliveryListener;

    public OrdersAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyc_view_order_rest,
                parent, false);
        return new OrdersViewHolder(view, context, pendingOrdersArrayList
                , onClickOrderStateListener, onClickShowOrderDetailsListener
                , onClientDeclineOrderListener, onClientConfirmOrderDeliveryListener);
    }


    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {
        holder.setData(position);
        holder.setAction(position);
    }


    @Override
    public int getItemCount() {
        if (pendingOrdersArrayList == null) {
            pendingOrdersArrayList = new ArrayList<>();
        }
        return pendingOrdersArrayList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe_layout_food_item_rest;
    }

    public static class OrdersViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_view_order_state)
        CardView cardViewOrderState;
        @BindView(R.id.iv_recyc_item_order_rest)
        RoundedImageView ivRecycItemOrderRest;
        @BindView(R.id.name_tv_recyc_item_order_rest)
        TextView nameTvRecycItemOrderRest;
        @BindView(R.id.order_no_tv_recyc_item_order_rest)
        TextView orderNoTvRecycItemOrderRest;
        @BindView(R.id.total_price_tv_recyc_item_order_rest)
        TextView totalPriceTvRecycItemOrderRest;
        @BindView(R.id.address_tv_recyc_item_order_rest)
        TextView addressTvRecycItemOrderRest;
        @BindView(R.id.call_btn_recyc_item_order_rest)
        Button callBtnRecycItemOrderRest;
        @BindView(R.id.accept_btn_recyc_item_order_rest)
        Button acceptBtnRecycItemOrderRest;
        @BindView(R.id.reject_btn_recyc_item_order_rest)
        Button rejectBtnRecycItemOrderRest;
        private final Context context;
        private final ArrayList<GeneralSourceData> ordersArrayList;
        private final OnClickOrderStateListener onClickOrderStateListener;
        private final OnClickShowOrderDetailsListener onClickShowOrderDetailsListener;
        private final OnClientDeclineOrderListener onClientDeclineOrderListener;
        private final OnClientConfirmOrderDeliveryListener onClientConfirmOrderDeliveryListener;

        OrdersViewHolder(View itemView, Context context
                , ArrayList<GeneralSourceData> ordersArrayList
                , OnClickOrderStateListener onClickOrderStateListener
                , OnClickShowOrderDetailsListener onClickShowOrderDetailsListener
                , OnClientDeclineOrderListener onClientDeclineOrderListener
                , OnClientConfirmOrderDeliveryListener onClientConfirmOrderDeliveryListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = context;
            this.ordersArrayList = ordersArrayList;
            this.onClickOrderStateListener = onClickOrderStateListener;
            this.onClickShowOrderDetailsListener = onClickShowOrderDetailsListener;
            this.onClientDeclineOrderListener = onClientDeclineOrderListener;
            this.onClientConfirmOrderDeliveryListener = onClientConfirmOrderDeliveryListener;
        }

        private void setData(int position) {
            GeneralSourceData data = ordersArrayList.get(position);
            String state = data.getState();
            MediaLoader.getInstance().load(ivRecycItemOrderRest, data.getPhotoUrl());
            orderNoTvRecycItemOrderRest.setText(context.getString((R.string.order_no)
                    , String.valueOf(data.getId())));
            totalPriceTvRecycItemOrderRest.setText(context.getString((R.string.total_price)
                    , data.getTotal()));
            addressTvRecycItemOrderRest.setText(context.getString((R.string.address), data.getAddress()));
            //Client
            if (data.getClient() == null) {
                nameTvRecycItemOrderRest
                        .setText(data.getRestaurant().getName());
                if (state.equals("pending")) {
                    callBtnRecycItemOrderRest.setVisibility(View.GONE);
                    acceptBtnRecycItemOrderRest.setVisibility(View.GONE);
                    rejectBtnRecycItemOrderRest.setPadding(0, 0, 350, 0);
                    rejectBtnRecycItemOrderRest.setCompoundDrawablePadding(-330);
                }
                if (state.equals("accepted")) {
                    callBtnRecycItemOrderRest.setVisibility(View.GONE);
                    acceptBtnRecycItemOrderRest.setText(context.getString(R.string.confirm_delivery2));
                    rejectBtnRecycItemOrderRest.setPadding(0,0,110,0);
                    rejectBtnRecycItemOrderRest.setCompoundDrawablePadding(-100);
                }
            }
            //Rest
            else {
                nameTvRecycItemOrderRest
                        .setText(context.getString(R.string.customer_name, data.getClient().getName()));
                if (state.equals("accepted")) {
                    callBtnRecycItemOrderRest.setVisibility(View.GONE);
                    acceptBtnRecycItemOrderRest.setText(context.getString(R.string.confirm_delivery));
                    rejectBtnRecycItemOrderRest.setPadding(0,0,110,0);
                    rejectBtnRecycItemOrderRest.setCompoundDrawablePadding(-100);
                }
            }

            if (state.equals("delivered")) {
                callBtnRecycItemOrderRest.setVisibility(View.GONE);
                rejectBtnRecycItemOrderRest.setVisibility(View.GONE);
                acceptBtnRecycItemOrderRest.setText(context.getString(R.string.order_completed));
                acceptBtnRecycItemOrderRest.setGravity(Gravity.CENTER);
                acceptBtnRecycItemOrderRest.setTextSize(18);
                acceptBtnRecycItemOrderRest.setCompoundDrawablePadding(0);
                acceptBtnRecycItemOrderRest.setPadding(0,0,0,0);
                acceptBtnRecycItemOrderRest.setCompoundDrawables(null, null, null, null);
                LayoutParams params = new LayoutParams(400, 100);
                acceptBtnRecycItemOrderRest.setLayoutParams(params);
            }
            if (state.equals("declined") || state.equals("rejected")) {
                callBtnRecycItemOrderRest.setVisibility(View.GONE);
                acceptBtnRecycItemOrderRest.setVisibility(View.GONE);
                rejectBtnRecycItemOrderRest.setText(context.getString(R.string.order_rejected));
                rejectBtnRecycItemOrderRest.setTextSize(18);
                rejectBtnRecycItemOrderRest.setBackgroundColor(context.getResources()
                        .getColor(R.color.colorPrimary));
                rejectBtnRecycItemOrderRest.setGravity(Gravity.CENTER);
                rejectBtnRecycItemOrderRest.setCompoundDrawablePadding(0);
                rejectBtnRecycItemOrderRest.setPadding(0,0,0,0);
                rejectBtnRecycItemOrderRest.setCompoundDrawables(null, null, null, null);
                LayoutParams params = new LayoutParams(400, 100);
                rejectBtnRecycItemOrderRest.setLayoutParams(params);
            }
        }

        void setAction(int position) {
            GeneralSourceData data = ordersArrayList.get(position);
            Integer orderId = data.getId();
            GeneralSourceData client = data.getClient();
            String state = data.getState();
            callBtnRecycItemOrderRest.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + client.getPhone()));
                context.startActivity(intent);
            });

            acceptBtnRecycItemOrderRest.setOnClickListener(v -> {
                if (state.equals("pending")) {
                    //For Rest
                    if (client != null) {
                        onClickOrderStateListener.changeRestOrderState(orderId);
                    }
                }
                if (state.equals("accepted")) {
                    if (client != null) {
                        onClickOrderStateListener.changeRestOrderState(orderId);
                    } //For Client
                    else {
                        onClientConfirmOrderDeliveryListener.confirmOrderDelivery(orderId);
                    }
                }
            });

            rejectBtnRecycItemOrderRest.setOnClickListener(v -> {
                if (state.equals("pending") || state.equals("accepted")) {
                    //For Rest
                    if (client != null) {
                        CustomRejectOrderDialog rejectOrderDialog = new CustomRejectOrderDialog(orderId);
                        rejectOrderDialog.show(((AppCompatActivity) context).getSupportFragmentManager()
                                , null);
                    } //For Client
                    else {
                        onClientDeclineOrderListener.declineOrder(orderId);
                    }
                }
            });

            cardViewOrderState.setOnClickListener(v -> onClickShowOrderDetailsListener.showDetails(orderId));

        }

    }

    public interface OnClickOrderStateListener {
        void changeRestOrderState(Integer orderId);

    }

    public interface OnClickShowOrderDetailsListener {
        void showDetails(Integer orderId);
    }

    public interface OnClientDeclineOrderListener {
        void declineOrder(Integer orderId);
    }

    public interface OnClientConfirmOrderDeliveryListener {
        void confirmOrderDelivery(Integer orderId);
    }


    public void setDataListToAdapter(List<GeneralSourceData> pendingOrdersArrayList) {
        this.pendingOrdersArrayList = new ArrayList<>(pendingOrdersArrayList);
    }


}
