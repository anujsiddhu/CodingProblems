package com.aks.code.systemdesign.meetingscheduler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MeetingScheduler {
    public static void main(String[] args) {
        MeetingScheduler meetingScheduler = new MeetingScheduler();
        Meeting meeting = new Meeting();
        meeting.setId(500);
        meeting.setOrganiser(DataUtils.pesons().get(0));
        meeting.setAttendees(List.of(DataUtils.pesons().get(1), DataUtils.pesons().get(2)));
        meeting.setStartTime(LocalDateTime.of(2023, 9, 10, 14, 0, 0));
        meeting.setEndTime(LocalDateTime.of(2023, 9, 10, 14, 5, 0));

        System.out.println("Meeting " + meeting.getId() + " booked " + meetingScheduler.bookMeeting(meeting));
    }

    private final Calender calender;

    public MeetingScheduler() {
        calender = new Calender();
        Thread thread = new Thread(new Scheduler());
        thread.start();
    }

    public boolean bookMeeting(Meeting meeting) {
        Room room = calender.getAvailableRoom(meeting.getStartTime(), meeting.getEndTime());
        if (room != null) {
            meeting.setRoom(room);
            calender.addMeeting(meeting);
            return true;
        } else {
            return false;
        }
    }

    private class Scheduler implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (calender) {
                    calender.setMeeting(calender.getAllMeeting().stream()
                            .filter(meeting -> meeting.getEndTime().isAfter(LocalDateTime.now()))
                            .collect(Collectors.toList()));
                }
                calender.getAllMeeting().forEach(meeting -> System.out.println(meeting));
            }
        }
    }
}
