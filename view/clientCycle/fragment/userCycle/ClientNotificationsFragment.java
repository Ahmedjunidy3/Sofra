package com.example.sofra.view.clientCycle.fragment.userCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sofra.R;
import com.example.sofra.adpter.NotificationsAdapter;
import com.example.sofra.view.resturantCycle.viewmodel.homeCycle.NotificationsViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ClientNotificationsFragment extends Fragment {

    @BindView(R.id.rv_frag_notificat)
    RecyclerView rvFragNotificat;
    private Unbinder unbinder;
    private NotificationsViewModel notificationsViewModel;
    private NotificationsAdapter notificatAdapter;

    public ClientNotificationsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notificaitons, container, false);
        unbinder = ButterKnife.bind(this, view);
        initRecyclerView();
        getMyNotifications();
        return view;
    }

    private void initRecyclerView() {
        notificatAdapter = new NotificationsAdapter(getActivity());
        rvFragNotificat.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFragNotificat.setAdapter(notificatAdapter);
    }

    private void getMyNotifications() {
        notificationsViewModel
                .getClientNotifications("K1X6AzRlJFeVbGnHwGYsdCu0ETP1BqYC7DpMTZ3zLvKgU5feHMvsEEnKTpzh")
                .observe(getViewLifecycleOwner(), notifications -> {
                    notificatAdapter.setNotificationListToAdapter(notifications);
                    notificatAdapter.notifyDataSetChanged();
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
