package com.curiousca.notificationhistory.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.curiousca.notificationhistory.Model.RecallPayload;
import com.curiousca.notificationhistory.R;

import java.util.ArrayList;

public class RecallDetailAdapter extends RecyclerView.Adapter<RecallDetailAdapter.RecallDetailViewHolder> {

    private Context dContext;
    private ArrayList<RecallPayload> dRecallPayload;

    public RecallDetailAdapter(Context context, ArrayList<RecallPayload> recallPayload) {
        this.dContext = context;
        this.dRecallPayload = recallPayload;
    }

    @NonNull
    @Override
    public RecallDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(dContext)
                .inflate(R.layout.card_recall_detail, parent, false);
        return new  RecallDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecallDetailViewHolder holder, int position) {

        RecallPayload currentItem = dRecallPayload.get(position);

        holder.textViewRecallTitle.setText(currentItem.getName());
        holder.textViewRecallDetail.setText(currentItem.getSubtitle());

    }

    @Override
    public int getItemCount() {
        return dRecallPayload.size();
    }

    public class RecallDetailViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewRecallTitle;
        public TextView textViewRecallDetail;

        public RecallDetailViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewRecallTitle = itemView.findViewById(R.id.text_view_recall_title);
            textViewRecallDetail = itemView.findViewById(R.id.text_view_recall_detail);
        }
    }
}
