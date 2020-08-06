package com.example.sofra.view.clientCycle.fragment.homeCycle.offers;

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
import com.example.sofra.view.clientCycle.fragment.homeCycle.order.RestContainerFragment;
import com.example.sofra.view.resturantCycle.viewmodel.homeCycle.OffersViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class OfferDetailsFragment extends Fragment {

    @BindView(R.id.name_tv_frag_offer_details)
    TextView nameTvFragOfferDetails;
    @BindView(R.id.description_tv_frag_offer_details)
    TextView descriptionTvFragOfferDetails;
    @BindView(R.id.date_from_tv_frag_offer_details)
    TextView dateFromTvFragOfferDetails;
    @BindView(R.id.date_to_tv_frag_offer_details)
    TextView dateToTvFragOfferDetails;
    private Unbinder unbinder;
    private int offerId;
    private OffersViewModel offersViewModel;

    public OfferDetailsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        offersViewModel = ViewModelProviders.of(this).get(OffersViewModel.class);
        if (getArguments() != null) {
            offerId = getArguments().getInt("SELECTED_OFFER_ID");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offer_details, container, false);
        unbinder = ButterKnife.bind(this, view);
        setData();
        return view;
    }

    private void setData() {
        offersViewModel.getOfferDetailsClient(offerId).observe(getViewLifecycleOwner(), offer -> {
            nameTvFragOfferDetails.setText(offer.getName());
            descriptionTvFragOfferDetails.setText(offer.getDescription());
            dateFromTvFragOfferDetails.setText(requireActivity()
                    .getString(R.string.offer_start_from_date, offer.getStartingAt()));
            dateToTvFragOfferDetails.setText(getActivity().getString(R.string.offer_end_on_date
                    , offer.getEndingAt()));
        });

    }

    @OnClick(R.id.get_btn_frag_offer_details)
    public void onViewClicked() {
        Fragment fragment = requireActivity().getSupportFragmentManager()
                .findFragmentById(R.id.container_activity_rest_list);
        if (fragment != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_activity_rest_list, new RestContainerFragment())
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
