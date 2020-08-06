package com.example.sofra.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sofra.R;
import com.example.sofra.data.model.generalsource.GeneralSourceData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationsAdapter extends
        RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder> {
    private final Context context;
    private ArrayList<GeneralSourceData> notificatArrayList;

    public NotificationsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_recyc_view_notification_rest
                , parent, false);
        return new NotificationsViewHolder(itemView, notificatArrayList);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsViewHolder holder, int position) {
        holder.setData(position);
        holder.setAction();
    }

    @Override
    public int getItemCount() {
        if (notificatArrayList == null) {
            notificatArrayList = new ArrayList<>();
        }
        return notificatArrayList.size();
    }

    public static class NotificationsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.notificat_iv_recyc_view_item_notification)
        ImageView notificatIvRecycViewItemNotification;
        @BindView(R.id.title_recyc_view_item_notification)
        TextView titleRecycViewItemNotification;
        @BindView(R.id.time_iv_recyc_view_item_notification)
        ImageView timeIvRecycViewItemNotification;
        @BindView(R.id.time_tv_recyc_view_item_notification)
        TextView timeTvRecycViewItemNotification;
        private final ArrayList<GeneralSourceData> notificatArrayList;

        NotificationsViewHolder(@NonNull View itemView
                , ArrayList<GeneralSourceData> notificatArrayList) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.notificatArrayList = notificatArrayList;
            notificatIvRecycViewItemNotification.setTag(R.drawable.ic_notificat_unread);
        }

        private void setData(int position) {
            GeneralSourceData data = notificatArrayList.get(position);
            titleRecycViewItemNotification.setText(data.getTitle());
            timeTvRecycViewItemNotification.setText(data.getCreatedAt().substring(0, 10));
        }

        private void setAction() {
            notificatIvRecycViewItemNotification.setOnClickListener(v -> {
                if (notificatIvRecycViewItemNotification.getTag().equals(R.drawable.ic_notificat_unread)) {
                    notificatIvRecycViewItemNotification.setImageResource(R.drawable.ic_notificat_read);
                    notificatIvRecycViewItemNotification.setTag(R.drawable.ic_notificat_read);
                } else {
                    notificatIvRecycViewItemNotification.setImageResource(R.drawable.ic_notificat_unread);
                    notificatIvRecycViewItemNotification.setTag(R.drawable.ic_notificat_unread);
                }
            });

        }


    }

    public void setNotificationListToAdapter(List<GeneralSourceData> notificatArrayList) {
        this.notificatArrayList = new ArrayList<>(notificatArrayList);
    }


}
