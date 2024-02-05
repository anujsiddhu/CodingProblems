package com.aks.code.systemdesign.hotelbooking.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Reservation {
    private long id;
    private User user;
    private List<String> guests;
    private Hotel hotel;
    private LocalDate checkIn;
    private LocalDate checkOut;
}