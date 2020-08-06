package com.example.sofra.view.resturantCycle.fragment.userCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.sofra.R;
import com.example.sofra.utility.ConvertFileToParts;
import com.example.sofra.utility.MediaLoader;
import com.example.sofra.view.resturantCycle.viewmodel.userCycle.AuthViewModel;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.album.AlbumFile;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class RegisterFragmenttwo extends Fragment {


    @BindView(R.id.rest_phone_no_et_frag_reg_2)
    EditText restPhoneNoEtFragReg2;
    @BindView(R.id.rest_whats_app_no_et_frag_reg_2)
    EditText restWhatsAppNoEtFragReg2;
    @BindView(R.id.rest_iv_frag_reg_2)
    RoundedImageView restIvFragReg2;
    @BindView(R.id.rest_reg_btn_frag_reg_2)
    Button restRegBtnFragReg2;
    private String name;
    private String email;
    private String deliveryTime;
    private int zoneId;
    private String password;
    private String repassword;
    private String minimumdelivery;
    private String deliveryprice;
    private AuthViewModel authViewModel;
    private String selectedImagePath;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);
        Album.initialize(AlbumConfig.newBuilder(getActivity())
                .setAlbumLoader(new MediaLoader())
                .build());
        if (getArguments() != null) {
            name = getArguments().getString("REST_NAME");
            email = getArguments().getString("REST_EMAIL");
            deliveryTime = getArguments().getString("REST_TRANSP_TIME");
            zoneId = getArguments().getInt("REST_REGION_ID");
            password = getArguments().getString("REST_PASS");
            repassword = getArguments().getString("REST_PASS_CONFIRM");
            minimumdelivery = getArguments().getString("REST_MIN_CHARGE");
            deliveryprice = getArguments().getString("REST_TRANSP_CHARGE");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registertwo, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.rest_iv_frag_reg_2, R.id.rest_reg_btn_frag_reg_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rest_iv_frag_reg_2:
                Album.image(this).singleChoice().onResult(result -> {
                    AlbumFile imageFile = result.get(0);
                    MediaLoader mediaLoader = new MediaLoader();
                    mediaLoader.load(restIvFragReg2, imageFile);
                    selectedImagePath = imageFile.getPath();
                }).start();
                break;
            case R.id.rest_reg_btn_frag_reg_2:
                authViewModel.setRestSignedUpData(ConvertFileToParts.convertToRequestBody(name)
                        , ConvertFileToParts.convertToRequestBody(email)
                        , ConvertFileToParts.convertToRequestBody(password)
                        , ConvertFileToParts.convertToRequestBody(repassword)
                        , ConvertFileToParts.convertToRequestBody(restPhoneNoEtFragReg2.getText().toString().trim())
                        , ConvertFileToParts.convertToRequestBody(restWhatsAppNoEtFragReg2.getText().toString().trim())
                        , ConvertFileToParts.convertToRequestBody(String.valueOf(zoneId))
                        , ConvertFileToParts.convertToRequestBody(deliveryprice)
                        , ConvertFileToParts.convertToRequestBody(minimumdelivery)
                        , ConvertFileToParts.convertToMultiParts(selectedImagePath, "photo")
                        , ConvertFileToParts.convertToRequestBody(deliveryTime));
                break;

        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
