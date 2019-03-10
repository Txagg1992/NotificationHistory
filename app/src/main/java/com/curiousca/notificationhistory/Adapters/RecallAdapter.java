package com.curiousca.notificationhistory.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.curiousca.notificationhistory.Model.Payload;
import com.curiousca.notificationhistory.R;

import java.util.ArrayList;

public class RecallAdapter extends RecyclerView.Adapter<RecallAdapter.RecallViewHolder> {

    private Context mContext;
    private ArrayList<Payload> mPayload;

    public RecallAdapter(Context context, ArrayList<Payload> recallItem) {
        this.mContext = context ;
        this.mPayload = recallItem;
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
        Payload currentItem = mPayload.get(position);

        String name = currentItem.getName();
        String subtitle = currentItem.getSubtitle();

        holder.recallText.setText(name);
    }

    @Override
    public int getItemCount() {
        return mPayload.size();
    }

}
