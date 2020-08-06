package com.example.sofra.view.resturantCycle.fragment.homeCycle.offers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RestNewOfferFragment extends Fragment {


    @BindView(R.id.tv_frag_new_offer_rest)
    TextView tvFragNewOfferRest;
    @BindView(R.id.iv_frag_new_offer_rest)
    RoundedImageView ivFragNewOfferRest;
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
    private String photoPath;
    private Unbinder unbinder;
    private OffersViewModel offersViewModel;

    public RestNewOfferFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MediaLoader.initAlbum(getActivity());
        offersViewModel = ViewModelProviders.of(this).get(OffersViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_offer_rest, container, false);
        unbinder = ButterKnife.bind(this, view);
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
                offersViewModel.setMyNewOfferRest(ConvertFileToParts.convertToRequestBody(descriptEtFragNewOfferRest.getText().toString().trim())
                        , ConvertFileToParts.convertToRequestBody(priceEtFragNewOfferRest.getText().toString().trim())
                        , ConvertFileToParts.convertToRequestBody(startDateEtFragNewOfferRest.getText().toString().trim())
                        , ConvertFileToParts.convertToRequestBody(nameEtFragNewOfferRest.getText().toString().trim())
                        , ConvertFileToParts.convertToMultiParts(photoPath, "photo")
                        , ConvertFileToParts.convertToRequestBody(endDateEtFragNewOfferRest.getText().toString().trim())
                        , ConvertFileToParts.convertToRequestBody(offerPriceEtFragNewOfferRest.getText().toString().trim())
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
