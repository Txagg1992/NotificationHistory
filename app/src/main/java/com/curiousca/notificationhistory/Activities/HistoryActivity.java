package com.curiousca.notificationhistory.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.curiousca.notificationhistory.Adapters.NotificationAdapter;
import com.curiousca.notificationhistory.Adapters.RecallAdapter;
import com.curiousca.notificationhistory.DataInterfaces.NotificationItem;
import com.curiousca.notificationhistory.DataInterfaces.RecallItem;
import com.curiousca.notificationhistory.DataInterfaces.RecallPayloadAPI;
import com.curiousca.notificationhistory.Model.Data;
import com.curiousca.notificationhistory.Model.Payload;
import com.curiousca.notificationhistory.Model.RecallData;
import com.curiousca.notificationhistory.Model.RecallPayload;
import com.curiousca.notificationhistory.R;
import com.curiousca.notificationhistory.DataInterfaces.NotificationPayloadAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryActivity extends AppCompatActivity {

    private static final String TAG = "HistoryActivity";

    private ArrayList<RecallItem> mRecallItem;
    private ArrayList<NotificationItem> mNotificationItem;

    private RecyclerView mRecyclerViewRecall;
    private RecyclerView mRecyclerViewNotify;

    private RecallAdapter mRecallAdapter;
    private NotificationAdapter mNotificationAdapter;

    private ArrayList<Payload> mPayload;
    private static final String BASE_NOTIFY_URL = "https://one-np.stg.telematicsct.com/";
    private static final String BASE_RECALL_URL = "http://api.icndb.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        createRecallItems();
        createNotificationItems();
        buildRecallRecyclerView();
        buildNotificationRecyclerView();
        parseNotificationJson();
        parseRecallJson();

        mPayload = new ArrayList<Payload>();

        if (mRecallItem.isEmpty() && mNotificationItem.isEmpty()){
            setContentView(R.layout.empty_notifications);
        }
    }

    private void parseRecallJson(){
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_RECALL_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            final RecallPayloadAPI payloadAPI = retrofit.create(RecallPayloadAPI.class);

            Call<RecallData> call = payloadAPI.getPayload();
            try {
                call.enqueue(new Callback<RecallData>() {
                    @Override
                    public void onResponse(Call<RecallData> call, Response<RecallData> response) {
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

                        ArrayList<RecallPayload> payload = response.body().getPayload();
                        for (int i = 0; i < payload.size(); i++){
                            payload.get(i).getName();
                            payload.get(i).getSubtitle();
                            Log.d(TAG, "onResopnse: \n"+
                                    "Name: " + payload.get(i).getName() + "\n" +
                                    "Subtitle: " + payload.get(i).getSubtitle());
                        }

                        mRecallAdapter = new RecallAdapter(getApplicationContext(), payload);
                        mRecyclerViewRecall.setAdapter(mRecallAdapter);
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

                        ArrayList<Payload> payload = response.body().getPayload();
                        for (int i = 0; i < payload.size(); i++){
                            payload.get(i).getName();
                            payload.get(i).getSubtitle();

                            Log.d(TAG, "onResopnse: \n"+
                                    "Name: " + payload.get(i).getName() + "\n" +
                                    "Subtitle: " + payload.get(i).getSubtitle());
                        }
                        mNotificationAdapter = new NotificationAdapter(getApplicationContext(), payload);
                        mRecyclerViewNotify.setAdapter(mNotificationAdapter);
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

        mRecyclerViewRecall.setAdapter(mRecallAdapter);
    }

    private void buildNotificationRecyclerView(){
        mRecyclerViewNotify = findViewById(R.id.recycler_view_notify);
        mRecyclerViewNotify.setHasFixedSize(true);
        mRecyclerViewNotify.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerViewNotify.setAdapter(mNotificationAdapter);
    }


//This code is not needed when information is dynamic
    private void createRecallItems(){
        mRecallItem = new ArrayList<>();
        mRecallItem.add(new RecallItem(R.drawable.alert_red, R.string.lorem_ipsum, 0));
        mRecallItem.add(new RecallItem(R.drawable.alert_red, R.string.lorem_ipsum, 0));
//        mRecallItem.add(new RecallItem(R.drawable.alert_red, R.string.lorem_ipsum_1, 0));
    }

    private void createNotificationItems(){
        mNotificationItem = new ArrayList<>();
        mNotificationItem.add(new NotificationItem(R.drawable.ic_message_black, R.string.lorem_ipsum, R.string.date));
        mNotificationItem.add(new NotificationItem(R.drawable.ic_bell_off_outline_black_48dp, R.string.lorem_ipsum, R.string.date));
        mNotificationItem.add(new NotificationItem(R.drawable.ic_check_circle_black, R.string.lorem_ipsum, R.string.date));
        mNotificationItem.add(new NotificationItem(R.drawable.ic_message_black, R.string.lorem_ipsum, R.string.date));
        mNotificationItem.add(new NotificationItem(R.drawable.ic_bell_off_outline_black_48dp, R.string.lorem_ipsum, R.string.date));
        mNotificationItem.add(new NotificationItem(R.drawable.ic_check_circle_black, R.string.lorem_ipsum_1, R.string.date));
        mNotificationItem.add(new NotificationItem(R.drawable.ic_message_black, R.string.lorem_ipsum_1, R.string.date));
        mNotificationItem.add(new NotificationItem(R.drawable.ic_bell_off_outline_black_48dp, R.string.lorem_ipsum_1, R.string.date));
        mNotificationItem.add(new NotificationItem(R.drawable.ic_check_circle_black, R.string.lorem_ipsum_1, R.string.date));
    }

}
