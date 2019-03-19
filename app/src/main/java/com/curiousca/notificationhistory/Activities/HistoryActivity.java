package com.curiousca.notificationhistory.Activities;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.curiousca.notificationhistory.Adapters.NotificationAdapter;
import com.curiousca.notificationhistory.Adapters.RecallAdapter;
import com.curiousca.notificationhistory.DataInterfaces.NotificationPayloadAPI;
import com.curiousca.notificationhistory.DataInterfaces.RecallPayloadAPI;
import com.curiousca.notificationhistory.Model.Data;
import com.curiousca.notificationhistory.Model.Payload;
import com.curiousca.notificationhistory.Model.RecallData;
import com.curiousca.notificationhistory.Model.RecallPayload;
import com.curiousca.notificationhistory.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryActivity extends AppCompatActivity implements RecallAdapter.OnItemClickListener{

    private ColorDrawable swipeBackground = new ColorDrawable(Color.parseColor("#ff0000"));
    private Drawable deleteIcon;

    public static final String EXTRA_RECALL_NAME = "recallName";
    public static final String EXTRA_RECALL_SUBTITLE = "recallSubtitle";

    private static final String TAG = "HistoryActivity";

//    private ArrayList<RecallItem> mRecallItem;
//    private ArrayList<NotificationItem> mNotificationItem;

    private RecyclerView mRecyclerViewRecall;
    private RecyclerView mRecyclerViewNotify;

    private RecallAdapter mRecallAdapter;
    private NotificationAdapter mNotificationAdapter;

    private ArrayList<Payload> mPayload;
    private ArrayList<RecallPayload> mRecallPayload;

    private static final String BASE_NOTIFY_URL = "https://one-np.stg.telematicsct.com/";
    private static final String BASE_RECALL_URL = "http://api.icndb.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

//        createRecallItems();
//        createNotificationItems();
        buildRecallRecyclerView();
        buildNotificationRecyclerView();
        parseNotificationJson();
        parseRecallJson();
        initSwipeToDelete();


//        if (mRecallPayload.isEmpty() && mPayload.isEmpty()){
//            setContentView(R.layout.empty_notifications);
//        }
    }

    private void parseRecallJson(){
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_RECALL_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RecallPayloadAPI payloadAPI = retrofit.create(RecallPayloadAPI.class);

            Call<RecallData> call = payloadAPI.getPayload();
            try {
                call.enqueue(new Callback<RecallData>() {
                    @Override
                    public void onResponse(Call<RecallData> call, Response<RecallData> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Recall Code: " + response.code(),
                                    Toast.LENGTH_SHORT).show();
                            Log.d("Not Response Successful", "RECALL CODE: " + response.code());
                            return;
                        }else {
                            Toast.makeText(getApplicationContext(), "Recall Code: " + response.code(),
                                    Toast.LENGTH_SHORT).show();
                            Log.d("Success!", "Response RECALL CODE is: " + response.code());
                        }

                        mRecallPayload = response.body().getPayload();

                        mRecallAdapter = new RecallAdapter(getApplicationContext(), mRecallPayload);
                        mRecyclerViewRecall.setAdapter(mRecallAdapter);
                        mRecallAdapter.setOnItemClickListener(HistoryActivity.this);

                        for (int i = 0; i < mRecallPayload.size(); i++){
                            mRecallPayload.get(i).getName();
                            mRecallPayload.get(i).getSubtitle();
                            Log.d(TAG, "onResopnse: \n"+
                                    "Name: " + mRecallPayload.get(i).getName() + "\n" +
                                    "Subtitle: " + mRecallPayload.get(i).getSubtitle());
                        }

                    }

                    @Override
                    public void onFailure(Call<RecallData> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                        Log.d("onFailure", t.getMessage());
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void parseNotificationJson(){
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_NOTIFY_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            NotificationPayloadAPI payloadAPI = retrofit.create(NotificationPayloadAPI.class);

            Call<Data> call = payloadAPI.getPayload();
            try {
                call.enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Code: " + response.code(),
                                    Toast.LENGTH_SHORT).show();
                            Log.d("Not Response Successful", "CODE: " + response.code());
                            return;
                        }else {
                            Toast.makeText(getApplicationContext(), "Code: " + response.code(),
                                    Toast.LENGTH_SHORT).show();
                            Log.d("Success!", "Response CODE is: " + response.code());
                        }

                        mPayload = response.body().getPayload();
                        for (int i = 0; i < mPayload.size(); i++){
                            mPayload.get(i).getName();
                            mPayload.get(i).getSubtitle();

                            Log.d(TAG, "onResopnse: \n"+
                                    "Name: " + mPayload.get(i).getName() + "\n" +
                                    "Subtitle: " + mPayload.get(i).getSubtitle());
                        }
                        mNotificationAdapter = new NotificationAdapter(getApplicationContext(), mPayload);
                        mRecyclerViewNotify.setAdapter(mNotificationAdapter);
                        //mNotificationAdapter.submitList(notes);
                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                        Log.d("onFailure", t.getMessage());
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buildRecallRecyclerView(){
        mRecyclerViewRecall = findViewById(R.id.recycler_view_recall);
        mRecyclerViewRecall.setHasFixedSize(true);
        mRecyclerViewRecall.setLayoutManager(new LinearLayoutManager(this));
    }

    private void buildNotificationRecyclerView(){
        mRecyclerViewNotify = findViewById(R.id.recycler_view_notify);
        mRecyclerViewNotify.setHasFixedSize(true);
        mRecyclerViewNotify.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemClick(int position) {

        Log.d(TAG, "Somebody clicked me at position " + position);

        Intent detailIntent = new Intent(this, RecallDetailActivity.class);
        RecallPayload clickedItem = mRecallPayload.get(position);

        detailIntent.putExtra(EXTRA_RECALL_NAME, clickedItem.getName());
        detailIntent.putExtra(EXTRA_RECALL_SUBTITLE, clickedItem.getSubtitle());

        startActivity(detailIntent);
    }

    public void initSwipeToDelete(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            deleteIcon = getDrawable(R.drawable.ic_delete);
        }
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
                viewHolder.getAdapterPosition();
                mNotificationAdapter.removeItem(viewHolder);
            }

            @Override
            public void onChildDraw(@NonNull Canvas c,
                                    @NonNull RecyclerView recyclerView,
                                    @NonNull RecyclerView.ViewHolder viewHolder,
                                    float dX,
                                    float dY,
                                    int actionState,
                                    boolean isCurrentlyActive) {
                View itemView = viewHolder.itemView;
                int iconMargin = (itemView.getHeight() - deleteIcon.getMinimumHeight())/2;

                if (dX > 0){
                    swipeBackground.setBounds(itemView.getLeft(), itemView.getTop(), itemView.getRight(), itemView.getBottom());
                    deleteIcon.setBounds(itemView.getLeft() + iconMargin,
                            itemView.getTop() + iconMargin,
                            itemView.getLeft() + iconMargin + deleteIcon.getIntrinsicWidth(),
                            itemView.getBottom() - iconMargin);
                }else {
                    swipeBackground.setBounds(itemView.getLeft(), itemView.getTop(), itemView.getRight(), itemView.getBottom());
                    deleteIcon.setBounds(itemView.getRight() - iconMargin - deleteIcon.getIntrinsicWidth(),
                            itemView.getTop() + iconMargin,
                            itemView.getRight() - iconMargin,
                            itemView.getBottom() - iconMargin);

                }

                swipeBackground.draw(c);
                deleteIcon.draw(c);
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }).attachToRecyclerView(mRecyclerViewNotify);


    }

    //This code is not needed when information is dynamic

//    private void createRecallItems(){
//        mRecallItem = new ArrayList<>();
//        mRecallItem.add(new RecallItem(R.drawable.alert_red, R.string.lorem_ipsum, 0));
//        mRecallItem.add(new RecallItem(R.drawable.alert_red, R.string.lorem_ipsum, 0));
////        mRecallItem.add(new RecallItem(R.drawable.alert_red, R.string.lorem_ipsum_1, 0));
//    }
//
//    private void createNotificationItems(){
//        mNotificationItem = new ArrayList<>();
//        mNotificationItem.add(new NotificationItem(R.drawable.ic_message_black, R.string.lorem_ipsum, R.string.date));
//        mNotificationItem.add(new NotificationItem(R.drawable.ic_bell_off_outline_black_48dp, R.string.lorem_ipsum, R.string.date));
//        mNotificationItem.add(new NotificationItem(R.drawable.ic_check_circle_black, R.string.lorem_ipsum, R.string.date));
//        mNotificationItem.add(new NotificationItem(R.drawable.ic_message_black, R.string.lorem_ipsum, R.string.date));
//        mNotificationItem.add(new NotificationItem(R.drawable.ic_bell_off_outline_black_48dp, R.string.lorem_ipsum, R.string.date));
//        mNotificationItem.add(new NotificationItem(R.drawable.ic_check_circle_black, R.string.lorem_ipsum_1, R.string.date));
//        mNotificationItem.add(new NotificationItem(R.drawable.ic_message_black, R.string.lorem_ipsum_1, R.string.date));
//        mNotificationItem.add(new NotificationItem(R.drawable.ic_bell_off_outline_black_48dp, R.string.lorem_ipsum_1, R.string.date));
//        mNotificationItem.add(new NotificationItem(R.drawable.ic_check_circle_black, R.string.lorem_ipsum_1, R.string.date));
//    }

}
