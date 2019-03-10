package com.curiousca.notificationhistory.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data {

    @Expose
    @SerializedName("payload")
    public ArrayList<Payload> payload;

    public ArrayList<Payload> getPayload() {
        return payload;
    }

    public void setPayload(ArrayList<Payload> payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "Data{" +
                "payload=" + payload +
                '}';
    }

}
