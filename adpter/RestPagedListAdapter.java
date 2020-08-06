package com.example.sofra.adpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.paging.PagedListAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sofra.R;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.data.model.generalsource.GeneralSourceData;
import com.example.sofra.utility.ConvertStringToOtherFormat;
import com.example.sofra.utility.MediaLoader;
import com.example.sofra.view.clientCycle.fragment.homeCycle.order.RestContainerFragment;

import java.util.Objects;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class RestPagedListAdapter extends
        PagedListAdapter<GeneralSourceData, RestPagedListAdapter.RestListViewHolder> {

    private final Context context;

    public RestPagedListAdapter(Context context) {
        super(SOURCE_DATA_COMPARATOR);
        this.context = context;
    }

    @NonNull
    @Override
    public RestListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_recycler_view_rest_list, parent, false);
        return new RestListViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RestListViewHolder holder, int position) {
        holder.setData(Objects.requireNonNull(getItem(position)));
        holder.setAction(getItem(position));
    }

    public static class RestListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.frame_layout_frag_rest_list_client)
        FrameLayout frameLayoutFragRestListClient;
        @BindView(R.id.name_tv_item_recyc_view_frag_rest_client)
        TextView nameTvItemRecycViewFragRestClient;
        @BindView(R.id.state_iv_item_recyc_view_frag_rest_client)
        ImageView stateIvItemRecycViewFragRestClient;
        @BindView(R.id.state_tv_item_recyc_view_frag_rest_client)
        TextView stateTvItemRecycViewFragRestClient;
        @BindView(R.id.rating_bar_item_recyc_view_frag_rest_client)
        AppCompatRatingBar ratingBarItemRecycViewFragRestClient;
        @BindView(R.id.min_charge_tv_item_recyc_view_frag_rest_client)
        TextView minChargeTvItemRecycViewFragRestClient;
        @BindView(R.id.delivery_charge_tv_item_recyc_view_frag_rest_client)
        TextView deliveryChargeTvItemRecycViewFragRestClient;
        @BindView(R.id.iv_item_recyc_view_frag_rest_client)
        CircleImageView ivItemRecycViewFragRestClient;
        @BindString(R.string.open_state)
        String open_state;
        @BindString(R.string.closed_state)
        String closed_state;
        @BindDrawable(R.drawable.open_state_light)
        Drawable open_state_light;
        @BindDrawable(R.drawable.closed_state_light)
        Drawable closed_state_light;
        private final Context context;

        RestListViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = context;
        }

        private void setData(GeneralSourceData data) {
            MediaLoader.getInstance().load(ivItemRecycViewFragRestClient, data.getPhotoUrl());
            nameTvItemRecycViewFragRestClient.setText(data.getName());
            ratingBarItemRecycViewFragRestClient.setRating(data.getRate());
            ratingBarItemRecycViewFragRestClient.setEnabled(false);
            minChargeTvItemRecycViewFragRestClient.setText(context.getString(R.string.rest_min_charge
                    , ConvertStringToOtherFormat.getIntFromString(data.getMinimumCharger())));
            deliveryChargeTvItemRecycViewFragRestClient.setText(context
                    .getString(R.string.delivery_fees, ConvertStringToOtherFormat
                            .getIntFromString(data.getDeliveryCost())));
            if (data.getAvailability().equals("open")) {
                stateTvItemRecycViewFragRestClient.setText(open_state);
                stateIvItemRecycViewFragRestClient.setImageDrawable(open_state_light);
            } else {
                stateTvItemRecycViewFragRestClient.setText(closed_state);
                stateIvItemRecycViewFragRestClient.setImageDrawable(closed_state_light);
            }
        }

        private void setAction(GeneralSourceData data) {
            frameLayoutFragRestListClient.setOnClickListener(v -> {
                Fragment fragment = Objects.requireNonNull((AppCompatActivity) context)
                        .getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_list);
                if (fragment != null) {
                    SharedPreferenceManager.saveSelectedRestId((AppCompatActivity) context
                            , String.valueOf(data.getId()));
                    SharedPreferenceManager.saveSelectedRestDeliveryCost((AppCompatActivity) context
                            , data.getDeliveryCost());
                    ((AppCompatActivity) context).getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_list, new RestContainerFragment())
                            .addToBackStack(null)
                            .commit();
                }
            });

        }

    }

    private static final DiffUtil.ItemCallback<GeneralSourceData> SOURCE_DATA_COMPARATOR
            = new DiffUtil.ItemCallback<GeneralSourceData>() {
        @Override
        public boolean areItemsTheSame(@NonNull GeneralSourceData oldItem, @NonNull GeneralSourceData newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull GeneralSourceData oldItem, @NonNull GeneralSourceData newItem) {
            return oldItem.equals(newItem);
        }
    };


}
