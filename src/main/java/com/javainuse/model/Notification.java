package com.javainuse.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="notifications")
@EntityListeners(AuditingEntityListener.class)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notification_id;

    @Column(name="user_id")
    private String userId;

    private String title;
    private String message;

    @Column(name="notification_date")
    private Timestamp notificationDate;

    @Column(name="status", columnDefinition = "boolean default 0")
    private Boolean status;


    public Notification() {
        super();
    }

    public Notification(String userId, String title, String message, Timestamp notificationDate) {
        this.userId = userId;
        this.title = title;
        this.message = message;
        this.notificationDate = notificationDate;
    }

    public int getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(int notification_id) {
        this.notification_id = notification_id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Timestamp notificationDate) {
        this.notificationDate = notificationDate;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notification_id=" + notification_id +
                ", userId='" + userId + '\'' +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", notificationDate=" + notificationDate +
                ", status=" + status +
                '}';
    }
}
