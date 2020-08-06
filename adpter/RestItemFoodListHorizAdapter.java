package com.example.sofra.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofra.R;
import com.example.sofra.data.model.generalsource.GeneralSourceData;
import com.example.sofra.utility.MediaLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class RestItemFoodListHorizAdapter
        extends RecyclerView.Adapter<RestItemFoodListHorizAdapter.CategViewHolder> {

    private final Context context;
    private ArrayList<GeneralSourceData> categArrayList;
    private final OnFoodCategorySelectListener onCategorySelectListener;

    public RestItemFoodListHorizAdapter(Context context
            , OnFoodCategorySelectListener onCategorySelectListener) {
        this.context = context;
        this.onCategorySelectListener = onCategorySelectListener;
    }

    @NonNull
    @Override
    public CategViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_recyc_view_horiz_rest_item_food_list
                , parent, false);
        return new CategViewHolder(itemView, categArrayList, onCategorySelectListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategViewHolder holder, int position) {
        holder.setData(position);
        holder.setAction(position);
    }


    @Override
    public int getItemCount() {
        if (categArrayList == null) {
            categArrayList = new ArrayList<>();
        }
        return categArrayList.size();
    }

    public static class CategViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_recyc_view_rest_item_food_list_horiz)
        CircleImageView ivItemRecycViewRestItemFoodListHoriz;
        @BindView(R.id.title_tv_item_recyc_view_rest_item_food_list_horiz)
        TextView titleTvItemRecycViewRestItemFoodListHoriz;
        private final ArrayList<GeneralSourceData> categArrayList;
        private final OnFoodCategorySelectListener onCategorySelectListener;

        CategViewHolder(@NonNull View itemView, ArrayList<GeneralSourceData> categArrayList
                , OnFoodCategorySelectListener onCategorySelectListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.categArrayList = categArrayList;
            this.onCategorySelectListener = onCategorySelectListener;
        }

        private void setData(int position) {
            GeneralSourceData data = categArrayList.get(position);
            MediaLoader.getInstance().load(ivItemRecycViewRestItemFoodListHoriz, data.getPhotoUrl());
            titleTvItemRecycViewRestItemFoodListHoriz.setText(data.getName());
        }

        private void setAction(int position) {
            ivItemRecycViewRestItemFoodListHoriz.setOnClickListener(v -> onCategorySelectListener.showItemFoodList(categArrayList.get(position).getId()));
        }
    }

    public interface OnFoodCategorySelectListener {
        void showItemFoodList(Integer categoryId);
    }

    public void setCategDataToAdapter(List<GeneralSourceData> categories) {
        this.categArrayList = new ArrayList<>(categories);
    }


}
