package com.example.sofra.view.resturantCycle.fragment.userCycle;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.sofra.R;
import com.example.sofra.data.local.SharedPreferenceManager;

import com.example.sofra.view.resturantCycle.viewmodel.userCycle.AuthViewModel;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.sofra.utility.HelperMethod.replaceFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    @BindView(R.id.login_fragment_et_number)
    EditText loginFragmentEtNumber;
    @BindView(R.id.login_fragment_et_password)
    EditText loginFragmentEtPassword;
    @BindView(R.id.login_fragment_tv_forgetpass)
    TextView loginFragmentTvForgetpass;
    @BindView(R.id.login_fragment_btn_login)
    Button loginFragmentBtnLogin;
    @BindView(R.id.login_fragment_tv_register)
    TextView loginFragmentTvRegister;
    private AuthViewModel authViewModel;
    private String categKey;
    private Unbinder unbinder;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);
        if (getArguments() != null) {
            categKey = getArguments().getString("CATEGORY_KEY");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        checkLoginDetails();
        setClickableSpanText();
        return view;


    }

    private void setClickableSpanText() {
        String savedEmailRest = SharedPreferenceManager.loadRestEmail(getActivity());
        String savedPassRest = SharedPreferenceManager.loadRestPassword(getActivity());
        String savedEmailClient = SharedPreferenceManager.loadClientEmail(getActivity());
        String savedPassClient = SharedPreferenceManager.loadClientPassword(getActivity());
        assert categKey != null;
        if (categKey.equals("RESTAURANT")) {
            if (savedEmailRest != null && savedPassRest != null) {
                loginFragmentEtNumber.setText(savedEmailRest);
                loginFragmentEtPassword.setText(savedPassRest);
                authViewModel.getLogin(getEmail(), getPassword());

            }
        } else {
            if (savedEmailClient != null && savedPassClient != null) {
                loginFragmentEtNumber.setText(savedEmailClient);
                loginFragmentEtPassword.setText(savedPassClient);
                authViewModel.setClientLoginDetails(getEmail(), getPassword());

            }
        }
    }

    private void checkLoginDetails() {
        SpannableString ss = new SpannableString(loginFragmentTvRegister.getText());
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                ds.setColor(Color.RED);
                ds.setUnderlineText(false);
            }

            @Override
            public void onClick(@NonNull View widget) {
                if (categKey.equals("RESTAURANT")) {
                    Fragment fragment = requireActivity().getSupportFragmentManager()
                            .findFragmentById(R.id.container_activity_login);
                    replaceFragment(getChildFragmentManager(), R.id.container_activity_login, new RegisterFragment());
                } else {
                    replaceFragment(getChildFragmentManager(), R.id.container_activity_login, new LoginFragment());
                }

            }
        };
        ss.setSpan(clickableSpan, 20, 23, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        loginFragmentTvRegister.setText(ss);
        loginFragmentTvRegister.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @OnClick({R.id.login_fragment_tv_forgetpass, R.id.login_fragment_btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_fragment_tv_forgetpass:
                      break;
            case R.id.login_fragment_btn_login:
                if (categKey.equals("RESTAURANT")) {
                    SharedPreferenceManager.saveRestEmail(getActivity(), getEmail());
                    SharedPreferenceManager.saveRestPassword(getActivity(), getPassword());
                    authViewModel.getLogin(getEmail(), getPassword());
                } else {
                    SharedPreferenceManager.saveClientEmail(getActivity(), getEmail());
                    SharedPreferenceManager.saveClientPassword(getActivity(), getPassword());
                    authViewModel.setClientLoginDetails(getEmail(), getPassword());
                }
                break;

        }
    }
    private String getPassword() {
        return loginFragmentEtPassword.getText().toString().trim();
    }

    private String getEmail() {
        return loginFragmentEtNumber.getText().toString().trim();
    }
    private void registerMyDeviceTokenRest() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                return;
            }
            String token = Objects.requireNonNull(task.getResult()).getToken();
            authViewModel.setMyDeviceTokenRest(token, "android"
                    , SharedPreferenceManager.loadRestApiToken(getActivity()));
//            Intent intent = new Intent(Intent.ACTION_SEND);
//            intent.setType("text/plain");
//            intent.putExtra(Intent.EXTRA_SUBJECT, "My Device Token");
//            intent.putExtra(Intent.EXTRA_TEXT, token);
//            startActivity(Intent.createChooser(intent, null));
        });
    }

    private void registerMyDeviceTokenClient() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                return;
            }
            String token = Objects.requireNonNull(task.getResult()).getToken();
            authViewModel.setMyDeviceTokenClient(token, "android"
                    , SharedPreferenceManager.loadClientApiToken(getActivity()));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
