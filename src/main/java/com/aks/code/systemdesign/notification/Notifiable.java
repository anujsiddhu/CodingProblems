package com.aks.code.systemdesign.notification;

public interface Notifiable {
    void sendNotification(String msg);

    void getNotified(Notification notification);
}
