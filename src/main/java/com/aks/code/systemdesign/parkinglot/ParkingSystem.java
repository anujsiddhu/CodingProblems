package com.aks.code.systemdesign.parkinglot;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ParkingSystem {
    private final Parking parking;
    private final PricingService pricingService;

    public Ticket entry(Vehicle vehicle, String customerName) {
        Ticket ticket = new Ticket.TicketBuilder()
                .id(Ticket.NEXT_ID++).vehicle(vehicle)
                .customerName(customerName)
                .entry(LocalDateTime.now())
                .build();
        Slot slot = parking.getFreeSlot(vehicle);
        if (slot == null) {
            throw new RuntimeException("No free slot found");
        }
        slot.addVehicle(ticket);
        parking.getTicketHistory().put(ticket.getId(), ticket);
        return ticket;
    }

    public Ticket exit(Ticket ticket) {
        ticket.setExit(LocalDateTime.now());
        long amount = pricingService.calculatePrice(ticket);
        ticket.setTotalAmount(amount);
        ticket.getSlot().freeSlot();
        return ticket;
    }

}
