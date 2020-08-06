package com.example.sofra.view.clientCycle.fragment.homeCycle.restaurant;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sofra.R;
import com.example.sofra.adpter.RestPagedListAdapter;
import com.example.sofra.view.clientCycle.viewmodel.homeCycle.RestListViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RestListFragment extends Fragment {
    @BindView(R.id.region_spin_frag_rest_list_client)
    Spinner regionSpinFragRestListClient;
    @BindView(R.id.search_et_frag_rest_list_client)
    EditText searchEtFragRestListClient;
    @BindView(R.id.rv_frag_rest_list_client)
    RecyclerView rvFragRestListClient;
    private Unbinder unbinder;
    private RestListViewModel restListViewModel;
    private RestPagedListAdapter pagedListAdapter;

    public RestListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restListViewModel = ViewModelProviders.of(this).get(RestListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rest_list
                , container, false);
        unbinder = ButterKnife.bind(this, view);
        initRecyclerView();
        setData();
        setEditTextListener();
        return view;
    }

    private void setEditTextListener() {
        searchEtFragRestListClient.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @SuppressLint("FragmentLiveDataObserve")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                restListViewModel.getFilteredRestPagedList(s.toString().trim()
                        , null).observe(RestListFragment.this
                        , filteredPagedList ->
                                pagedListAdapter.submitList(filteredPagedList));

                if (count == 0) {
                    setData();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initRecyclerView() {
        pagedListAdapter = new RestPagedListAdapter(getActivity());
        rvFragRestListClient.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFragRestListClient.setAdapter(pagedListAdapter);
    }

    private void setData() {
        restListViewModel.getRestPagedList().observe(getViewLifecycleOwner(), pagedList -> pagedListAdapter.submitList(pagedList));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
