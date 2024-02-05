package com.aks.code.systemdesign.hotelbooking.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservedRoom {
    private long id;
    private Reservation reservation;
    private Room room;
    private LocalDate checkIn;
    private LocalDate checkOut;
}
