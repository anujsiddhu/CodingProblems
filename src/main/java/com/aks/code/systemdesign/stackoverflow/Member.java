package com.aks.code.systemdesign.stackoverflow;

import lombok.Data;

@Data
public class Member {
    private long id;
    private String name;
    private MemberStatus status;
}
