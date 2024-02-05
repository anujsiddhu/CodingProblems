package com.aks.code.systemdesign.meetingscheduler;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Calender {
    private List<Meeting> meetings;

    public Calender() {
        meetings = new LinkedList<>();
    }

    public List<Meeting> getAllMeetingByPerson(Person person) {
        return meetings.stream()
                .filter(meeting -> meeting.getOrganiser().getId() == person.getId()
                        || meeting.getAttendees().contains(person)
                ).collect(Collectors.toList());
    }

    public List<Meeting> getAllMeetingByRoom(Room room) {
        return meetings.stream()
                .filter(meeting -> meeting.getRoom().getId() == room.getId()
                ).collect(Collectors.toList());
    }

    public Room getAvailableRoom(LocalDateTime start, LocalDateTime end) {
        List<Room> availableRoom = new LinkedList<>(DataUtils.rooms());
        meetings.forEach(meeting -> {
            if (isOverlap(meeting, start, end)) {
                availableRoom.remove(meeting.getRoom());
            }
        });
        if (availableRoom.isEmpty()) {
            return null;
        } else {
            return availableRoom.get(0);
        }
    }

    private boolean isOverlap(Meeting meeting, LocalDateTime start, LocalDateTime end) {
        return (!start.isBefore(meeting.getStartTime()) && !start.isAfter(meeting.getEndTime()))
                || (!end.isBefore(meeting.getStartTime()) && !end.isAfter(meeting.getEndTime()));
    }

    public List<Meeting> getAllMeeting() {
        return meetings;
    }

    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    public void setMeeting(List<Meeting> meetings) {
        this.meetings = meetings;
    }
}
