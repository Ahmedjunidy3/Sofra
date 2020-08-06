package com.example.sofra.view.clientCycle.fragment.homeCycle.restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sofra.R;
import com.example.sofra.adpter.RestReviewsAdapter;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.view.resturantCycle.viewmodel.homeCycle.MoreViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RestClientReviewFragment extends Fragment {
    @BindView(R.id.tv_frag_reviews_rest)
    TextView tvFragReviewsRest;
    @BindView(R.id.evaluate_btn_frag_reviews_rest)
    Button evaluateBtnFragReviewsRest;
    @BindView(R.id.rv_frag_rest_reviews)
    RecyclerView rvFragRestReviews;
    private Unbinder unbinder;
    private MoreViewModel moreViewModel;
    private RestReviewsAdapter restReviewsAdapter;

    public RestClientReviewFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moreViewModel = ViewModelProviders.of(this).get(MoreViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rest_reviews, container, false);
        unbinder = ButterKnife.bind(this, view);
        initRecyclerView();
        setData();
        return view;
    }

    private void initRecyclerView() {
        restReviewsAdapter = new RestReviewsAdapter(getActivity());
        rvFragRestReviews.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFragRestReviews.setAdapter(restReviewsAdapter);
    }

    private void setData() {
        moreViewModel.getMyReviews(SharedPreferenceManager.loadClientApiToken(getActivity())
                , Integer.parseInt(SharedPreferenceManager.loadSelectedRestId(getActivity())))
                .observe(getViewLifecycleOwner(), reviews -> {
                    restReviewsAdapter.setDataToAdapter(reviews);
                    restReviewsAdapter.notifyDataSetChanged();
                });
    }

    @OnClick(R.id.evaluate_btn_frag_reviews_rest)
    public void onViewClicked() {
        ClientAddCustomReviewDialog addReviewDialog = new ClientAddCustomReviewDialog();
        addReviewDialog.show(getChildFragmentManager(), null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}

