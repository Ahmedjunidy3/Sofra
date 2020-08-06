package com.example.sofra.view.resturantCycle.activity.userCycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.sofra.R;
import com.example.sofra.view.resturantCycle.fragment.userCycle.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.container_activity_login);
        if (fragment == null) {
            fragment = new LoginFragment();
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                fragment.setArguments(bundle);
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.container_activity_login, fragment)
                        .commit();
                return;
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_activity_login, fragment)
                    .commit();
        }
    }
}
