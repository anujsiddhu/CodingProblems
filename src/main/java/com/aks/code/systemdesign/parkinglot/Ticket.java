package com.aks.code.systemdesign.parkinglot;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Ticket {
    public static long NEXT_ID = 1;

    private long id;
    private Vehicle vehicle;
    private String customerName;
    private Slot slot;
    private LocalDateTime entry;
    private LocalDateTime exit;
    private long totalAmount;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("id: " + id);
        sb.append("\nvehicle: ").append(vehicle.getVehicleNuber());
        sb.append("\ncustomerName: ").append(customerName);
        sb.append("\nentry: ").append(entry);
        if(slot != null) {
            sb.append("\nslot: ").append(slot.getId());
        }
        if (exit != null) {
            sb.append("\nexit: ").append(exit);
            sb.append("\ntotalAmount: ").append(totalAmount);
        }
        return sb.toString();
    }

}