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
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.view.resturantCycle.viewmodel.homeCycle.MoreViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ChangePassRestFragment extends Fragment {

    @BindView(R.id.old_pass_et_frag_change_pass)
    EditText oldPassEtFragChangePass;
    @BindView(R.id.new_pass_et_frag_change_pass)
    EditText newPassEtFragChangePass;
    @BindView(R.id.pass_confirm_et_frag_change_pass)
    EditText passConfirmEtFragChangePass;
    private MoreViewModel moreViewModel;
    private Unbinder unbinder;

    public ChangePassRestFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moreViewModel = ViewModelProviders.of(this).get(MoreViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_pass, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.change_btn_frag_change_pass)
    public void onViewClicked() {
            moreViewModel.setMyNewPassRest(SharedPreferenceManager.loadRestApiToken(getActivity())
                    , oldPassEtFragChangePass.getText().toString().trim()
                    , newPassEtFragChangePass.getText().toString().trim()
                    , passConfirmEtFragChangePass.getText().toString().trim());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
