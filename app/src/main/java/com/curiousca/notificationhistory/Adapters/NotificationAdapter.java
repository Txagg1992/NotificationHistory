package com.curiousca.notificationhistory.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.curiousca.notificationhistory.DataClasses.NotificationItem;
import com.curiousca.notificationhistory.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private Context mContext;
    private ArrayList<NotificationItem> mNotificationItem;

    public NotificationAdapter(Context context, ArrayList<NotificationItem> notificationItem) {
        this.mContext = context;
        this.mNotificationItem = notificationItem;
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder{
        public ImageView notificationImage;
        public TextView notificationText;
        public TextView notificationDate;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            notificationImage = itemView.findViewById(R.id.image_view_notification);
            notificationText = itemView.findViewById(R.id.text_view_notification);
            notificationDate = itemView.findViewById(R.id.text_view_date);
        }
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.card_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        NotificationItem currentItem = mNotificationItem.get(position);
        holder.notificationImage.setImageResource(currentItem.getImageNotificationResource());
        holder.notificationText.setText(currentItem.getNotificationNotification());
        holder.notificationDate.setText(currentItem.getNotificationMessage());
    }

    @Override
    public int getItemCount() {
        return mNotificationItem.size();
    }

}
