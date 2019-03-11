package com.curiousca.notificationhistory.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecallData {

    @Expose
    @SerializedName("value")
    public ArrayList<RecallPayload> payload;

    public ArrayList<RecallPayload> getPayload() {
        return payload;
    }

    public void setPayload(ArrayList<RecallPayload> payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "RecallData{" +
                "payload=" + payload +
                '}';
    }
}
