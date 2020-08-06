package com.example.sofra.view.clientCycle.fragment.userCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.sofra.R;
import com.example.sofra.adpter.SpinAdapter;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.data.model.generalsource.GeneralSourceData;
import com.example.sofra.utility.ConvertFileToParts;
import com.example.sofra.utility.MediaLoader;
import com.example.sofra.view.resturantCycle.viewmodel.homeCycle.ProfileViewModel;
import com.example.sofra.view.resturantCycle.viewmodel.userCycle.AuthViewModel;
import com.yanzhenjie.album.Album;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class ClientEditProfileFragment extends Fragment {

    @BindView(R.id.iv_rest_profile_edit_s1)
    CircleImageView ivRestProfileEditS1;
    @BindView(R.id.name_et_frag_rest_profile_edit_s1)
    EditText nameEtFragRestProfileEditS1;
    @BindView(R.id.email_et_frag_rest_profile_edit_s1)
    EditText emailEtFragRestProfileEditS1;
    @BindView(R.id.phone_et_frag_rest_profile_edit_s1)
    EditText phoneEtFragRestProfileEditS1;
    @BindView(R.id.city_spin_frag_rest_profile_edit_s1)
    Spinner citySpinFragRestProfileEditS1;
    @BindView(R.id.reg_spin_frag_rest_profile_edit_s1)
    Spinner regSpinFragRestProfileEditS1;
    @BindView(R.id.min_charge_et_frag_rest_profile_edit_s1)
    EditText minChargeEtFragRestProfileEditS1;
    @BindView(R.id.next_btn_frag_rest_profile_edit_s1)
    Button nextBtnFragRestProfileEditS1;
    private Unbinder unbinder;
    private String imagePath;
    private AuthViewModel authViewModel;
    private ProfileViewModel profileViewModel;
    private int cityId;
    private ArrayList<GeneralSourceData> regionArrayList;
    private int defaultRegId;
    private String apiToken;

    public ClientEditProfileFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        MediaLoader.initAlbum(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_edit_s1_rest, container, false);
        unbinder = ButterKnife.bind(this, view);
        initCitySpin();
        setData();
        return view;
    }

    private void setData() {
        apiToken = SharedPreferenceManager.loadClientApiToken(getActivity());
        minChargeEtFragRestProfileEditS1.setVisibility(View.GONE);
        profileViewModel.getClientDetails(apiToken)
                .observe(getViewLifecycleOwner(), profileDetails -> {
                    MediaLoader.getInstance().load(ivRestProfileEditS1, profileDetails.getUser().getPhotoUrl());
                    nameEtFragRestProfileEditS1.setText(profileDetails.getUser().getName());
                    emailEtFragRestProfileEditS1.setText(profileDetails.getUser().getEmail());
                    phoneEtFragRestProfileEditS1.setText(profileDetails.getUser().getPhone());
                    citySpinFragRestProfileEditS1.setSelection((profileDetails.getUser().getRegion()
                            .getCity().getId()) - 1);
                    defaultRegId = profileDetails.getUser().getRegion().getId();
                });
    }

    private void initCitySpin() {
        authViewModel.getAllCities().observe(getViewLifecycleOwner(), cities -> {
            ArrayList<GeneralSourceData> cityArrayList = new ArrayList<>(cities);
            SpinAdapter citySpinAdapter = new SpinAdapter(getActivity(), cityArrayList);
            citySpinFragRestProfileEditS1.setAdapter(citySpinAdapter);
            citySpinFragRestProfileEditS1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    cityId = position + 1;
                    initRegionSpin();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        });
    }

    private void initRegionSpin() {
        authViewModel.getAllRegions(String.valueOf(cityId))
                .observe(this, (List<GeneralSourceData> regions) -> {
                    regionArrayList = new ArrayList<>(regions);
                    SpinAdapter regionSpinAdapter = new SpinAdapter(getActivity(), regionArrayList);
                    regSpinFragRestProfileEditS1.setAdapter(regionSpinAdapter);
                    setDefaultSelectedRegByClient(defaultRegId);
                });
    }

    private void setDefaultSelectedRegByClient(int defaultRegId) {
        for (int i = 0; i < regionArrayList.size(); i++) {
            if (regionArrayList.get(i).getId() == defaultRegId) {
                regSpinFragRestProfileEditS1.setSelection(i);
                break;
            }
        }
    }

    @OnClick({R.id.iv_rest_profile_edit_s1, R.id.next_btn_frag_rest_profile_edit_s1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_rest_profile_edit_s1: {
                Album.image(getActivity()).singleChoice().onResult(result -> {
                    imagePath = result.get(0).getPath();
                    MediaLoader.getInstance().load(ivRestProfileEditS1, imagePath);
                }).start();
                break;
            }
            case R.id.next_btn_frag_rest_profile_edit_s1: {
                profileViewModel.setClientProfileDetails(ConvertFileToParts.convertToRequestBody(apiToken)
                        , ConvertFileToParts.convertToRequestBody(nameEtFragRestProfileEditS1.getText().toString().trim())
                        , ConvertFileToParts.convertToRequestBody(phoneEtFragRestProfileEditS1.getText().toString().trim())
                        , ConvertFileToParts.convertToRequestBody(emailEtFragRestProfileEditS1.getText().toString().trim())
                        , ConvertFileToParts.convertToRequestBody(String.valueOf(regionArrayList
                                .get(regSpinFragRestProfileEditS1.getSelectedItemPosition()).getId()))
                        , ConvertFileToParts.convertToMultiParts(imagePath, "profile_image"));
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
