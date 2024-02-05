package com.aks.code.systemdesign.parkinglot;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Parking {
    private int id;
    private String address;
    private List<Floor> floors;
    private Map<Long, Ticket> ticketHistory;

    public Parking(int id, String address) {
        this.id = id;
        this.address = address;
        this.ticketHistory = new HashMap<>();
    }

    public void addFloor(Floor floor) {
        if(floors == null) {
            floors = new ArrayList<>();
        }
        floors.add(floor);
        floor.setParking(this);
    }

    public Slot getFreeSlot(Vehicle vehicle) {
        for (Floor floor : floors) {
            for (Slot slot : floor.getSlots()) {
                if (slot.isFree() && slot.getType() == vehicle.getType()) {
                    return slot;
                }
            }
        }
        return null;
    }
}
