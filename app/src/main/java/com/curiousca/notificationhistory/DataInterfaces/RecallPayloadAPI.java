package com.curiousca.notificationhistory.DataInterfaces;

import com.curiousca.notificationhistory.Model.RecallData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface RecallPayloadAPI {

    String BASE_RECALL_URL = "http://api.icndb.com/";


    @Headers("Content-Type: application/json")
    @GET("jokes/random/2")
    Call<RecallData> getPayload(
            /*@Path("guid") String guid*/

            //http://api.icndb.com/jokes/random/2
    );
}
