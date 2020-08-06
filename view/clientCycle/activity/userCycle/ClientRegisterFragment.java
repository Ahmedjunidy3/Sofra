package com.example.sofra.view.clientCycle.activity.userCycle;

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
import com.example.sofra.data.model.generalsource.GeneralSourceData;
import com.example.sofra.utility.ConvertFileToParts;
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

public class ClientRegisterFragment extends Fragment {
    @BindView(R.id.iv_frag_reg_client)
    CircleImageView ivFragRegClient;
    @BindView(R.id.name_et_frag_reg_client)
    EditText nameEtFragRegClient;
    @BindView(R.id.email_et_frag_reg_client)
    EditText emailEtFragRegClient;
    @BindView(R.id.phone_no_et_frag_reg_client)
    EditText phoneNoEtFragRegClient;
    @BindView(R.id.city_spin_frag_reg_client)
    Spinner citySpinFragRegClient;
    @BindView(R.id.region_spin_frag_reg_client)
    Spinner regionSpinFragRegClient;
    @BindView(R.id.pass_et_frag_reg_client)
    EditText passEtFragRegClient;
    @BindView(R.id.pass_confirm_et_frag_reg_client)
    EditText passConfirmEtFragRegClient;
    @BindView(R.id.register_btn_frag_reg_client)
    Button registerBtnFragRegClient;
    private AuthViewModel authViewModel;
    private ArrayList<GeneralSourceData> regionArrayList;
    private String imagePath;
    private Unbinder unbinder;

    public ClientRegisterFragment() {
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
        View view = inflater.inflate(R.layout.fragment_reg_client, container, false);
        unbinder = ButterKnife.bind(this, view);
        initCitySpin();
        return view;
    }

    private void initCitySpin() {
        authViewModel.getAllCities()
                .observe(requireActivity()
                        , cityData -> {
                            ArrayList<GeneralSourceData> cityArrayList = new ArrayList<>(cityData);
                            SpinAdapter citySpinAdapter = new SpinAdapter(getActivity(), cityArrayList);
                            citySpinFragRegClient.setAdapter(citySpinAdapter);
                        });
        citySpinFragRegClient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int cityId = position + 1;
                initRegionSpin(cityId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void initRegionSpin(int cityId) {
        authViewModel.getAllRegions(String.valueOf(cityId)).observe(this, cityData -> {
            regionArrayList = new ArrayList<>(cityData);
            SpinAdapter regionSpinAdapter = new SpinAdapter(getActivity(), regionArrayList);
            regionSpinFragRegClient.setAdapter(regionSpinAdapter);
        });

    }

    @OnClick({R.id.iv_frag_reg_client, R.id.register_btn_frag_reg_client})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_frag_reg_client: {
                Album.image(getActivity()).singleChoice().onResult(result -> {
                    imagePath = result.get(0).getPath();
                    MediaLoader.getInstance().load(ivFragRegClient, imagePath);
                }).start();
                break;
            }
            case R.id.register_btn_frag_reg_client: {
                authViewModel.setClientSignedUpDetails(ConvertFileToParts
                                .convertToRequestBody(nameEtFragRegClient.getText().toString().trim())
                        , ConvertFileToParts
                                .convertToRequestBody(emailEtFragRegClient.getText().toString().trim())
                        , ConvertFileToParts
                                .convertToRequestBody(passEtFragRegClient.getText().toString().trim())
                        , ConvertFileToParts
                                .convertToRequestBody(passConfirmEtFragRegClient.getText().toString().trim())
                        , ConvertFileToParts.convertToRequestBody(phoneNoEtFragRegClient.getText().toString().trim())
                        , ConvertFileToParts
                                .convertToRequestBody(String.valueOf(regionArrayList.get(regionSpinFragRegClient.getSelectedItemPosition()).getId()))
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


