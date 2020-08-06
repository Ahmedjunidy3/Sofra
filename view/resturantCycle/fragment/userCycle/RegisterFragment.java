package com.example.sofra.view.resturantCycle.fragment.userCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.sofra.R;
import com.example.sofra.adpter.SpinAdapter;
import com.example.sofra.data.model.generalsource.GeneralSourceData;
import com.example.sofra.view.resturantCycle.viewmodel.userCycle.AuthViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.sofra.utility.network.InternetState.isConnected;


public class RegisterFragment extends Fragment {


    @BindView(R.id.register_fragment_et_restaurant_name)
    EditText registerFragmentEtRestaurantName;
    @BindView(R.id.register_fragment_et_email)
    EditText registerFragmentEtEmail;
    @BindView(R.id.register_fragment_et_delivery_time)
    EditText registerFragmentEtDeliveryTime;
    @BindView(R.id.register_fragment_spinner_city)
    Spinner registerFragmentSpinnerCity;
    @BindView(R.id.register_fragment_spinner_zone)
    Spinner registerFragmentSpinnerZone;
    @BindView(R.id.register_fragment_et_password)
    EditText registerFragmentEtPassword;
    @BindView(R.id.register_fragment_et_repassword)
    EditText registerFragmentEtRepassword;
    @BindView(R.id.register_fragment_et_minimum_order)
    EditText registerFragmentEtMinimumOrder;
    @BindView(R.id.register_fragment_et_delivery_price)
    EditText registerFragmentEtDeliveryPrice;
    @BindView(R.id.register_fragment_btn_next)
    Button registerFragmentBtnNext;
    private AuthViewModel authViewModel;
    private ArrayList<GeneralSourceData> regionArrayList;
    private Unbinder unbinder;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
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
                            registerFragmentSpinnerCity.setAdapter(citySpinAdapter);
                        });
        registerFragmentSpinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            registerFragmentSpinnerZone.setAdapter(regionSpinAdapter);
        });

    }



    @OnClick(R.id.register_fragment_btn_next)
    public void onViewClicked() {
        if (isConnected(getActivity())) {
            if (registerFragmentEtRestaurantName.length() > 0 && registerFragmentEtEmail.length() > 0
                    && registerFragmentEtDeliveryTime.length() > 0 && registerFragmentEtPassword.length() > 0
                    && registerFragmentEtRepassword.length() > 0 && registerFragmentEtMinimumOrder.length() > 0
                    && registerFragmentEtDeliveryPrice.length() > 0) {
                GoToRegStage2(registerFragmentEtRestaurantName.getText().toString().trim()
                        , registerFragmentEtEmail.getText().toString().trim()
                        , registerFragmentEtDeliveryTime.getText().toString().trim()
                        , regionArrayList.get(registerFragmentSpinnerZone.getSelectedItemPosition()).getId()
                        , registerFragmentEtPassword.getText().toString().trim()
                        , registerFragmentEtRepassword.getText().toString().trim()
                        , registerFragmentEtMinimumOrder.getText().toString().trim()
                        , registerFragmentEtDeliveryPrice.getText().toString().trim());
            }

    }


}

    private void GoToRegStage2(String name, String email, String deliveryTime, Integer zoneId, String password, String repassword, String miniorder, String deliveryprice) {
        Fragment fragment = requireActivity().getSupportFragmentManager()
                .findFragmentById(R.id.container_activity_login);
        if (fragment != null) {
            fragment = new RegisterFragmenttwo();
            Bundle bundle = new Bundle();
            bundle.putString("REST_NAME", name);
            bundle.putString("REST_EMAIL", email);
            bundle.putString("REST_TRANSP_TIME", deliveryTime);
            bundle.putInt("REST_REGION_ID", zoneId);
            bundle.putString("REST_PASS", password);
            bundle.putString("REST_PASS_CONFIRM", repassword);
            bundle.putString("REST_MIN_CHARGE", miniorder);
            bundle.putString("REST_TRANSP_CHARGE", deliveryprice);
            fragment.setArguments(bundle);
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_activity_login, fragment)
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
