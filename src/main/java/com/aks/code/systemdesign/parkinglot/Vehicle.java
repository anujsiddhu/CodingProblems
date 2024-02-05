package com.aks.code.systemdesign.parkinglot;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vehicle {
    private String vehicleNuber;
    private VehicleType type;

    public static Vehicle of(String vehicleNuber, VehicleType type) {
        return new Vehicle(vehicleNuber, type);
    }
}
