package com.example.sofra.view.resturantCycle.fragment.homeCycle.general;

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
import com.example.sofra.view.resturantCycle.viewmodel.homeCycle.MoreViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ContactUsFragment extends Fragment {

    @BindView(R.id.name_frag_contact_us)
    EditText nameFragContactUs;
    @BindView(R.id.email_frag_contact_us)
    EditText emailFragContactUs;
    @BindView(R.id.phone_frag_contact_us)
    EditText phoneFragContactUs;
    @BindView(R.id.message_frag_contact_us)
    EditText messageFragContactUs;
    private Unbinder unbinder;
    private MoreViewModel moreViewModel;
    private String type;

    public ContactUsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moreViewModel = ViewModelProviders.of(this).get(MoreViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.complaint_rb_frag_contact_us, R.id.suggest_rb_frag_contact_us, R.id.inquiry_rb_frag_contact_us, R.id.send_btn_frag_contact_us})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.complaint_rb_frag_contact_us: {
                type = "complaint";
                break;
            }
            case R.id.suggest_rb_frag_contact_us: {
                type = "suggestion";
                break;
            }
            case R.id.inquiry_rb_frag_contact_us:
                type = "inquiry";
                break;
            case R.id.send_btn_frag_contact_us: {
                moreViewModel.setMyContactDetails(nameFragContactUs.getText().toString().trim()
                        , emailFragContactUs.getText().toString().trim()
                        , phoneFragContactUs.getText().toString().trim()
                        , type
                        , messageFragContactUs.getText().toString().trim());
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
