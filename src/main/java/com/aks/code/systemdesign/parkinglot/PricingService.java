package com.aks.code.systemdesign.parkinglot;

import java.time.temporal.ChronoUnit;

public class PricingService {
    public long calculatePrice(Ticket ticket) {
        long hour = ticket.getEntry().until(ticket.getExit(), ChronoUnit.HOURS);
        switch (ticket.getVehicle().getType()) {
            case CAR:
                return getAmountForCar(hour);
            case BIKE:
                return getAmountForBike(hour);
            case TRUCK:
                return getAmountForTruck(hour);
            default:
                throw new RuntimeException("Invalid Vehicle type");
        }
    }

    private long getAmountForBike(long hour) {
        if (hour <= 3) {
            return 20;
        } else if (hour <= 5) {
            return 50;
        } else {
            return hour * 10;
        }
    }

    private long getAmountForCar(long hour) {
        if (hour <= 3) {
            return 100;
        } else if (hour <= 6) {
            return 250;
        } else {
            return hour * 100;
        }
    }

    private long getAmountForTruck(long hour) {
        if (hour <= 3) {
            return 250;
        } else if (hour <= 6) {
            return 600;
        } else {
            return hour * 150;
        }
    }
}
