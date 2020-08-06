package com.example.sofra.view.resturantCycle.fragment.homeCycle.orders;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModelProviders;


import com.example.sofra.R;
import com.example.sofra.view.resturantCycle.viewmodel.homeCycle.OrdersViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CustomRejectOrderDialog extends AppCompatDialogFragment {
    @BindView(R.id.reject_reason_et_dialog_order_state)
    EditText rejectReasonEtDialogOrderState;
    @BindView(R.id.cancel_btn_dialog_order_state)
    Button cancelBtnDialogOrderState;
    private AlertDialog alertDialog;
    private OrdersViewModel ordersViewModel;
    private int orderId;
    private Unbinder unbinder;

    public CustomRejectOrderDialog(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ordersViewModel = ViewModelProviders.of(this).get(OrdersViewModel.class);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_custom_order_reject, null);
        unbinder = ButterKnife.bind(this, view);
        builder.setView(view);
        alertDialog = builder.create();
        return alertDialog;
    }

    @OnClick(R.id.cancel_btn_dialog_order_state)
    public void onViewClicked() {
        String apiToken = "Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx";
        ordersViewModel.rejectCustomerOrder(apiToken, orderId
                , rejectReasonEtDialogOrderState.getText().toString().trim());
        alertDialog.dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
