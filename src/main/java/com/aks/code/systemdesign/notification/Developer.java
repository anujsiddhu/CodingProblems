package com.aks.code.systemdesign.notification;

import java.util.ArrayList;
import java.util.List;

public class Developer extends EmployeeImpl {

    public Developer(String id, String name) {
        super(id, name, true);
    }

    @Override
    public void addSubordinate(Employee employee) {
        throw new UnsupportedOperationException("Developer can't have Subordinate");
    }
}