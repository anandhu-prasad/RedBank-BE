package com.javainuse.repositories;

import com.javainuse.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepo extends JpaRepository<Notification, Integer> {

    public List<Notification> findByUserId(String uid);

    public Notification findByNotificationId(int nid);

}
