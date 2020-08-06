package com.example.sofra.adpter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.sofra.R;
import com.example.sofra.data.model.generalsource.GeneralSourceData;
import com.example.sofra.utility.MediaLoader;
import com.example.sofra.view.clientCycle.fragment.homeCycle.offers.OfferDetailsFragment;
import com.example.sofra.view.resturantCycle.fragment.homeCycle.offers.RestEditOfferFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class RestSwipeLayoutOffersAdapter
        extends RecyclerSwipeAdapter<RestSwipeLayoutOffersAdapter.OffersViewHolder> {

    private final Context context;
    private ArrayList<GeneralSourceData> offersArrayList;
    public OnDeleteRecyItemListener onDeleteRecyItemListener;

    public RestSwipeLayoutOffersAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public OffersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_recycler_view_offer
                , parent, false);
        return new OffersViewHolder(itemView, offersArrayList, context, onDeleteRecyItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull OffersViewHolder holder, int position) {
        holder.setData(position);
        holder.setAction(position);

    }


    @Override
    public int getItemCount() {
        if (offersArrayList == null) {
            offersArrayList = new ArrayList<>();
        }
        return offersArrayList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe_layout_frag_offers_rest;
    }

    public static class OffersViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.edit_recy_item_frag_offers_rest)
        ImageView editRecyItemFragOffersRest;
        @BindView(R.id.remove_recy_item_frag_offers_rest)
        ImageView removeRecyItemFragOffersRest;
        @BindView(R.id.iv_item_recy_offer_frag_offers)
        CircleImageView ivItemRecyOfferFragOffers;
        @BindView(R.id.title_tv_item_recy_frag_offers)
        TextView titleTvItemRecyFragOffers;
        @BindView(R.id.details_btn_item_recy_frag_offers)
        Button detailsBtnItemRecyFragOffers;
        final List<GeneralSourceData> offersArrayList;
        final Context context;
        final OnDeleteRecyItemListener onDeleteRecyItemListener;

        OffersViewHolder(@NonNull View itemView, List<GeneralSourceData> offersArrayList
                , Context context, OnDeleteRecyItemListener onDeleteRecyItemListener) {
            super(itemView);
            this.offersArrayList = offersArrayList;
            this.context = context;
            this.onDeleteRecyItemListener = onDeleteRecyItemListener;
            ButterKnife.bind(this, itemView);
        }

        private void setData(int position) {
            GeneralSourceData data = offersArrayList.get(position);
            titleTvItemRecyFragOffers.setText(data.getName());
            MediaLoader.getInstance().load(ivItemRecyOfferFragOffers, data.getPhotoUrl());
            Fragment fragment = ((AppCompatActivity) context).getSupportFragmentManager()
                    .findFragmentById(R.id.container_activity_rest_food_category);
            if (fragment != null) {
              detailsBtnItemRecyFragOffers.setVisibility(View.GONE);
            } else {
                editRecyItemFragOffersRest.setVisibility(View.GONE);
                removeRecyItemFragOffersRest.setVisibility(View.GONE);
            }
        }

        private void setAction(int position) {
            GeneralSourceData data = offersArrayList.get(position);
            Integer itemId = data.getId();
            editRecyItemFragOffersRest.setOnClickListener(v -> {
                Fragment fragment = ((AppCompatActivity) context).getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_food_category);
                if (fragment != null) {
                    fragment = new RestEditOfferFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("EDIT_OFFER_ITEM_ID", itemId);
                    fragment.setArguments(bundle);
                    ((AppCompatActivity) context).getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_food_category, fragment)
                            .addToBackStack(null)
                            .commit();
                }
            });

            removeRecyItemFragOffersRest.setOnClickListener(v -> {
                offersArrayList.remove(position);
                onDeleteRecyItemListener.deleteItem(itemId);
            });

            detailsBtnItemRecyFragOffers.setOnClickListener(v -> {
                Fragment fragment = ((AppCompatActivity) context).getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_list);
                if (fragment != null) {
                    fragment = new OfferDetailsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("SELECTED_OFFER_ID", itemId);
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

    public interface OnDeleteRecyItemListener {
        void deleteItem(int itemId);
    }

    public void setDataToAdapter(List<GeneralSourceData> offersList) {
        this.offersArrayList = new ArrayList<>(offersList);
    }


}
