package com.curiousca.notificationhistory.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.curiousca.notificationhistory.Adapters.RecallDetailAdapter;
import com.curiousca.notificationhistory.DataInterfaces.RecallPayloadAPI;
import com.curiousca.notificationhistory.Model.RecallData;
import com.curiousca.notificationhistory.Model.RecallPayload;
import com.curiousca.notificationhistory.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecallDetailActivity extends AppCompatActivity {

    private static final String TAG = "RecallDetailActivity";


    private TextView textViewRecallDetail;
    private RecyclerView dRecyclerViewRecall;
    private RecallDetailAdapter dRecallAdapter;
    private ArrayList<RecallPayload> dRecallPayload;

    private static final String BASE_RECALL_URL = "http://api.icndb.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recall_detail);

        buildRecallRecyclerView();
        parseRecallJson();

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

                        ArrayList<RecallPayload> payload = response.body().getPayload();
                        for (int i = 0; i < payload.size(); i++){
                            payload.get(i).getName();
                            payload.get(i).getSubtitle();
                            Log.d(TAG, "onResopnse: \n"+
                                    "Name: " + payload.get(i).getName() + "\n" +
                                    "Subtitle: " + payload.get(i).getSubtitle());
                        }

                        dRecallAdapter = new RecallDetailAdapter(getApplicationContext(), payload);
                        dRecyclerViewRecall.setAdapter(dRecallAdapter);
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

    private void buildRecallRecyclerView(){
        dRecyclerViewRecall = findViewById(R.id.recycler_view_recall);
        dRecyclerViewRecall.setHasFixedSize(true);
        dRecyclerViewRecall.setLayoutManager(new LinearLayoutManager(this));

    }
}
