package com.example.sofra.view.resturantCycle.fragment.userCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


import com.example.sofra.R;
import com.example.sofra.view.resturantCycle.viewmodel.userCycle.ForgetPassViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class NewPassFragment extends Fragment {

    @BindView(R.id.pin_code_et_frag_new_pass)
    EditText pinCodeEtFragNewPass;
    @BindView(R.id.new_pass_et_frag_new_pass)
    EditText newPassEtFragNewPass;
    @BindView(R.id.pass_confirm_et_frag_new_pass)
    EditText passConfirmEtFragNewPass;
    @BindView(R.id.login_btn_frag_new_pass)
    Button loginBtnFragNewPass;
    private ForgetPassViewModel forgetPassViewModel;
    private Unbinder unbinder;

    public NewPassFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        forgetPassViewModel = ViewModelProviders.of(this).get(ForgetPassViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_pass, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.login_btn_frag_new_pass)
    public void onViewClicked() {
        forgetPassViewModel.setClientNewPass(pinCodeEtFragNewPass.getText().toString().trim()
                , newPassEtFragNewPass.getText().toString().trim()
                , passConfirmEtFragNewPass.getText().toString().trim());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
