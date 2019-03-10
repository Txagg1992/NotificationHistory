package com.curiousca.notificationhistory.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.curiousca.notificationhistory.Adapters.NotificationAdapter;
import com.curiousca.notificationhistory.Adapters.RecallAdapter;
import com.curiousca.notificationhistory.DataClasses.NotificationItem;
import com.curiousca.notificationhistory.DataClasses.RecallItem;
import com.curiousca.notificationhistory.Model.Payload;
import com.curiousca.notificationhistory.R;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private static final String TAG = "MAinActivity";

    private ArrayList<RecallItem> mRecallItem;
    private ArrayList<NotificationItem> mNotificationItem;

    private RecyclerView mRecyclerViewRecall;
    private RecyclerView mRecyclerViewNotify;

    private RecallAdapter mRecallAdapter;
    private NotificationAdapter mNotificationAdapter;

    private ArrayList<Payload> mPayload;
    private static final String BASE_URL = "https://put.stuff.here";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        createRecallItems();
        createNotificationItems();
        buildRecallRecyclerView();
        buildNotificationRecyclerView();
        parseJson();

        if (mRecallItem.isEmpty() && mNotificationItem.isEmpty()){
            setContentView(R.layout.empty_notifications);
        }
    }

    private void parseJson(){

    }

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

    private void buildRecallRecyclerView(){
        mRecyclerViewRecall = findViewById(R.id.recycler_view_recall);
        mRecyclerViewRecall.setHasFixedSize(true);
        mRecyclerViewRecall.setLayoutManager(new LinearLayoutManager(this));

        mRecallAdapter = new RecallAdapter(getApplicationContext(), mRecallItem);
        mRecyclerViewRecall.setAdapter(mRecallAdapter);
    }

    private void buildNotificationRecyclerView(){
        mRecyclerViewNotify = findViewById(R.id.recycler_view_notify);
        mRecyclerViewNotify.setHasFixedSize(true);
        mRecyclerViewNotify.setLayoutManager(new LinearLayoutManager(this));

        mNotificationAdapter = new NotificationAdapter(getApplicationContext(), mNotificationItem);
        mRecyclerViewNotify.setAdapter(mNotificationAdapter);
    }
}
