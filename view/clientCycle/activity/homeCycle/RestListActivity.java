package com.example.sofra.view.clientCycle.activity.homeCycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.sofra.R;
import com.example.sofra.view.clientCycle.fragment.homeCycle.offers.MoreClientFragment;
import com.example.sofra.view.clientCycle.fragment.homeCycle.order.ClientCompleteOrderFragment;
import com.example.sofra.view.clientCycle.fragment.homeCycle.order.ClientOrderDetailsFragment;
import com.example.sofra.view.clientCycle.fragment.homeCycle.order.ClientOrdersViewPagerFragment;
import com.example.sofra.view.clientCycle.fragment.homeCycle.restaurant.CartFragment;
import com.example.sofra.view.clientCycle.fragment.homeCycle.restaurant.RestListFragment;
import com.example.sofra.view.clientCycle.fragment.userCycle.ClientEditProfileFragment;
import com.example.sofra.view.clientCycle.fragment.userCycle.ClientNotificationsFragment;
import com.example.sofra.view.splashCycle.activity.SplashActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RestListActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.bottom_nav_activity_rest_list)
    BottomNavigationView bottomNavActivityRestList;
    private Fragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_list);
        ButterKnife.bind(this);
        fragment = getSupportFragmentManager()
                .findFragmentById(R.id.container_activity_rest_list);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        if (bundle.getString("CLIENT_NAME") != null) {
            if (fragment == null) {
                fragment = new ClientCompleteOrderFragment();
                fragment.setArguments(bundle);
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.container_activity_rest_list, fragment)
                        .commit();
            }
        } else {
            if (fragment == null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.container_activity_rest_list, new RestListFragment())
                        .commit();
            }
        }
        bottomNavActivityRestList.setOnNavigationItemSelectedListener(this);
    }

    @OnClick({R.id.notific_iv_activity_rest_list, R.id.shop_cart_iv_activity_rest_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.notific_iv_activity_rest_list: {
                Fragment fragment = getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_list);
                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_list, new ClientNotificationsFragment())
                            .addToBackStack(null)
                            .commit();
                }
                break;
            }
            case R.id.shop_cart_iv_activity_rest_list: {
                Fragment fragment = getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_list);
                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_list, new CartFragment())
                            .addToBackStack(null)
                            .commit();
                }
                break;
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_btn_nav_bottom: {
                Fragment fragment = getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_list);
                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_list, new RestListFragment())
                            .addToBackStack(null)
                            .commit();
                }
                return true;
            }
            case R.id.orders_btn_nav_bottom: {
                Fragment fragment = getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_list);
                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_list, new ClientOrdersViewPagerFragment())
                            .addToBackStack(null)
                            .commit();
                }
                return true;
            }
            case R.id.profile_btn_nav_bottom: {
                Fragment fragment = getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_list);
                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_list, new ClientEditProfileFragment())
                            .addToBackStack(null)
                            .commit();
                }
                return true;
            }
            case R.id.more_btn_nav_bottom: {
                Fragment fragment = getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_list);
                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_list, new MoreClientFragment())
                            .addToBackStack(null)
                            .commit();
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        fragment = getSupportFragmentManager().findFragmentById(R.id.container_activity_rest_list);

        if (fragment instanceof RestListFragment) {
            Intent intent = new Intent(RestListActivity.this, SplashActivity.class);
            startActivity(intent);
        } else if (fragment instanceof ClientOrderDetailsFragment) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_activity_rest_list, new ClientOrdersViewPagerFragment())
                    .addToBackStack(null)
                    .commit();
        } else if (fragment instanceof ClientCompleteOrderFragment) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_activity_rest_list, new CartFragment())
                    .addToBackStack(null)
                    .commit();
        } else if (fragment instanceof ClientOrdersViewPagerFragment) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_activity_rest_list, new RestListFragment())
                    .addToBackStack(null)
                    .commit();
        } else if (count == 0) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            getSupportFragmentManager().popBackStack();
        }

    }


}