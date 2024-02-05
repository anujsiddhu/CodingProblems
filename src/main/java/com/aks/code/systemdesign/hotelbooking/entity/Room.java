package com.aks.code.systemdesign.hotelbooking.entity;

import lombok.Data;

@Data
public class Room {
    private long id;
    private int number;
    private String name;
    private RoomType roomType;
    private Hotel hotel;
    private RoomStatus roomStatus;
}
