package com.aks.code.systemdesign.parkinglot;

public class ParkingLotApp {
    public static void main(String[] args) {
        Parking parking = init();
        PricingService pricingService = new PricingService();
        ParkingSystem parkingSystem = new ParkingSystem(parking, pricingService);

        Ticket t1 = parkingSystem.entry(Vehicle.of("UP01AA1234", VehicleType.CAR), "Cust1");
        System.out.println("Entry: " + t1);
        Ticket t2 = parkingSystem.entry(Vehicle.of("DL01AA1234", VehicleType.CAR), "Cust2");
        System.out.println("Entry: " + t2);
        parkingSystem.exit(t2);
        System.out.println("Exit: " + t2);
        parkingSystem.exit(t1);
        System.out.println("Exit: " + t1);

        System.out.println(parking.getTicketHistory());
    }

    private static Parking init() {
        Parking parking = new Parking(1000, "XYZ");
        Floor floor = new Floor(1, parking);
        int slotId = 1;
        floor.addSlot(new Slot(slotId++, VehicleType.TRUCK));
        floor.addSlot(new Slot(slotId++, VehicleType.TRUCK));
        floor.addSlot(new Slot(slotId++, VehicleType.BIKE));
        floor.addSlot(new Slot(slotId++, VehicleType.BIKE));
        floor.addSlot(new Slot(slotId++, VehicleType.CAR));
        floor.addSlot(new Slot(slotId++, VehicleType.CAR));
        parking.addFloor(floor);
        floor = new Floor(2, parking);
        floor.addSlot(new Slot(slotId++, VehicleType.BIKE));
        floor.addSlot(new Slot(slotId++, VehicleType.BIKE));
        floor.addSlot(new Slot(slotId++, VehicleType.BIKE));
        floor.addSlot(new Slot(slotId++, VehicleType.CAR));
        floor.addSlot(new Slot(slotId++, VehicleType.CAR));
        floor.addSlot(new Slot(slotId, VehicleType.CAR));
        parking.addFloor(floor);
        return parking;
    }
}
