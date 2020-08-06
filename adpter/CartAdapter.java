package com.example.sofra.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofra.R;
import com.example.sofra.data.local.room.FoodItem;
import com.example.sofra.utility.MediaLoader;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private final Context context;
    private ArrayList<FoodItem> foodItemsArrayList;
    private final OnDeleteCartItemListener onDeleteCartItemListener;
    private final OnQtyChangeListener onQtyChangeListener;


    public CartAdapter(Context context, OnDeleteCartItemListener onDeleteCartItemListener
            , OnQtyChangeListener onQtyChangeListener) {
        this.context = context;
        this.onDeleteCartItemListener = onDeleteCartItemListener;
        this.onQtyChangeListener = onQtyChangeListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_recyc_view_cart
                , parent, false);
        return new CartViewHolder(itemView, context, foodItemsArrayList, onDeleteCartItemListener
                , onQtyChangeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.setData(position);
        holder.setAction(position);
    }


    @Override
    public int getItemCount() {
        if (foodItemsArrayList == null) {
            foodItemsArrayList = new ArrayList<>();
        }
        return foodItemsArrayList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_recyc_view_cart)
        RoundedImageView ivItemRecycViewCart;
        @BindView(R.id.title_tv_item_recyc_view_cart)
        TextView titleTvItemRecycViewCart;
        @BindView(R.id.price_tv_item_recyc_view_cart)
        TextView priceTvItemRecycViewCart;
        @BindView(R.id.increase_qty_iv_item_recyc_view_cart)
        ImageView increaseQtyIvItemRecycViewCart;
        @BindView(R.id.qty_value_tv_item_recyc_view_cart)
        TextView qtyValueTvItemRecycViewCart;
        @BindView(R.id.decrease_qty_iv_item_recyc_view_cart)
        ImageView decreaseQtyIvItemRecycViewCart;
        @BindView(R.id.remove_iv_item_recyc_view_cart)
        ImageView removeIvItemRecycViewCart;
        private final ArrayList<FoodItem> foodItemsArrayList;
        private final Context context;
        private final OnDeleteCartItemListener onDeleteCartItemListener;
        private final OnQtyChangeListener onQtyChangeListener;
        private int qtyCounter;

        CartViewHolder(@NonNull View itemView, Context context
                , ArrayList<FoodItem> foodItemsArrayList
                , OnDeleteCartItemListener onDeleteCartItemListener
                , OnQtyChangeListener onQtyChangeListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.foodItemsArrayList = foodItemsArrayList;
            this.context = context;
            this.onDeleteCartItemListener = onDeleteCartItemListener;
            this.onQtyChangeListener = onQtyChangeListener;
        }

        private void setData(int position) {
            FoodItem data = foodItemsArrayList.get(position);
            titleTvItemRecycViewCart.setText(data.getName());
            priceTvItemRecycViewCart.setText(context.getString(R.string.price_value2
                    , data.getPrice()
                    , "ريال"));
            qtyValueTvItemRecycViewCart.setText(data.getQty());
            MediaLoader.getInstance().load(ivItemRecycViewCart, data.getPhotoUrl());
        }

        private void setAction(int position) {
            FoodItem item = foodItemsArrayList.get(position);
            removeIvItemRecycViewCart.setOnClickListener(v -> onDeleteCartItemListener.deleteItem(foodItemsArrayList.get(position).getId()
            ));

            increaseQtyIvItemRecycViewCart.setOnClickListener(v -> {
                qtyCounter++;
                qtyValueTvItemRecycViewCart.setText(String.valueOf(qtyCounter));
                item.setQty(String.valueOf(qtyCounter));
                foodItemsArrayList.set(position, item);
                onQtyChangeListener.getNewQtyList(foodItemsArrayList);
            });

            decreaseQtyIvItemRecycViewCart.setOnClickListener(v -> {
                if (qtyCounter > 0) {
                    qtyCounter--;
                    qtyValueTvItemRecycViewCart.setText(String.valueOf(qtyCounter));
                    item.setQty(String.valueOf(qtyCounter));
                    foodItemsArrayList.set(position, item);
                    onQtyChangeListener.getNewQtyList(foodItemsArrayList);
                }
            });

        }

    }

    public interface OnDeleteCartItemListener {
        void deleteItem(int id);
    }

    public interface OnQtyChangeListener {
        void getNewQtyList(ArrayList<FoodItem> newQtyArrayList);
    }

    public void setDataToAdapter(List<FoodItem> foodItems) {
        this.foodItemsArrayList = new ArrayList<>(foodItems);
    }


}
