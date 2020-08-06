package com.example.sofra.adpter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.sofra.R;
import com.example.sofra.view.resturantCycle.fragment.homeCycle.orders.RestCurrentOrdersFragment;
import com.example.sofra.view.resturantCycle.fragment.homeCycle.orders.RestNewOrdersFragment;
import com.example.sofra.view.resturantCycle.fragment.homeCycle.orders.RestPrevOrdersFragment;


public class RestOrdersViewPagerAdapter extends FragmentPagerAdapter {
    private final Context context;

    public RestOrdersViewPagerAdapter(Context context, @NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new RestNewOrdersFragment();
            case 1:
                return new RestCurrentOrdersFragment();
            case 2:
                return new RestPrevOrdersFragment();
            default:
                return new RestNewOrdersFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        int[] TabLayoutTitles = {R.string.new_orders, R.string.current_orders, R.string.previous_orders};
        return context.getResources().getString(TabLayoutTitles[position]);
    }


}
