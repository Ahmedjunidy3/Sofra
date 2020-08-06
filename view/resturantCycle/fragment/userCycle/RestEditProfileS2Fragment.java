package com.example.sofra.view.resturantCycle.fragment.userCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


import com.example.sofra.R;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.utility.ConvertFileToParts;
import com.example.sofra.view.resturantCycle.viewmodel.homeCycle.ProfileViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RestEditProfileS2Fragment extends Fragment {
    @BindView(R.id.delivery_charge_et_frag_rest_profile_edit_s2)
    EditText deliveryChargeEtFragRestProfileEditS2;
    @BindView(R.id.delivery_time_et_frag_rest_profile_edit_s2)
    EditText deliveryTimeEtFragRestProfileEditS2;
    @BindView(R.id.sw_btn_frag_rest_profile_edit_s2)
    Switch SwBtnFragRestProfileEditS2;
    @BindView(R.id.phone_no_et_frag_rest_profile_edit_s2)
    EditText phoneNoEtFragRestProfileEditS2;
    @BindView(R.id.whats_app_no_et_frag_rest_profile_edit_s2)
    EditText whatsAppNoEtFragRestProfileEditS2;
    @BindView(R.id.edit_btn_frag_rest_profile_edit_s2)
    Button editS2BtnFragRestProfileEditS2;
    private String name;
    private String email;
    private int regionId;
    private String minCharge;
    private String photoPath;
    private ProfileViewModel profileViewModel;
    private Unbinder unbinder;
    private String restId;
    private String availability;

    public RestEditProfileS2Fragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        restId = SharedPreferenceManager.loadRestId(getActivity());
        if (getArguments() != null) {
            name = getArguments().getString("PROFILE_NAME");
            email = getArguments().getString("PROFILE_EMAIL");
            regionId = getArguments().getInt("PROFILE_REGION_ID");
            minCharge = getArguments().getString("PROFILE_MIN_CHARGE");
            photoPath = getArguments().getString("PROFILE_PHOTO");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_edit_s2_rest
                , container, false);
        unbinder = ButterKnife.bind(this, view);
        checkRestState();
        return view;
    }

    private void checkRestState() {
        profileViewModel.getMyAvailabilityState(Integer.valueOf(restId))
                .observe(getViewLifecycleOwner(), state -> {
                    availability = state.getAvailability();
                    if (availability.equals("open")) {
                        SwBtnFragRestProfileEditS2.setChecked(true);
                    } else {
                        SwBtnFragRestProfileEditS2.setChecked(false);
                    }
                });
    }

    @OnClick(R.id.edit_btn_frag_rest_profile_edit_s2)
    public void onViewClicked() {
        profileViewModel.setMyProfileDetailsRest(ConvertFileToParts.convertToRequestBody(email)
                , ConvertFileToParts.convertToRequestBody(name)
                , ConvertFileToParts.convertToRequestBody(phoneNoEtFragRestProfileEditS2.getText().toString().trim())
                , ConvertFileToParts.convertToRequestBody(String.valueOf(regionId))
                , ConvertFileToParts.convertToRequestBody(deliveryChargeEtFragRestProfileEditS2.getText().toString().trim())
                , ConvertFileToParts.convertToRequestBody(minCharge)
                , ConvertFileToParts.convertToRequestBody(availability)
                , ConvertFileToParts.convertToMultiParts(photoPath, "photo")
                , ConvertFileToParts.convertToRequestBody(SharedPreferenceManager.loadRestApiToken(getActivity()))
                , ConvertFileToParts.convertToRequestBody(deliveryTimeEtFragRestProfileEditS2.getText().toString().trim()));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
