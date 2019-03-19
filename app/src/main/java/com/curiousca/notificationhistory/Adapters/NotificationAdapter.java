package com.curiousca.notificationhistory.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.curiousca.notificationhistory.Model.Payload;
import com.curiousca.notificationhistory.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private int removedPosition = 0;
    private Payload removedCard;

    private Context mContext;
    private ArrayList<Payload> mPayload;

    public NotificationAdapter(Context context, ArrayList<Payload> notificationItem) {
        this.mContext = context;
        this.mPayload = notificationItem;
        notifyDataSetChanged();
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
    public void onBindViewHolder(@NonNull NotificationAdapter.NotificationViewHolder holder, int position) {
        Payload currentItem = mPayload.get(position);

        holder.notificationText.setText(currentItem.getName());
        holder.notificationDate.setText(currentItem.getSubtitle());
    }

    public void removeItem(RecyclerView.ViewHolder viewHolder) {
        removedPosition = viewHolder.getAdapterPosition();
        removedCard = mPayload.get(viewHolder.getAdapterPosition());

        mPayload.remove(viewHolder.getAdapterPosition());
        notifyItemRemoved (viewHolder.getAdapterPosition());

        Snackbar.make(viewHolder.itemView, "Notification Deleted", Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPayload.add(removedPosition, removedCard);
                        notifyItemInserted(removedPosition);
                    }
                })
                .show();

    }

    @Override
    public int getItemCount() {
        return mPayload.size();
    }

}



