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

public class RecallAdapter extends RecyclerView.Adapter<RecallAdapter.RecallViewHolder> {

    private Context mContext;
    private ArrayList<RecallPayload> mPayload;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }


    public RecallAdapter(Context context, ArrayList<RecallPayload> recallItem) {
        this.mContext = context ;
        this.mPayload = recallItem;
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
        RecallPayload currentItem = mPayload.get(position);

        holder.recallText.setText(currentItem.getName());

    }

    @Override
    public int getItemCount() {
        return mPayload.size();
    }

    public class RecallViewHolder extends RecyclerView.ViewHolder{
        public TextView recallText;

        public RecallViewHolder(@NonNull View itemView) {
            super(itemView);

            recallText = itemView.findViewById(R.id.text_view_recall);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

}
