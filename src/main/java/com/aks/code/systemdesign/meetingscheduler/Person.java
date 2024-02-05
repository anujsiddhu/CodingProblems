package com.aks.code.systemdesign.meetingscheduler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Person {
    private int id;
    private String name;
}
