package com.example.sofra.view.resturantCycle.fragment.homeCycle.general;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sofra.R;
import com.example.sofra.view.resturantCycle.fragment.homeCycle.general.AboutUsFragment;
import com.example.sofra.view.resturantCycle.fragment.homeCycle.general.ContactUsFragment;
import com.example.sofra.view.resturantCycle.fragment.homeCycle.general.RestReviewsFragment;
import com.example.sofra.view.resturantCycle.fragment.homeCycle.offers.OffersRestFragment;
import com.example.sofra.view.resturantCycle.fragment.userCycle.ChangePassRestFragment;
import com.example.sofra.view.resturantCycle.fragment.userCycle.RestCustomLogOutDialogFragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MoreRestFragment extends Fragment {
    @BindView(R.id.offers_tv_frag_more)
    TextView offersTvFragMore;
    private Unbinder unbinder;

    public MoreRestFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more
                , container, false);
        unbinder = ButterKnife.bind(this, view);
        setData();
        return view;
    }

    private void setData() {
        offersTvFragMore.setText(R.string.my_offers);
    }

    @OnClick({R.id.offers_tv_frag_more, R.id.contact_us_tv_frag_more, R.id.about_us_tv_frag_more, R.id.reviews_tv_frag_more, R.id.change_pass_tv_frag_more, R.id.log_out_tv_frag_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.offers_tv_frag_more: {
                Fragment fragment = requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_food_category);
                if (fragment != null) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_food_category, new OffersRestFragment())
                            .addToBackStack(null)
                            .commit();
                }
                break;
            }
            case R.id.contact_us_tv_frag_more: {
                Fragment fragment = requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_food_category);
                if (fragment != null) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_food_category, new ContactUsFragment())
                            .addToBackStack(null)
                            .commit();
                }
                break;
            }
            case R.id.about_us_tv_frag_more: {
                Fragment fragment = requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_food_category);
                if (fragment != null) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_food_category, new AboutUsFragment())
                            .addToBackStack(null)
                            .commit();
                }
                break;
            }
            case R.id.reviews_tv_frag_more: {
                Fragment fragment = requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_food_category);
                if (fragment != null) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_food_category, new RestReviewsFragment())
                            .addToBackStack(null)
                            .commit();
                }
                break;
            }
            case R.id.change_pass_tv_frag_more: {
                Fragment fragment = requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_food_category);
                if (fragment != null) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_food_category, new ChangePassRestFragment())
                            .addToBackStack(null)
                            .commit();
                }
                break;
            }
            case R.id.log_out_tv_frag_more: {
                RestCustomLogOutDialogFragment dialog = new RestCustomLogOutDialogFragment();
                dialog.show(requireActivity().getSupportFragmentManager()
                        , null);
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
