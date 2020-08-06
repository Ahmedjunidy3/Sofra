package com.example.sofra.view.resturantCycle.fragment.homeCycle.categories;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.sofra.R;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.utility.ConvertFileToParts;
import com.example.sofra.utility.MediaLoader;
import com.example.sofra.view.resturantCycle.viewmodel.homeCycle.RestFoodCategViewModel;
import com.yanzhenjie.album.Album;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class CustomNewFoodCategDialog extends AppCompatDialogFragment {

    @BindView(R.id.title_tv_dialog_rest_food_categ)
    TextView titleTvDialogRestFoodCateg;
    @BindView(R.id.iv_dialog_rest_food_categ)
    CircleImageView ivDialogRestFoodCateg;
    @BindView(R.id.foodtype_et_dialog_rest_food_categ)
    EditText foodtypeEtDialogRestFoodCateg;
    @BindView(R.id.add_btn_dialog_rest_food_categ)
    Button addBtnDialogRestFoodCateg;
    private RestFoodCategViewModel restFoodCategViewModel;
    private AlertDialog alertDialog;
    private String photoPath;
    private Unbinder unbinder;

    public CustomNewFoodCategDialog() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MediaLoader.initAlbum(getActivity());
        restFoodCategViewModel = ViewModelProviders.of(this).get(RestFoodCategViewModel.class);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_custom_new_food_categ, null);
        unbinder = ButterKnife.bind(this, view);
        builder.setView(view);
        alertDialog = builder.create();
        return alertDialog;
    }

    @OnClick({R.id.iv_dialog_rest_food_categ, R.id.add_btn_dialog_rest_food_categ})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_dialog_rest_food_categ: {
                Album.image(this).singleChoice().onResult(result -> {
                    photoPath = result.get(0).getPath();
                    MediaLoader.getInstance().load(ivDialogRestFoodCateg, photoPath);
                }).start();
                break;
            }
            case R.id.add_btn_dialog_rest_food_categ: {
                restFoodCategViewModel.setRestNewFoodCateg(
                        ConvertFileToParts.convertToRequestBody(foodtypeEtDialogRestFoodCateg.getText().toString().trim())
                        , ConvertFileToParts.convertToMultiParts(photoPath, "photo")
                        , ConvertFileToParts.convertToRequestBody(SharedPreferenceManager.loadRestApiToken(getActivity())));
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


