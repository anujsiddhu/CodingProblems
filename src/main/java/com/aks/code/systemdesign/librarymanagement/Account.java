package com.aks.code.systemdesign.librarymanagement;

import lombok.Data;

@Data
public class Account {
    private int id;
    private String name;
    private String phone;
    private MemberType memberType;
    private AccountStatus status;
}
