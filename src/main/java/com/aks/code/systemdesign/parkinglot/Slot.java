package com.aks.code.systemdesign.parkinglot;

import lombok.Data;

@Data
public class Slot {
    private int id;
    private VehicleType type;
    private Floor floor;
    private Ticket ticket;

    public Slot(int id, VehicleType type) {
        this.id = id;
        this.type = type;
    }

    public boolean isFree() {
        return ticket == null;
    }

    public void addVehicle(Ticket ticket) {
        if (!isFree()) {
            throw new RuntimeException("Slot already occupied");
        }
        this.ticket = ticket;
        ticket.setSlot(this);
    }

    public void freeSlot() {
        this.ticket = null;
    }
}