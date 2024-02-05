package com.aks.code.systemdesign.notification;

public class Notification {
    private final Notifiable notifiable;
    private final String message;

    public Notification(Notifiable notifiable, String message) {
        this.notifiable = notifiable;
        this.message = message;
    }
}
