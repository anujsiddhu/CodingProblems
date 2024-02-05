package com.aks.code.systemdesign.notification;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class EmployeeImpl implements Employee, Notifiable {
    private final String id;
    private final String name;
    private Employee manager;
    private List<String> skills;
    private boolean leafLevel;
    private boolean optedOutNotifications;
    private Queue<Notification> notifications;


    EmployeeImpl(String id, String name, boolean leafLevel) {
        this.id = id;
        this.name = name;
        this.leafLevel = leafLevel;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Employee manager() {
        return manager;
    }

    @Override
    public void manager(Employee employee) {
        this.manager = employee;
    }

    @Override
    public List<String> skills() {
        return skills;
    }

    @Override
    public void addSkill(String skill) {
        if (skills == null) {
            skills = new ArrayList<>();
        }
        skills.add(skill);
    }

    @Override
    public boolean leafLevel() {
        return leafLevel;
    }

    @Override
    public void leafLevel(boolean leafLevel) {
        this.leafLevel = leafLevel;
    }

    @Override
    public boolean optedOutNotifications() {
        return optedOutNotifications;
    }

    @Override
    public void optedOutNotifications(Boolean optedOutNotifications) {
        this.optedOutNotifications = optedOutNotifications;
    }

    @Override
    public void sendNotification(String msg) {
        Notification notification = new Notification(this, msg);
        if (leafLevel) {
            List<Employee> peers = manager.getSubordinates();
            for (Employee peer : peers) {
                peer.getNotified(notification);
            }
        } else {
            Queue<Employee> queue = new LinkedList<>();
            queue.add(this);
            while (!queue.isEmpty()) {
                int size = queue.size();
                while (size > 0) {
                    Employee emp = queue.poll();
                    if (!emp.optedOutNotifications()) {
                        emp.getNotified(notification);
                    }
                    if (!emp.leafLevel()) {
                        for (Employee e : emp.getSubordinates()) {
                            queue.add(e);
                        }
                    }
                    size--;
                }
            }
        }
    }

    @Override
    public void getNotified(Notification notification) {
        notifications.offer(notification);
    }

}
