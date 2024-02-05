package com.aks.code.systemdesign.meetingscheduler;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Meeting {
    private int id;
    private Person organiser;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Room room;
    private List<Person> attendees;

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", organiser=" + organiser +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", room=" + room +
                '}';
    }
}
