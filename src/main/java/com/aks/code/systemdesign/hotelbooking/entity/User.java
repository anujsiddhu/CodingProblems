package com.aks.code.systemdesign.hotelbooking.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private long id;
    private String name;
    private String email;
    private LocalDateTime joiningTime;
}
