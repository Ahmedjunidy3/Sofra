package com.example.sofra.view.resturantCycle.fragment.userCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


import com.example.sofra.R;
import com.example.sofra.view.resturantCycle.viewmodel.userCycle.ForgetPassViewModel;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ResetPassFragment extends Fragment {

    @BindView(R.id.email_et_frag_reset_pass)
    EditText emailEtFragResetPass;
    private ForgetPassViewModel forgetPassViewModel;
    private Unbinder unbinder;
    private String categKey;

    public ResetPassFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        forgetPassViewModel = ViewModelProviders.of(this).get(ForgetPassViewModel.class);
        if (getArguments() != null) {
            categKey = getArguments().getString("CATEGORY_KEY");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reset_pass
                , container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.send_btn_frag_reset_pass)
    public void onViewClicked() {
        if (categKey.equals("RESTAURANT")) {
            forgetPassViewModel.setRestResetPass(emailEtFragResetPass.getText().toString().trim());
            IfEmailRegisteredGoToChangePassFragment();
        } else {
            forgetPassViewModel.setClientResetPass(emailEtFragResetPass.getText().toString().trim());
            IfEmailRegisteredGoToChangePassFragment();
        }

    }

    private void IfEmailRegisteredGoToChangePassFragment() {
        forgetPassViewModel.getSuccessfulResetMsg().observe(this, msg -> {
            if (msg != null) {
                Fragment fragment = requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_login);
                if (fragment != null) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_login, new NewPassFragment())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
