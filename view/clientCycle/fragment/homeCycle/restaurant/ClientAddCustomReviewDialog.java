package com.example.sofra.view.clientCycle.fragment.homeCycle.restaurant;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModelProviders;


import com.example.sofra.R;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.view.resturantCycle.viewmodel.homeCycle.MoreViewModel;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ClientAddCustomReviewDialog extends AppCompatDialogFragment {
    @BindView(R.id.very_satisf_iv_dialog_client_add_review)
    ImageView verySatisfIvDialogClientAddReview;
    @BindView(R.id.satisf_iv_dialog_client_add_review)
    ImageView satisfIvDialogClientAddReview;
    @BindView(R.id.neutral_iv_dialog_client_add_review)
    ImageView neutralIvDialogClientAddReview;
    @BindView(R.id.dissatisf_iv_dialog_client_add_review)
    ImageView dissatisfIvDialogClientAddReview;
    @BindView(R.id.very_dissatisf_iv_dialog_client_add_review)
    ImageView veryDissatisfIvDialogClientAddReview;
    @BindView(R.id.review_et_dialog_client_add_review)
    EditText reviewEtDialogClientAddReview;
    @BindView(R.id.add_btn_dialog_client_add_review)
    Button addBtnDialogClientAddReview;
    private AlertDialog alertDialog;
    private MoreViewModel moreViewModel;
    private Unbinder unbinder;
    private String rate;

    public ClientAddCustomReviewDialog() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moreViewModel = ViewModelProviders.of(this).get(MoreViewModel.class);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_custom_client_add_review, null);
        unbinder = ButterKnife.bind(this, view);
        builder.setView(view);
        alertDialog = builder.create();
        Objects.requireNonNull(alertDialog.getWindow()).getDecorView()
                .setBackgroundResource(android.R.color.transparent);
        setTagsForIVs();
        return alertDialog;
    }

    private void setTagsForIVs() {
        veryDissatisfIvDialogClientAddReview.setTag(R.drawable.ic_emotion_very_dissatisfied);
        dissatisfIvDialogClientAddReview.setTag(R.drawable.ic_emotion_dissatisfied);
        neutralIvDialogClientAddReview.setTag(R.drawable.ic_emotion_neutral);
        satisfIvDialogClientAddReview.setTag(R.drawable.ic_emotion_satisfied);
        verySatisfIvDialogClientAddReview.setTag(R.drawable.ic_emotion_very_satisfied);
    }

    @OnClick({R.id.very_satisf_iv_dialog_client_add_review, R.id.satisf_iv_dialog_client_add_review
            , R.id.neutral_iv_dialog_client_add_review, R.id.dissatisf_iv_dialog_client_add_review
            , R.id.very_dissatisf_iv_dialog_client_add_review, R.id.add_btn_dialog_client_add_review})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.very_satisf_iv_dialog_client_add_review: {
                rate = "5";
                if (verySatisfIvDialogClientAddReview.getTag().equals(R.drawable.ic_emotion_very_satisfied)) {
                    verySatisfIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_very_satisfied_green);
                    verySatisfIvDialogClientAddReview.setTag(R.drawable.ic_emotion_very_satisfied_green);
                    resetOtherIVToDefault(verySatisfIvDialogClientAddReview);
                } else {
                    verySatisfIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_very_satisfied);
                    verySatisfIvDialogClientAddReview.setTag(R.drawable.ic_emotion_very_satisfied);
                }
                break;
            }
            case R.id.satisf_iv_dialog_client_add_review: {
                rate = "4";
                if (satisfIvDialogClientAddReview.getTag().equals(R.drawable.ic_emotion_satisfied)) {
                    satisfIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_satisfied_green);
                    satisfIvDialogClientAddReview.setTag(R.drawable.ic_emotion_satisfied_green);
                    resetOtherIVToDefault(satisfIvDialogClientAddReview);
                } else {
                    satisfIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_satisfied);
                    satisfIvDialogClientAddReview.setTag(R.drawable.ic_emotion_satisfied);
                }
                break;
            }
            case R.id.neutral_iv_dialog_client_add_review: {
                rate = "3";
                if (neutralIvDialogClientAddReview.getTag().equals(R.drawable.ic_emotion_neutral)) {
                    neutralIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_neutral_green);
                    neutralIvDialogClientAddReview.setTag(R.drawable.ic_emotion_neutral_green);
                    resetOtherIVToDefault(neutralIvDialogClientAddReview);
                } else {
                    neutralIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_neutral);
                    neutralIvDialogClientAddReview.setTag(R.drawable.ic_emotion_neutral);
                }
                break;
            }
            case R.id.dissatisf_iv_dialog_client_add_review: {
                rate = "2";
                if (dissatisfIvDialogClientAddReview.getTag().equals(R.drawable.ic_emotion_dissatisfied)) {
                    dissatisfIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_dissatisfied_green);
                    dissatisfIvDialogClientAddReview.setTag(R.drawable.ic_emotion_dissatisfied_green);
                    resetOtherIVToDefault(dissatisfIvDialogClientAddReview);
                } else {
                    dissatisfIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_dissatisfied);
                    dissatisfIvDialogClientAddReview.setTag(R.drawable.ic_emotion_dissatisfied);
                }
                break;
            }
            case R.id.very_dissatisf_iv_dialog_client_add_review: {
                rate = "1";
                if (veryDissatisfIvDialogClientAddReview.getTag().equals(R.drawable.ic_emotion_very_dissatisfied)) {
                    veryDissatisfIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_very_dissatisfied_green);
                    veryDissatisfIvDialogClientAddReview.setTag(R.drawable.ic_emotion_very_dissatisfied_green);
                    resetOtherIVToDefault(veryDissatisfIvDialogClientAddReview);
                } else {
                    veryDissatisfIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_very_dissatisfied);
                    veryDissatisfIvDialogClientAddReview.setTag(R.drawable.ic_emotion_very_dissatisfied);
                }
                break;
            }
            case R.id.add_btn_dialog_client_add_review: {
                moreViewModel.addClientReview(rate
                        , reviewEtDialogClientAddReview.getText().toString().trim()
                        , SharedPreferenceManager.loadSelectedRestId(getActivity())
                        , SharedPreferenceManager.loadClientApiToken(getActivity()));
                alertDialog.dismiss();
                break;
            }
        }
    }

    private void resetOtherIVToDefault(ImageView iv) {
        if (iv == dissatisfIvDialogClientAddReview) {
            satisfIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_satisfied);
            verySatisfIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_very_satisfied);
            neutralIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_neutral);
            veryDissatisfIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_very_dissatisfied);
        }
        if (iv == veryDissatisfIvDialogClientAddReview) {
            satisfIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_satisfied);
            verySatisfIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_very_satisfied);
            neutralIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_neutral);
            dissatisfIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_dissatisfied);
        }
        if (iv == neutralIvDialogClientAddReview) {
            satisfIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_satisfied);
            verySatisfIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_very_satisfied);
            veryDissatisfIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_very_dissatisfied);
            dissatisfIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_very_dissatisfied);
        }
        if (iv == satisfIvDialogClientAddReview) {
            veryDissatisfIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_very_dissatisfied);
            verySatisfIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_very_satisfied);
            neutralIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_neutral);
            dissatisfIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_dissatisfied);
        }
        if (iv == verySatisfIvDialogClientAddReview) {
            satisfIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_satisfied);
            veryDissatisfIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_very_dissatisfied);
            neutralIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_neutral);
            dissatisfIvDialogClientAddReview.setImageResource(R.drawable.ic_emotion_dissatisfied);
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
