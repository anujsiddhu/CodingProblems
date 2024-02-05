package com.aks.code.systemdesign.hotelbooking.entity;

import lombok.Data;

@Data
public class Hotel {
    private long id;
    private String name;
    private String address;
    private double lat;
    private double lng;
}
