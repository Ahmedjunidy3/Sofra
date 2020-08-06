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
import com.example.sofra.view.resturantCycle.fragment.homeCycle.categories.CustomEditFoodCategDialog;
import com.example.sofra.view.resturantCycle.fragment.homeCycle.fooditems.RestFoodItemsFragment;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestSwipeFoodCategAdapter extends RecyclerSwipeAdapter<RestSwipeFoodCategAdapter.ViewHolder> {
    private final Context context;
    private ArrayList<GeneralSourceData> categList;
    private final OnDeleteRecyItemListener onDeleteRecyItemListener;

    public RestSwipeFoodCategAdapter(Context context, OnDeleteRecyItemListener onDeleteRecyItemListener) {
        this.context = context;
        this.onDeleteRecyItemListener = onDeleteRecyItemListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_rest_food_categ,
                parent, false);
        return new ViewHolder(view, context, categList, onDeleteRecyItemListener);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(position);
        holder.setAction(position);
    }


    @Override
    public int getItemCount() {
        if (categList == null) {
            categList = new ArrayList<>();
        }
        return categList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe_layout_rest_food_categ;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.edit_iv_item_rv_food_categ)
        ImageView editIvItemRvFoodCateg;
        @BindView(R.id.remove_iv_item_rv_food_categ)
        ImageView removeIvItemRvFoodCateg;
        @BindView(R.id.food_type_iv_item_rv_food_categ)
        RoundedImageView foodTypeIvItemRvFoodCateg;
        @BindView(R.id.tv_item_rv_food_categ)
        TextView tvItemRvFoodCateg;
        @BindView(R.id.swipe_layout_rest_food_categ)
        SwipeLayout swipeLayoutRestFoodCateg;
        private final Context context;
        private final ArrayList<GeneralSourceData> categList;
        private final OnDeleteRecyItemListener onDeleteRecyItemListener;

        ViewHolder(View itemView, Context context, ArrayList<GeneralSourceData> categList
                , OnDeleteRecyItemListener onDeleteRecyItemListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = context;
            this.categList = categList;
            this.onDeleteRecyItemListener = onDeleteRecyItemListener;
        }

        private void setData(int position) {
            GeneralSourceData data = categList.get(position);
            tvItemRvFoodCateg.setText(data.getName());
            MediaLoader.getInstance().load(foodTypeIvItemRvFoodCateg, data.getPhotoUrl());
        }

        private void setAction(int position) {
            GeneralSourceData data = categList.get(position);
            Integer categId = data.getId();
            editIvItemRvFoodCateg.setOnClickListener(v -> {
                CustomEditFoodCategDialog dialog = new CustomEditFoodCategDialog(context
                        .getString(R.string.edit_food_categ_type_frag_food_categ)
                        , context.getString(R.string.edit), categId);
                dialog.show(((AppCompatActivity) context).getSupportFragmentManager(), null);
            });

            removeIvItemRvFoodCateg.setOnClickListener(v -> {
                categList.remove(position);
                onDeleteRecyItemListener.deleteItem(categId);
            });

            swipeLayoutRestFoodCateg.setOnClickListener(v -> {
                Fragment fragment = ((AppCompatActivity) context).getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_food_category);
                if (fragment != null) {
                    fragment = new RestFoodItemsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("FOOD_CATEGORY_ID", categId);
                    bundle.putString("FOOD_CATEGORY_TYPE", data.getName());
                    fragment.setArguments(bundle);
                    ((AppCompatActivity) context).getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_food_category, fragment)
                            .addToBackStack(null)
                            .commit();
                }

            });


        }

    }

    public interface OnDeleteRecyItemListener {
        void deleteItem(Integer categId);
    }

    public void setDataToAdapter(List<GeneralSourceData> categories) {
        this.categList = new ArrayList<>(categories);
    }

}


