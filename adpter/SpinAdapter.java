package com.example.sofra.adpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sofra.R;
import com.example.sofra.data.model.generalsource.GeneralSourceData;

import java.util.ArrayList;

public class SpinAdapter extends BaseAdapter {
    private final Context context;
    private ArrayList<GeneralSourceData> cityDataList;

    public SpinAdapter(Context context, ArrayList<GeneralSourceData> cityDataList) {
        this.context = context;
        this.cityDataList = cityDataList;
    }

    @Override
    public int getCount() {
        if (cityDataList == null) {
            cityDataList = new ArrayList<>();
        }
        return cityDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return cityDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_spinner, parent, false);
        TextView spinTv = itemView.findViewById(R.id.rest_spin_tv_reg_s1);
        spinTv.setText(cityDataList.get(position).getName());
        return itemView;
    }
}
