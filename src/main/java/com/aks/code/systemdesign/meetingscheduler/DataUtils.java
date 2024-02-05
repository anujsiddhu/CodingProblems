package com.aks.code.systemdesign.meetingscheduler;

import java.util.ArrayList;
import java.util.List;

public class DataUtils {

    private static final List<Room> rooms = new ArrayList<>();
    private static final List<Person> pesons = new ArrayList<>();

    static {
        for (int i = 1; i <= 5; i++) {
            rooms.add(new Room(100 + i, "Room" + i));
        }
    }

    static {
        for (int i = 1; i <= 50; i++) {
            pesons.add(new Person(200 + i, "Person" + i));
        }
    }

    public static List<Room> rooms() {
        return rooms;
    }
    public static List<Person> pesons() {
        return pesons;
    }
}
