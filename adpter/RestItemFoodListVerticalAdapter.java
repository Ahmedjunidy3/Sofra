package com.example.sofra.adpter;

import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofra.R;
import com.example.sofra.data.model.generalsource.GeneralSourceData;
import com.example.sofra.utility.MediaLoader;
import com.example.sofra.view.clientCycle.fragment.homeCycle.restaurant.ReviewItemFoodFragment;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestItemFoodListVerticalAdapter
        extends RecyclerView.Adapter<RestItemFoodListVerticalAdapter.FoodItemsViewHolder> {

    private final Context context;
    private ArrayList<GeneralSourceData> foodItemsArrayList;

    public RestItemFoodListVerticalAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FoodItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_recyc_view_rest_item_food_list_vert, parent, false);
        return new FoodItemsViewHolder(itemView, foodItemsArrayList, context);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemsViewHolder holder, int position) {
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

    public static class FoodItemsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.root_layout_item_recyc_view_rest_item_food_list_vert)
        RelativeLayout rootLayoutItemRecycViewRestItemFoodListVert;
        @BindView(R.id.iv_item_recyc_view_rest_item_food_list_vert)
        RoundedImageView ivItemRecycViewRestItemFoodListVert;
        @BindView(R.id.title_tv_item_recyc_view_rest_item_food_list_vert)
        TextView titleTvItemRecycViewRestItemFoodListVert;
        @BindView(R.id.descript_tv_item_recyc_view_rest_item_food_list_vert)
        TextView descriptTvItemRecycViewRestItemFoodListVert;
        @BindView(R.id.price_tv_item_recyc_view_rest_item_food_list_vert)
        TextView priceTvItemRecycViewRestItemFoodListVert;
        @BindView(R.id.offer_tv_item_recyc_view_rest_item_food_list_vert)
        TextView offerTvItemRecycViewRestItemFoodListVert;
        private final ArrayList<GeneralSourceData> foodItemsArrayList;
        private final Context context;

        FoodItemsViewHolder(@NonNull View itemView
                , ArrayList<GeneralSourceData> foodItemsArrayList, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.foodItemsArrayList = foodItemsArrayList;
            this.context = context;
        }

        private void setData(int position) {
            GeneralSourceData data = foodItemsArrayList.get(position);
            String offerPrice = data.getOfferPrice();
            MediaLoader.getInstance().load(ivItemRecycViewRestItemFoodListVert, data.getPhotoUrl());
            titleTvItemRecycViewRestItemFoodListVert.setText(data.getName());
            descriptTvItemRecycViewRestItemFoodListVert.setText(data.getDescription());
            priceTvItemRecycViewRestItemFoodListVert.setText(context.getString(R.string.price_value
                    , data.getPrice()));
            if (offerPrice != null) {
                SpannableString ss = new SpannableString(priceTvItemRecycViewRestItemFoodListVert.getText());
                ss.setSpan(new StrikethroughSpan(), 7, 17, 0);
                priceTvItemRecycViewRestItemFoodListVert.setText(ss);
                offerTvItemRecycViewRestItemFoodListVert.setVisibility(View.VISIBLE);
                offerTvItemRecycViewRestItemFoodListVert.setText(context.getString(R.string.price_value2
                        , context.getString(R.string.saudi_riyal), offerPrice));
            } else {
                offerTvItemRecycViewRestItemFoodListVert.setVisibility(View.GONE);
            }
        }

        private void setAction(int position) {
            GeneralSourceData data = foodItemsArrayList.get(position);
            rootLayoutItemRecycViewRestItemFoodListVert.setOnClickListener(v -> {
                Fragment fragment = Objects.requireNonNull((AppCompatActivity) context)
                        .getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_list);
                if (fragment != null) {
                    fragment = new ReviewItemFoodFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("SELECTED_ITEM_ID", data.getId());
                    bundle.putString("SELECTED_ITEM_FOOD_PHOTO_URL", data.getPhotoUrl());
                    bundle.putString("SELECTED_ITEM_FOOD_NAME", data.getName());
                    bundle.putString("SELECTED_ITEM_FOOD_DESCRIPTION", data.getDescription());
                    bundle.putString("SELECTED_ITEM_FOOD_PRICE", data.getPrice());
                    bundle.putString("SELECTED_ITEM_FOOD_OFFER_PRICE", data.getOfferPrice());
                    bundle.putString("SELECTED_ITEM_FOOD_DELIVERY_COST", data.getDeliveryCost());
                    fragment.setArguments(bundle);
                    ((AppCompatActivity) context).getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_list, fragment)
                            .addToBackStack(null)
                            .commit();
                }
            });

        }

    }

    public void setFoodItemsDataToAdapter(List<GeneralSourceData> foodItems) {
        this.foodItemsArrayList = new ArrayList<>(foodItems);
    }


}
