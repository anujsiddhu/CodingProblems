package com.aks.code.systemdesign.hotelbooking.entity;

public enum RoomType {
    STANDARD(2, "Standard");

    RoomType(int c, String desc) {
        capacity = c;
        description = desc;
    }

    private final int capacity;
    private final String description;

    public int getCapacity() {
        return capacity;
    }

    public String getDescription() {
        return description;
    }
}
