package com.curiousca.notificationhistory.DataInterfaces;

public class NotificationItem {

    private int mImageNotificationResource;
    private int mNotificationNotification;
    private int mNotificationMessage;

    public NotificationItem(int imageNotificationResource, int notificationNotification, int notificationMessage) {
        this.mImageNotificationResource = imageNotificationResource;
        this.mNotificationNotification = notificationNotification;
        this.mNotificationMessage = notificationMessage;
    }

    public int getImageNotificationResource() {
        return mImageNotificationResource;
    }

    public int getNotificationNotification() {
        return mNotificationNotification;
    }

    public int getNotificationMessage() {
        return mNotificationMessage;
    }

    @Override
    public String toString() {
        return "NotificationItem{" +
                "mImageNotificationResource=" + mImageNotificationResource +
                ", mNotificationNotification=" + mNotificationNotification +
                ", mNotificationMessage=" + mNotificationMessage +
                '}';
    }
}
