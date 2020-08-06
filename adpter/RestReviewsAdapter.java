package com.example.sofra.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sofra.R;
import com.example.sofra.data.model.generalsource.GeneralSourceData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestReviewsAdapter extends RecyclerView.Adapter<RestReviewsAdapter.ReviewsViewHolder> {
    private final Context context;
    private ArrayList<GeneralSourceData> reviewsArrayList;

    public RestReviewsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_recyc_view_review_rest
                , parent, false);
        return new ReviewsViewHolder(itemView, reviewsArrayList);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsViewHolder holder, int position) {
        holder.setData(position);
    }


    @Override
    public int getItemCount() {
        if (reviewsArrayList == null) {
            reviewsArrayList = new ArrayList<>();
        }
        return reviewsArrayList.size();
    }

    public static class ReviewsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name_tv_item_recyc_view_review_rest)
        TextView nameTvItemRecycViewReviewRest;
        @BindView(R.id.review_tv_item_recyc_view_review_rest)
        TextView reviewTvItemRecycViewReviewRest;
        private final ArrayList<GeneralSourceData> dataArrayList;

        ReviewsViewHolder(@NonNull View itemView, ArrayList<GeneralSourceData> dataArrayList) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.dataArrayList = dataArrayList;
        }

        private void setData(int position) {
            nameTvItemRecycViewReviewRest.setText(dataArrayList.get(position).getClient().getName());
            reviewTvItemRecycViewReviewRest.setText(dataArrayList.get(position).getComment());
        }

    }

    public void setDataToAdapter(List<GeneralSourceData> reviews) {
        this.reviewsArrayList = new ArrayList<>(reviews);
    }


}
