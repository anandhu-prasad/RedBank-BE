package com.javainuse.models;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="notifications")
@EntityListeners(AuditingEntityListener.class)
public class Notification {

    @Id
    @SequenceGenerator(
            name = "notification_sequence",
            sequenceName = "notification_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "\"notification_sequence\""
    )
    @Column(name="notification_id")
    private int notificationId;

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
        return notificationId;
    }

    public void setNotification_id(int notificationId) {
        this.notificationId = notificationId;
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
                "notificationId=" + notificationId +
                ", userId='" + userId + '\'' +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", notificationDate=" + notificationDate +
                ", status=" + status +
                '}';
    }
}
