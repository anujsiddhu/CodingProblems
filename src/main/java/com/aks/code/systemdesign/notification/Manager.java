package com.aks.code.systemdesign.notification;

import java.util.ArrayList;
import java.util.List;

public class Manager extends EmployeeImpl {
    private final List<Employee> subordinates;

    Manager(String id, String name) {
        super(id, name, false);
        subordinates = new ArrayList<>();
    }

    public void removeFromTeam(Employee employee) {
        subordinates.remove(employee);
    }

    @Override
    public List<Employee> getSubordinates() {
        return subordinates;
    }

    @Override
    public void addSubordinate(Employee subordinate) {
        subordinates.add(subordinate);
    }

}
