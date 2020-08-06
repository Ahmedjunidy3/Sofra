package com.example.sofra.view.clientCycle.fragment.homeCycle.offers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.sofra.R;
import com.example.sofra.view.clientCycle.fragment.userCycle.ChangePassClientFragment;
import com.example.sofra.view.clientCycle.fragment.userCycle.ClientCustomLogOutDialogFragment;
import com.example.sofra.view.resturantCycle.fragment.homeCycle.general.AboutUsFragment;
import com.example.sofra.view.resturantCycle.fragment.homeCycle.general.ContactUsFragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MoreClientFragment extends Fragment {

    @BindView(R.id.reviews_tv_frag_more)
    TextView reviewsTvFragMore;
    @BindView(R.id.divider_view_frag_more)
    TextView dividerViewFragMore;
    private Unbinder unbinder;

    public MoreClientFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more
                , container, false);
        unbinder = ButterKnife.bind(this, view);
        hideView();
        return view;
    }

    private void hideView() {
        reviewsTvFragMore.setVisibility(View.GONE);
        dividerViewFragMore.setVisibility(View.GONE);
    }

    @OnClick({R.id.offers_tv_frag_more, R.id.contact_us_tv_frag_more, R.id.about_us_tv_frag_more, R.id.reviews_tv_frag_more, R.id.change_pass_tv_frag_more, R.id.log_out_tv_frag_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.offers_tv_frag_more: {
                Fragment fragment = requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_list);
                if (fragment != null) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_list, new OffersClientFragment())
                            .addToBackStack(null)
                            .commit();
                }
                break;
            }
            case R.id.contact_us_tv_frag_more: {
                Fragment fragment = requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_list);
                if (fragment != null) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_list, new ContactUsFragment())
                            .addToBackStack(null)
                            .commit();
                }
                break;
            }
            case R.id.about_us_tv_frag_more: {
                Fragment fragment = requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_list);
                if (fragment != null) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_list, new AboutUsFragment())
                            .addToBackStack(null)
                            .commit();
                }
                break;
            }
            case R.id.change_pass_tv_frag_more: {
                Fragment fragment = requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_rest_list);
                if (fragment != null) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_rest_list, new ChangePassClientFragment())
                            .addToBackStack(null)
                            .commit();
                }
                break;
            }
            case R.id.log_out_tv_frag_more: {
                ClientCustomLogOutDialogFragment dialog = new ClientCustomLogOutDialogFragment();
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
