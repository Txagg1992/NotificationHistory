package com.curiousca.notificationhistory.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.curiousca.notificationhistory.DataClasses.RecallItem;
import com.curiousca.notificationhistory.R;

import java.util.ArrayList;

public class RecallAdapter extends RecyclerView.Adapter<RecallAdapter.RecallViewHolder> {

    private Context mContext;
    private ArrayList<RecallItem> mRecallItem;

    public RecallAdapter(Context context, ArrayList<RecallItem> recallItem) {
        this.mContext = context ;
        this.mRecallItem = recallItem;
    }

    public static class RecallViewHolder extends RecyclerView.ViewHolder{

        public ImageView recallImage;
        public TextView recallText;

        public RecallViewHolder(@NonNull View itemView) {
            super(itemView);

            recallImage = itemView.findViewById(R.id.image_view_alert);
            recallText = itemView.findViewById(R.id.text_view_recall);
        }
    }

    @NonNull
    @Override
    public RecallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.card_recall, parent, false);
        return new RecallViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecallViewHolder holder, int position) {
        RecallItem currentItem = mRecallItem.get(position);
        holder.recallImage.setImageResource(currentItem.getImageRecallResource());
        holder.recallText.setText(currentItem.getRecallNotification());
    }

    @Override
    public int getItemCount() {
        return mRecallItem.size();
    }

}
