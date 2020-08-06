package com.example.sofra.view.resturantCycle.fragment.homeCycle.general;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


import com.example.sofra.R;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.utility.ConvertStringToOtherFormat;
import com.example.sofra.view.resturantCycle.viewmodel.homeCycle.RestCommissionViewModel;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RestCommissionFragment extends Fragment {
    @BindView(R.id.title_tv_frag_commission_rest)
    TextView titleTvFragCommissionRest;
    @BindView(R.id.rest_sales_tv_frag_commission_rest)
    TextView restSalesTvFragCommissionRest;
    @BindView(R.id.app_commiss_tv_frag_commission_rest)
    TextView appCommissTvFragCommissionRest;
    @BindView(R.id.paid_commiss_tv_frag_commission_rest)
    TextView paidCommissTvFragCommissionRest;
    @BindView(R.id.left_commiss_tv_frag_commission_rest)
    TextView leftCommissTvFragCommissionRest;
    @BindView(R.id.iv_frag_commission_rest)
    ImageView ivFragCommissionRest;
    private RestCommissionViewModel restCommissionViewModel;
    private Unbinder unbinder;

    public RestCommissionFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restCommissionViewModel = ViewModelProviders.of(this).get(RestCommissionViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_commission_rest, container, false);
        unbinder = ButterKnife.bind(this, view);
        initCommission();
        return view;
    }

    private void initCommission() {
        restCommissionViewModel.getRestCommission(SharedPreferenceManager.loadRestApiToken(getActivity()))
                .observe(getViewLifecycleOwner(), commission -> {
                    titleTvFragCommissionRest.setText(requireActivity()
                            .getString(R.string.subtitle_text_frag_commission_rest
                                    , ConvertStringToOtherFormat.getPercentFromString(commission.getCommission())));
                    restSalesTvFragCommissionRest.setText(getActivity().getString(R.string.rest_sales_frag_commission_rest
                            , commission.getTotal()));
                    appCommissTvFragCommissionRest.setText(getActivity().getString(R.string.app_commiss_frag_commission_rest
                            , commission.getCommissions()));
                    paidCommissTvFragCommissionRest.setText(getActivity().getString(R.string.paid_commiss_frag_commission_rest
                            , commission.getPayments()));
                    leftCommissTvFragCommissionRest.setText(getActivity().getString(R.string.unpaid_commiss_frag_commission_rest
                            , String.valueOf(commission.getNetCommissions())));
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
