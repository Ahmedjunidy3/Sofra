package com.example.sofra.view.clientCycle.fragment.homeCycle.offers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sofra.R;
import com.example.sofra.adpter.RestSwipeLayoutOffersAdapter;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.view.resturantCycle.viewmodel.homeCycle.OffersViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OffersClientFragment extends Fragment {

    @BindView(R.id.rv_frag_offers)
    RecyclerView rvFragOffers;
    @BindView(R.id.add_btn_frag_offers)
    Button addBtnFragOffers;
    private Unbinder unbinder;
    private OffersViewModel offersViewModel;
    private RestSwipeLayoutOffersAdapter offersAdapter;

    public OffersClientFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        offersViewModel = ViewModelProviders.of(this).get(OffersViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offers, container, false);
        unbinder = ButterKnife.bind(this, view);
        initRecyclerView();
        initSelectedRestOffers();
        hideView();
        return view;
    }

    private void initRecyclerView() {
        offersAdapter = new RestSwipeLayoutOffersAdapter(getActivity());
        rvFragOffers.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFragOffers.setAdapter(offersAdapter);
    }

    private void initSelectedRestOffers() {
        offersViewModel.getOffersForSelectedRest(SharedPreferenceManager.loadSelectedRestId(getActivity()))
                .observe(getViewLifecycleOwner(), offersList -> {
                    offersAdapter.setDataToAdapter(offersList);
                    offersAdapter.notifyDataSetChanged();
                });
    }

    private void hideView() {
        addBtnFragOffers.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
