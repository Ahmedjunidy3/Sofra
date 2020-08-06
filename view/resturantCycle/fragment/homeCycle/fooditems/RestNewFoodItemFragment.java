package com.example.sofra.view.resturantCycle.fragment.homeCycle.fooditems;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


import com.example.sofra.R;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.utility.ConvertFileToParts;
import com.example.sofra.utility.MediaLoader;
import com.example.sofra.view.resturantCycle.viewmodel.homeCycle.RestFoodItemsViewModel;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RestNewFoodItemFragment extends Fragment {

    @BindView(R.id.add_iv_frag_new_food_item)
    RoundedImageView addIvFragNewFoodItem;
    @BindView(R.id.name_et_frag_new_food_item)
    EditText nameEtFragNewFoodItem;
    @BindView(R.id.descript_et_frag_new_food_item)
    EditText descriptEtFragNewFoodItem;
    @BindView(R.id.price_et_frag_new_food_item)
    EditText priceEtFragNewFoodItem;
    @BindView(R.id.price_in_offer_et_frag_new_food_item)
    EditText priceInOfferEtFragNewFoodItem;
    @BindView(R.id.add_btn_frag_new_food_item)
    Button addBtnFragNewFoodItem;
    private String imagePath;
    private int categId;
    private Unbinder unbinder;
    private RestFoodItemsViewModel restFoodItemsViewModel;

    public RestNewFoodItemFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MediaLoader.initAlbum(getActivity());
        restFoodItemsViewModel = ViewModelProviders.of(this).get(RestFoodItemsViewModel.class);
        if (getArguments() != null) {
            categId = getArguments().getInt("FOOD_CATEGORY_ID");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_food_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @OnClick({R.id.add_iv_frag_new_food_item, R.id.add_btn_frag_new_food_item})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_iv_frag_new_food_item: {
                Album.image(getActivity()).singleChoice().onResult(result -> {
                    imagePath = result.get(0).getPath();
                    MediaLoader.getInstance().load(addIvFragNewFoodItem, imagePath);
                }).start();
                break;
            }
            case R.id.add_btn_frag_new_food_item: {
                restFoodItemsViewModel.setRestNewFoodItem(ConvertFileToParts.convertToRequestBody(descriptEtFragNewFoodItem.getText().toString().trim())
                        , ConvertFileToParts.convertToRequestBody(priceEtFragNewFoodItem.getText().toString().trim())
                        , ConvertFileToParts.convertToRequestBody(nameEtFragNewFoodItem.getText().toString().trim())
                        , ConvertFileToParts.convertToMultiParts(imagePath, "photo")
                        , ConvertFileToParts.convertToRequestBody(SharedPreferenceManager.loadRestApiToken(getActivity()))
                        , ConvertFileToParts.convertToRequestBody(priceInOfferEtFragNewFoodItem.getText().toString().trim())
                        , ConvertFileToParts.convertToRequestBody(String.valueOf(categId)));
                break;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
