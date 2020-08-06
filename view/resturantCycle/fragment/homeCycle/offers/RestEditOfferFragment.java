package com.example.sofra.view.resturantCycle.fragment.homeCycle.offers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


import com.example.sofra.R;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.utility.ConvertFileToParts;
import com.example.sofra.utility.MediaLoader;
import com.example.sofra.view.resturantCycle.viewmodel.homeCycle.OffersViewModel;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yanzhenjie.album.Album;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RestEditOfferFragment extends Fragment {


    @BindView(R.id.tv_frag_new_offer_rest)
    TextView tvFragNewOfferRest;
    @BindView(R.id.iv_frag_new_offer_rest)
    RoundedImageView ivFragNewOfferRest;
    OffersViewModel offersViewModel;
    @BindView(R.id.name_et_frag_new_offer_rest)
    EditText nameEtFragNewOfferRest;
    @BindView(R.id.descript_et_frag_new_offer_rest)
    EditText descriptEtFragNewOfferRest;
    @BindView(R.id.start_date_et_frag_new_offer_rest)
    EditText startDateEtFragNewOfferRest;
    @BindView(R.id.end_date_et_frag_new_offer_rest)
    EditText endDateEtFragNewOfferRest;
    @BindView(R.id.price_et_frag_new_offer_rest)
    EditText priceEtFragNewOfferRest;
    @BindView(R.id.offer_price_et_frag_new_offer_rest)
    EditText offerPriceEtFragNewOfferRest;
    @BindView(R.id.add_btn_frag_new_offer_rest)
    Button addBtnFragNewOfferRest;
    private String photoPath;
    private int offerItemId;
    private Unbinder unbinder;

    public RestEditOfferFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        offersViewModel = ViewModelProviders.of(this).get(OffersViewModel.class);
        if (getArguments() != null) {
            offerItemId = getArguments().getInt("EDIT_OFFER_ITEM_ID");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_offer_rest, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvFragNewOfferRest.setText(requireActivity()
                .getString(R.string.offer_image_frag_edit_offer_rest));
        addBtnFragNewOfferRest.setText(getActivity().getString(R.string.edit));
        return view;
    }


    @OnClick({R.id.iv_frag_new_offer_rest, R.id.add_btn_frag_new_offer_rest})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_frag_new_offer_rest: {
                Album.image(this).singleChoice().onResult(result -> {
                    photoPath = result.get(0).getPath();
                    MediaLoader.getInstance().load(ivFragNewOfferRest, photoPath);
                }).start();
                break;
            }
            case R.id.add_btn_frag_new_offer_rest: {
                offersViewModel.editMyOfferRest(ConvertFileToParts.convertToRequestBody(descriptEtFragNewOfferRest.getText().toString().trim())
                        , ConvertFileToParts.convertToRequestBody(priceEtFragNewOfferRest.getText().toString().trim())
                        , ConvertFileToParts.convertToRequestBody(startDateEtFragNewOfferRest.getText().toString().trim())
                        , ConvertFileToParts.convertToRequestBody(nameEtFragNewOfferRest.getText().toString().trim())
                        , ConvertFileToParts.convertToMultiParts(photoPath, "photo")
                        , ConvertFileToParts.convertToRequestBody(endDateEtFragNewOfferRest.getText().toString().trim())
                        , ConvertFileToParts.convertToRequestBody(String.valueOf(offerItemId))
                        , ConvertFileToParts.convertToRequestBody(SharedPreferenceManager.loadRestApiToken(getActivity())));
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
