package com.curiousca.notificationhistory.DataInterfaces;

public class RecallItem {

    private int mImageRecallResource;
    private int mRecallNotification;
    private int mRecallMessage;

    public RecallItem(int imageRecallResource, int recallNotification, int recallMessage) {
        this.mImageRecallResource = imageRecallResource;
        this.mRecallNotification = recallNotification;
        this.mRecallMessage = recallMessage;
    }

    public int getImageRecallResource() {
        return mImageRecallResource;
    }

    public int getRecallNotification() {
        return mRecallNotification;
    }

    public int getRecallMessage() {
        return mRecallMessage;
    }

    @Override
    public String toString() {
        return "RecallItem{" +
                "mImageRecallResource=" + mImageRecallResource +
                ", mRecallNotification=" + mRecallNotification +
                ", mRecallMessage=" + mRecallMessage +
                '}';
    }
}
