package com.aks.code.systemdesign.parkinglot;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Floor {
    private int level;
    private List<Slot> slots;
    private Parking parking;

    public Floor(int level, Parking parking) {
        this.level = level;
        this.parking = parking;
    }

    public void addSlot(Slot slot) {
        if (slots == null) {
            slots = new ArrayList<>();
        }
        slots.add(slot);
        slot.setFloor(this);
    }
}