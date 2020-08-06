package com.example.sofra.view.clientCycle.fragment.homeCycle.restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.sofra.R;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.data.local.room.FoodItem;
import com.example.sofra.utility.MediaLoader;
import com.example.sofra.view.clientCycle.viewmodel.homeCycle.CartViewModel;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReviewItemFoodFragment extends Fragment {
    @BindView(R.id.iv_item_recyc_view_rest_item_food_list_vert)
    RoundedImageView ivItemRecycViewRestItemFoodListVert;
    @BindView(R.id.title_tv_item_recyc_view_rest_item_food_list_vert)
    TextView titleTvItemRecycViewRestItemFoodListVert;
    @BindView(R.id.descript_tv_item_recyc_view_rest_item_food_list_vert)
    TextView descriptTvItemRecycViewRestItemFoodListVert;
    @BindView(R.id.price_tv_item_recyc_view_rest_item_food_list_vert)
    TextView priceTvItemRecycViewRestItemFoodListVert;
    @BindView(R.id.note_et_frag_review_item_food)
    EditText noteEtFragReviewItemFood;
    @BindView(R.id.decrease_qty_iv_frag_review_item_food)
    ImageView decreaseQtyIvFragReviewItemFood;
    @BindView(R.id.qty_value_tv_frag_review_item_food)
    TextView qtyValueTvFragReviewItemFood;
    @BindView(R.id.increase_qty_iv_frag_review_item_food)
    ImageView increaseQtyIvFragReviewItemFood;
    @BindView(R.id.shop_cart_iv_frag_review_item_food)
    ImageView shopCartIvFragReviewItemFood;
    @BindView(R.id.offer_tv_item_recyc_view_rest_item_food_list_vert)
    TextView offerTvItemRecycViewRestItemFoodListVert;
    @BindView(R.id.qty_tv_frag_review_item_food)
    TextView qtyTvFragReviewItemFood;
    private CartViewModel cartViewModel;
    private String itemPhotoUrl;
    private String itemName;
    private String itemDescription;
    private String itemPrice;
    private int itemId;
    private int qtyCounter = 0;
    private String itemOfferPrice;
    private String itemFinalPrice;

    public ReviewItemFoodFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        if (getArguments() != null) {
            itemId = getArguments().getInt("SELECTED_ITEM_ID");
            itemPhotoUrl = getArguments().getString("SELECTED_ITEM_FOOD_PHOTO_URL");
            itemName = getArguments().getString("SELECTED_ITEM_FOOD_NAME");
            itemDescription = getArguments().getString("SELECTED_ITEM_FOOD_DESCRIPTION");
            itemPrice = getArguments().getString("SELECTED_ITEM_FOOD_PRICE");
            itemOfferPrice = getArguments().getString("SELECTED_ITEM_FOOD_OFFER_PRICE");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review_item_food, container, false);
        ButterKnife.bind(this, view);
        setData();
        return view;
    }

    private void setData() {
        MediaLoader.getInstance().load(ivItemRecycViewRestItemFoodListVert, itemPhotoUrl);
        titleTvItemRecycViewRestItemFoodListVert.setText(itemName);
        descriptTvItemRecycViewRestItemFoodListVert.setText(itemDescription);
        priceTvItemRecycViewRestItemFoodListVert.setText("");
        if (itemOfferPrice != null) {
            itemFinalPrice = itemOfferPrice;
        } else {
            itemFinalPrice = itemPrice;
        }
        offerTvItemRecycViewRestItemFoodListVert.setText(requireActivity()
                .getString(R.string.price_value2, getActivity().getString(R.string.saudi_riyal)
                        , itemFinalPrice));
    }

    @OnClick({R.id.decrease_qty_iv_frag_review_item_food, R.id.increase_qty_iv_frag_review_item_food, R.id.shop_cart_iv_frag_review_item_food})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.decrease_qty_iv_frag_review_item_food: {
                if (qtyCounter > 0) {
                    qtyCounter--;
                    qtyValueTvFragReviewItemFood.setText(String.valueOf(qtyCounter));
                }
                break;
            }
            case R.id.increase_qty_iv_frag_review_item_food: {
                qtyCounter++;
                qtyValueTvFragReviewItemFood.setText(String.valueOf(qtyCounter));
                break;
            }
            case R.id.shop_cart_iv_frag_review_item_food: {
                FoodItem foodItem = new FoodItem();
                foodItem.setItemId(itemId);
                foodItem.setName(itemName);
                foodItem.setPrice(itemFinalPrice);
                foodItem.setDeliveryCost(SharedPreferenceManager.loadSelectedRestDeliveryCost(getActivity()));
                foodItem.setPhotoUrl(itemPhotoUrl);
                foodItem.setQty(qtyValueTvFragReviewItemFood.getText().toString());
                foodItem.setNote(noteEtFragReviewItemFood.getText().toString().trim());
                foodItem.setRestaurantId(SharedPreferenceManager.loadSelectedRestId(getActivity()));
                cartViewModel.addReviewedFoodItemToDataBase(foodItem);
                Fragment fragment = requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_list);
                if (fragment != null) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_list, new CartFragment())
                            .addToBackStack(null)
                            .commit();
                }
                break;
            }
        }
    }
}
