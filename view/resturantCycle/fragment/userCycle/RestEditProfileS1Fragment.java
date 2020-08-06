package com.example.sofra.view.resturantCycle.fragment.userCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


import com.example.sofra.R;
import com.example.sofra.adpter.SpinAdapter;
import com.example.sofra.data.model.generalsource.GeneralSourceData;
import com.example.sofra.utility.MediaLoader;
import com.example.sofra.view.resturantCycle.viewmodel.userCycle.AuthViewModel;
import com.yanzhenjie.album.Album;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class RestEditProfileS1Fragment extends Fragment {
    @BindView(R.id.iv_rest_profile_edit_s1)
    CircleImageView ivRestProfileEditS1;
    @BindView(R.id.name_et_frag_rest_profile_edit_s1)
    EditText nameEtFragRestProfileEditS1;
    @BindView(R.id.email_et_frag_rest_profile_edit_s1)
    EditText emailEtFragRestProfileEditS1;
    @BindView(R.id.city_spin_frag_rest_profile_edit_s1)
    Spinner citySpinFragRestProfileEditS1;
    @BindView(R.id.reg_spin_frag_rest_profile_edit_s1)
    Spinner regSpinFragRestProfileEditS1;
    @BindView(R.id.min_charge_et_frag_rest_profile_edit_s1)
    EditText minChargeEtFragRestProfileEditS1;
    @BindView(R.id.phone_et_frag_rest_profile_edit_s1)
    EditText phoneEtFragRestProfileEditS1;
    private AuthViewModel authViewModel;
    private String photoPath;
    private ArrayList<GeneralSourceData> regionArrayList;
    private Unbinder unbinder;

    public RestEditProfileS1Fragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MediaLoader.initAlbum(getActivity());
        authViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_edit_s1_rest
                , container, false);
        unbinder = ButterKnife.bind(this, view);
        initCitySpin();
        return view;
    }

    private void initCitySpin() {
        phoneEtFragRestProfileEditS1.setVisibility(View.GONE);
        authViewModel.getAllCities().observe(getViewLifecycleOwner(), cities -> {
            ArrayList<GeneralSourceData> cityArrayList = new ArrayList<>(cities);
            SpinAdapter citySpinAdapter = new SpinAdapter(getActivity(), cityArrayList);
            citySpinFragRestProfileEditS1.setAdapter(citySpinAdapter);
            citySpinFragRestProfileEditS1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int cityId = position + 1;
                    initRegionSpin(cityId);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        });
    }

    private void initRegionSpin(int cityId) {
        authViewModel.getAllRegions(String.valueOf(cityId))
                .observe(this, regions -> {
                    regionArrayList = new ArrayList<>(regions);
                    SpinAdapter regionSpinAdapter = new SpinAdapter(getActivity(), regionArrayList);
                    regSpinFragRestProfileEditS1.setAdapter(regionSpinAdapter);
                });
    }

    @OnClick({R.id.iv_rest_profile_edit_s1, R.id.next_btn_frag_rest_profile_edit_s1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_rest_profile_edit_s1: {
                Album.image(this).singleChoice().onResult(result -> {
                    photoPath = result.get(0).getPath();
                    MediaLoader.getInstance().load(ivRestProfileEditS1, photoPath);
                }).start();
                break;
            }
            case R.id.next_btn_frag_rest_profile_edit_s1: {
                if (nameEtFragRestProfileEditS1.length() > 0 && emailEtFragRestProfileEditS1.length() > 0
                        && minChargeEtFragRestProfileEditS1.length() > 0) {
                    GoToProfileS2(nameEtFragRestProfileEditS1.getText().toString().trim()
                            , emailEtFragRestProfileEditS1.getText().toString().trim()
                            , regionArrayList.get(regSpinFragRestProfileEditS1.getSelectedItemPosition()).getId()
                            , minChargeEtFragRestProfileEditS1.getText().toString().trim());
                    break;
                }

            }
        }
    }

    private void GoToProfileS2(String name, String email, int regionId, String minCharge) {
        Fragment fragment = requireActivity().getSupportFragmentManager()
                .findFragmentById(R.id.container_activity_rest_food_category);
        if (fragment != null) {
            fragment = new RestEditProfileS2Fragment();
            Bundle bundle = new Bundle();
            bundle.putString("PROFILE_NAME", name);
            bundle.putString("PROFILE_EMAIL", email);
            bundle.putInt("PROFILE_REGION_ID", regionId);
            bundle.putString("PROFILE_MIN_CHARGE", minCharge);
            bundle.putString("PROFILE_PHOTO", photoPath);
            fragment.setArguments(bundle);
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_activity_rest_food_category, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
