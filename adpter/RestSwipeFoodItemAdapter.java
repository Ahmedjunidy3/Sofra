package com.example.sofra.adpter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.sofra.R;
import com.example.sofra.data.model.generalsource.GeneralSourceData;
import com.example.sofra.utility.MediaLoader;
import com.example.sofra.view.resturantCycle.fragment.homeCycle.fooditems.RestEditFoodItemFragment;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestSwipeFoodItemAdapter extends RecyclerSwipeAdapter<RestSwipeFoodItemAdapter.ViewHolder> {

    private final Context context;
    private ArrayList<GeneralSourceData> foodItemsList;
    private final OnDeleteRecyItemListener onDeleteRecyItemListener;

    public RestSwipeFoodItemAdapter(Context context, ArrayList<GeneralSourceData> foodItemsList,
                                    OnDeleteRecyItemListener onDeleteRecyItemListener) {
        this.context = context;
        this.foodItemsList = foodItemsList;
        this.onDeleteRecyItemListener = onDeleteRecyItemListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_food_item_rest,
                parent, false);
        return new ViewHolder(view, context, foodItemsList, onDeleteRecyItemListener);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(holder, position);
        holder.setAction(position);
    }


    @Override
    public int getItemCount() {
        if (foodItemsList == null) {
            foodItemsList = new ArrayList<>();
        }
        return foodItemsList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe_layout_food_item_rest;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.edit_iv_item_rv_food_items_rest)
        ImageView editIvItemRvFoodItemsRest;
        @BindView(R.id.remove_iv_item_rv_food_items_rest)
        ImageView removeIvItemRvFoodItemsRest;
        @BindView(R.id.main_iv_item_rv_food_items_rest)
        RoundedImageView mainIvItemRvFoodItemsRest;
        @BindView(R.id.name_tv_item_rv_food_items_rest)
        TextView nameTvItemRvFoodItemsRest;
        @BindView(R.id.descript_tv_item_rv_food_items_rest)
        TextView descriptTvItemRvFoodItemsRest;
        @BindView(R.id.price_value_tv_item_rv_food_items_rest)
        TextView priceValueTvItemRvFoodItemsRest;
        @BindView(R.id.swipe_layout_food_item_rest)
        SwipeLayout swipeLayoutFoodItemRest;

        private final Context context;
        private final ArrayList<GeneralSourceData> foodItemsList;
        private final OnDeleteRecyItemListener onDeleteRecyItemListener;

        ViewHolder(View itemView, Context context, ArrayList<GeneralSourceData> foodItemsList
                , OnDeleteRecyItemListener onDeleteRecyItemListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = context;
            this.foodItemsList = foodItemsList;
            this.onDeleteRecyItemListener = onDeleteRecyItemListener;
        }

        private void setData(ViewHolder holder, int position) {
            GeneralSourceData data = foodItemsList.get(position);
            holder.nameTvItemRvFoodItemsRest.setText(data.getName());
            holder.descriptTvItemRvFoodItemsRest.setText(data.getDescription());
            holder.priceValueTvItemRvFoodItemsRest.setText(data.getPrice());
            MediaLoader.getInstance().load(holder.mainIvItemRvFoodItemsRest, data.getPhotoUrl());
        }

        void setAction(int position) {
            GeneralSourceData data = foodItemsList.get(position);
            Integer itemId = data.getId();
            String categId = data.getCategoryId();
            editIvItemRvFoodItemsRest.setOnClickListener(v -> {
                Fragment fragment = ((AppCompatActivity) context).getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_food_category);
                if (fragment != null) {
                    fragment = new RestEditFoodItemFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("EDIT_ITEM_CATEG_ID", categId);
                    bundle.putInt("EDIT_ITEM_ITEM_ID", itemId);
                    fragment.setArguments(bundle);
                    ((AppCompatActivity) context).getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_food_category, fragment)
                            .addToBackStack(null)
                            .commit();
                }

            });

            removeIvItemRvFoodItemsRest.setOnClickListener(v -> onDeleteRecyItemListener.deleteItem(position, itemId));


        }

    }

    public interface OnDeleteRecyItemListener {
        void deleteItem(int itemPosition, Integer itemId);
    }

}


