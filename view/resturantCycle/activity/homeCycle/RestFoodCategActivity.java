package com.example.sofra.view.resturantCycle.activity.homeCycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.example.sofra.R;

import com.example.sofra.view.resturantCycle.fragment.homeCycle.general.MoreRestFragment;
import com.example.sofra.view.resturantCycle.fragment.homeCycle.general.RestCommissionFragment;
import com.example.sofra.view.resturantCycle.fragment.homeCycle.general.RestNotificationsFragment;
import com.example.sofra.view.resturantCycle.fragment.homeCycle.categories.RestFoodCategFragment;
import com.example.sofra.view.resturantCycle.fragment.homeCycle.orders.RestOrderDetailsFragment;
import com.example.sofra.view.resturantCycle.fragment.homeCycle.orders.RestOrdersViewPagerFragment;
import com.example.sofra.view.resturantCycle.fragment.userCycle.RestEditProfileS1Fragment;
import com.example.sofra.view.splashCycle.activity.SplashActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RestFoodCategActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.rest_bottom_nav_activity_food_category)
    BottomNavigationView restBottomNavActivityFoodCategory;
    @BindView(R.id.notific_iv_activity_rest_list)
    ImageView notificIvActivityRestList;
    @BindView(R.id.commission_iv_activity_rest_food_categ)
    ImageView commissionIvActivityRestFoodCateg;
    private Fragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_food_category);
        ButterKnife.bind(this);
        fragment = getSupportFragmentManager()
                .findFragmentById(R.id.container_activity_rest_food_category);
        if (fragment == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_activity_rest_food_category, new RestFoodCategFragment())
                    .commit();
        }
        restBottomNavActivityFoodCategory.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_btn_nav_bottom: {
                Fragment fragment = getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_food_category);
                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_food_category, new RestFoodCategFragment())
                            .addToBackStack(null)
                            .commit();
                }
                break;
            }
            case R.id.orders_btn_nav_bottom: {
                Fragment fragment = getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_food_category);
                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_food_category, new RestOrdersViewPagerFragment())
                            .addToBackStack(null)
                            .commit();
                }
                break;
            }
            case R.id.profile_btn_nav_bottom: {
                Fragment fragment = getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_food_category);
                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_food_category, new RestEditProfileS1Fragment())
                            .addToBackStack(null)
                            .commit();
                }
                break;
            }
            case R.id.more_btn_nav_bottom: {
                Fragment fragment = getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_food_category);
                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_food_category, new MoreRestFragment())
                            .addToBackStack(null)
                            .commit();
                }
                break;
            }

        }
        return false;
    }

    @OnClick({R.id.notific_iv_activity_rest_list, R.id.commission_iv_activity_rest_food_categ})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.notific_iv_activity_rest_list: {
                Fragment fragment = getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_food_category);
                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_food_category, new RestNotificationsFragment())
                            .addToBackStack(null)
                            .commit();
                }
                break;
            }
            case R.id.commission_iv_activity_rest_food_categ: {
                Fragment fragment = getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_food_category);
                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_food_category, new RestCommissionFragment())
                            .addToBackStack(null)
                            .commit();
                    break;
                }
            }
        }


    }

    @Override
    public void onBackPressed() {
        fragment = getSupportFragmentManager().findFragmentById(R.id.container_activity_rest_food_category);
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (fragment instanceof RestFoodCategFragment) {
            Intent intent = new Intent(this, SplashActivity.class);
            startActivity(intent);
        }
        else if (fragment instanceof RestOrderDetailsFragment) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_activity_rest_food_category, new RestOrdersViewPagerFragment())
                    .commit();
        } else if (fragment instanceof RestOrdersViewPagerFragment) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_activity_rest_food_category, new RestFoodCategFragment())
                    .commit();
        }
        else if (count == 0) {
            Intent intent = new Intent(this, SplashActivity.class);
            startActivity(intent);
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

}