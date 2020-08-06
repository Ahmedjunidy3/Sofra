package com.example.sofra.view.resturantCycle.fragment.homeCycle.offers;

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

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class OffersRestFragment extends Fragment
        implements RestSwipeLayoutOffersAdapter.OnDeleteRecyItemListener {

    @BindView(R.id.rv_frag_offers)
    RecyclerView rvFragOffers;
    @BindView(R.id.add_btn_frag_offers)
    Button addBtnFragOffers;
    private OffersViewModel offersViewModel;
    private RestSwipeLayoutOffersAdapter offersAdapter;
    private String apiToken;
    private Unbinder unbinder;

    public OffersRestFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        offersViewModel = ViewModelProviders.of(this).get(OffersViewModel.class);
        apiToken = SharedPreferenceManager.loadRestApiToken(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offers, container, false);
        unbinder = ButterKnife.bind(this, view);
        initRecyclerView();
        initMyOffers();
        return view;
    }

    private void initRecyclerView() {
        offersAdapter = new RestSwipeLayoutOffersAdapter(getActivity());
        offersAdapter.onDeleteRecyItemListener = this;
        rvFragOffers.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFragOffers.setAdapter(offersAdapter);
    }

    private void initMyOffers() {
        offersViewModel.getMyOffersRest(apiToken).observe(getViewLifecycleOwner(), offersList -> {
            offersAdapter.setDataToAdapter(offersList);
            offersAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void deleteItem(int itemId) {
        offersAdapter.notifyDataSetChanged();
        offersViewModel.deleteOfferRest(apiToken, itemId);
    }

    @OnClick(R.id.add_btn_frag_offers)
    public void onViewClicked() {
        Fragment fragment = requireActivity().getSupportFragmentManager()
                .findFragmentById(R.id.container_activity_rest_food_category);
        if (fragment != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_activity_rest_food_category, new RestNewOfferFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }

        @Override
        public void onDestroyView () {
            super.onDestroyView();
            unbinder.unbind();
        }

    }
