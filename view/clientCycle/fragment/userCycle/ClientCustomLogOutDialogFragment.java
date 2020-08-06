package com.example.sofra.view.clientCycle.fragment.userCycle;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;


import com.example.sofra.R;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.view.splashCycle.activity.SplashActivity;

import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ClientCustomLogOutDialogFragment extends AppCompatDialogFragment {

    private AlertDialog alertDialog;
    private Unbinder unbinder;

    public ClientCustomLogOutDialogFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_custom_log_out
                , null, false);
        unbinder = ButterKnife.bind(this, view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        alertDialog = builder.create();
        Objects.requireNonNull(alertDialog.getWindow()).getDecorView().setBackgroundColor(Color.TRANSPARENT);
        return alertDialog;
    }


    @OnClick({R.id.yes_iv_dialog_custom_log_out, R.id.no_iv_dialog_custom_log_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.yes_iv_dialog_custom_log_out: {
                SharedPreferenceManager.removeClientEmail(getActivity());
                SharedPreferenceManager.removeClientPassword(getActivity());
                Intent intent = new Intent(getActivity(), SplashActivity.class);
                startActivity(intent);
                alertDialog.dismiss();
                break;
            }
            case R.id.no_iv_dialog_custom_log_out: {
                alertDialog.dismiss();
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
