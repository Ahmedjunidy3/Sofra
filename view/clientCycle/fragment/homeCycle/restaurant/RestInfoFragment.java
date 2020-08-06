package com.example.sofra.view.clientCycle.fragment.homeCycle.restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


import com.example.sofra.R;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.utility.ConvertStringToOtherFormat;
import com.example.sofra.view.clientCycle.viewmodel.homeCycle.RestListViewModel;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RestInfoFragment extends Fragment {
    @BindView(R.id.state_tv_item_list_view_frag_rest_info)
    TextView stateTvItemListViewFragRestInfo;
    @BindView(R.id.city_tv_item_list_view_frag_rest_info)
    TextView cityTvItemListViewFragRestInfo;
    @BindView(R.id.region_tv_item_list_view_frag_rest_info)
    TextView regionTvItemListViewFragRestInfo;
    @BindView(R.id.min_charge_tv_item_list_view_frag_rest_info)
    TextView minChargeTvItemListViewFragRestInfo;
    @BindView(R.id.delivery_cost_tv_item_list_view_frag_rest_info)
    TextView deliveryCostTvItemListViewFragRestInfo;
    private Unbinder unbinder;
    private RestListViewModel restListViewModel;

    public RestInfoFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restListViewModel = ViewModelProviders.of(this).get(RestListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rest_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        setData();
        return view;
    }

    private void setData() {
        restListViewModel.getRestInfoForClient(Integer.valueOf(SharedPreferenceManager
                .loadSelectedRestId(getActivity()))).observe(getViewLifecycleOwner(), info -> {
            if (info.getAvailability().equals("open")) {
                stateTvItemListViewFragRestInfo.setText(requireActivity()
                        .getString(R.string.state_open));
            } else {
                stateTvItemListViewFragRestInfo.setText(requireActivity()
                        .getString(R.string.state_closed));
            }
            cityTvItemListViewFragRestInfo.setText(getActivity().getString(R.string.city
                    , info.getRegion().getCity().getName()));
            regionTvItemListViewFragRestInfo.setText(getActivity().getString(R.string.region
                    , info.getRegion().getName()));
            minChargeTvItemListViewFragRestInfo.setText(getActivity().getString(R.string.min_charge
                    , ConvertStringToOtherFormat.getIntFromString(info.getMinimumCharger())));
            deliveryCostTvItemListViewFragRestInfo.setText(getActivity().getString(R.string.delivery_fees
                    , ConvertStringToOtherFormat.getIntFromString(info.getDeliveryCost())));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
