package com.example.sofra.view.splashCycle.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.sofra.R;


import com.example.sofra.view.clientCycle.activity.homeCycle.RestListActivity;
import com.example.sofra.view.resturantCycle.activity.userCycle.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {

    @BindView(R.id.btn_buy_frag_main)
    Button btnBuyFragMain;
    @BindView(R.id.btn_sell_frag_main)
    Button btnSellFragMain;
    private Unbinder unbinder;

    public SplashFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_splash, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.btn_buy_frag_main, R.id.btn_sell_frag_main})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_buy_frag_main: {
                Intent intent = new Intent(getActivity(), RestListActivity.class);
                intent.putExtra("CATEGORY_KEY", "CLIENT");
                startActivity(intent);
                break;
            }
            case R.id.btn_sell_frag_main: {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.putExtra("CATEGORY_KEY", "RESTAURANT");
                startActivity(intent);
                break;
            }
        }
    }
}
