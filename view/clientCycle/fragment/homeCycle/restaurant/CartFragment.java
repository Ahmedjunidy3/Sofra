package com.example.sofra.view.clientCycle.fragment.homeCycle.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sofra.R;
import com.example.sofra.adpter.CartAdapter;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.data.local.room.FoodItem;
import com.example.sofra.utility.ConvertStringToOtherFormat;
import com.example.sofra.view.clientCycle.viewmodel.homeCycle.CartViewModel;
import com.example.sofra.view.resturantCycle.activity.userCycle.LoginActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CartFragment extends Fragment
        implements CartAdapter.OnDeleteCartItemListener
, CartAdapter.OnQtyChangeListener {

    @BindView(R.id.rv_frag_cart)
    RecyclerView rvFragCart;
    @BindView(R.id.total_price_btn_frag_cart)
    Button sumPriceBtnFragCart;
    @BindView(R.id.order_confirm_btn_frag_cart)
    Button orderConfirmBtnFragCart;
    @BindView(R.id.add_items_btn_frag_cart)
    Button addItemsBtnFragCart;
    private Unbinder unbinder;
    private CartViewModel cartViewModel;
    private double sumPrice = 0;
    private CartAdapter cartAdapter;

    public CartFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        unbinder = ButterKnife.bind(this, view);
        initRecyclerView();
        setData();
        return view;
    }

    private void initRecyclerView() {
        cartAdapter = new CartAdapter(getActivity(), this, this);
        rvFragCart.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFragCart.setAdapter(cartAdapter);
    }

    @OnClick({R.id.order_confirm_btn_frag_cart, R.id.add_items_btn_frag_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.order_confirm_btn_frag_cart: {
                cartViewModel.getMyFoodItems().observe(this, foodItems -> {
                    if (foodItems.size() == 0) {
                        Toast.makeText(getActivity(), requireActivity().getString(R.string.no_orders_in_the_cart)
                                , Toast.LENGTH_SHORT).show();
                    }
                    else if (foodItems.size() > 3) {
                        orderConfirmBtnFragCart.setEnabled(false);
                        Toast.makeText(getActivity(), requireActivity()
                                .getString(R.string.max_items_no_per_order), Toast.LENGTH_SHORT).show();
                    } else {
                        SharedPreferenceManager.saveClientOrderSumPrice(getActivity(), String.valueOf(sumPrice));
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.putExtra("CATEGORY_KEY", "CLIENT");
                        startActivity(intent);
                    }
                });

                break;
            }
            case R.id.add_items_btn_frag_cart: {
                cartViewModel.getMyFoodItems().observe(this, foodItems -> {
                    Fragment fragment = requireActivity().getSupportFragmentManager()
                            .findFragmentById(R.id.container_activity_rest_list);
                    if (fragment != null) {
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container_activity_rest_list, new RestListFragment())
                                .addToBackStack(null)
                                .commit();
                    }
                });
                break;
            }
        }
    }

    @Override
    public void deleteItem(int id) {
        cartViewModel.deleteSelectedItemFromCart(id);
        setData();
    }

    private void setData() {
        cartViewModel.getMyFoodItems().observe(getViewLifecycleOwner(), foodItems -> {
            cartAdapter.setDataToAdapter(foodItems);
            cartAdapter.notifyDataSetChanged();
            setTotalPrice(foodItems);
        });
    }

    @Override
    public void getNewQtyList(ArrayList<FoodItem> newQtyArrayList) {
        setTotalPrice(newQtyArrayList);
    }

    private void setTotalPrice(List<FoodItem> foodItems) {
        ArrayList<FoodItem> cartItemsArrayList = new ArrayList<>(foodItems);
        sumPrice = 0;
        for (int i = 0; i < cartItemsArrayList.size(); i++) {
            double price = (ConvertStringToOtherFormat
                    .getDoubleFromString(cartItemsArrayList.get(i).getPrice()))
                    * ConvertStringToOtherFormat.getIntFromString(cartItemsArrayList.get(i).getQty());
            sumPrice += price;
        }
        sumPriceBtnFragCart.setText(requireActivity()
                .getString(R.string.price_value2, getActivity().getString(R.string.saudi_riyal)
                        , String.valueOf(sumPrice)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
