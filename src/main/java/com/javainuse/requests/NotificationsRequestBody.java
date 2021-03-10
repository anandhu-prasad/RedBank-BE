package com.javainuse.requests;

public class NotificationsRequestBody {
    private int notificationId;

    public NotificationsRequestBody() {
        super();
    }

    public NotificationsRequestBody(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    @Override
    public String toString() {
        return "NotificationsRequestBody{" +
                "notificationId='" + notificationId + '\'' +
                '}';
    }
}
