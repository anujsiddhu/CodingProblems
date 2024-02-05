package com.aks.code.systemdesign.notification;

import java.util.ArrayList;
import java.util.List;

public interface Employee {
    String id();

    String name();

    Employee manager();

    void manager(Employee employee);

    List<String> skills();

    void addSkill(String skill);

    boolean leafLevel();

    void leafLevel(boolean leafLevel);

    boolean optedOutNotifications();

    void optedOutNotifications(Boolean optedOutNotifications);

    default List<Employee> getSubordinates() {
        return new ArrayList<>();
    }

    void addSubordinate(Employee employee);

    void getNotified(Notification notification);
}
