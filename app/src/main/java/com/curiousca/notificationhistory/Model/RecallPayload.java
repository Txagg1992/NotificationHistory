package com.curiousca.notificationhistory.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecallPayload {

    @Expose
    @SerializedName("joke")
    private String name;

    @Expose
    @SerializedName("id")
    private String subtitle;

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void  setSubtitle(String subtitle){
        this.subtitle = subtitle;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "name='" + name + '\'' +
                ", subtitle='" + subtitle + '\'' +
                '}';
    }
}
