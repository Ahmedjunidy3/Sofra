package com.example.sofra.adpter;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.sofra.R;
import com.example.sofra.view.clientCycle.fragment.homeCycle.order.RestContainerFragment;
import com.example.sofra.view.clientCycle.fragment.homeCycle.restaurant.RestClientReviewFragment;
import com.example.sofra.view.clientCycle.fragment.homeCycle.restaurant.RestInfoFragment;
import com.example.sofra.view.clientCycle.fragment.homeCycle.restaurant.RestItemFoodListFragment;


public class RestContainerAdapter extends FragmentPagerAdapter {
    private final Context context;

    public RestContainerAdapter(Context context, @NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new RestItemFoodListFragment();
            case 1:
                return new RestClientReviewFragment();
            case 2:
                return new RestInfoFragment();
            default:
                return new RestItemFoodListFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        int[] TabLayoutTitles = {R.string.food_list, R.string.reviews, R.string.rest_info};
        return context.getResources().getString(TabLayoutTitles[position]);
    }


}
