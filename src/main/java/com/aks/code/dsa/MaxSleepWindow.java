package com.aks.code.dsa;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.stream.Collectors;

public class MaxSleepWindow {
    public static void main(String[] args) {

        String s = "Sun 10:00-20:00\n" +
                "Fri 05:00-10:00\n" +
                "Fri 16:30-23:50\n" +
                "Sat 10:00-24:00\n" +
                "Sun 01:00-04:00\n" +
                "Sat 02:00-06:00\n" +
                "Tue 03:30-18:15\n" +
                "Tue 19:00-20:00\n" +
                "Wed 04:25-15:14\n" +
                "Wed 15:14-22:40\n" +
                "Thu 00:00-23:59\n" +
                "Mon 05:00-13:00\n" +
                "Mon 15:00-21:00";
        System.out.println(solution(s));
        s = "Mon 00:00-24:00\n" +
                "Sun 00:00-24:00";
        System.out.println(solution(s));
    }

    private static int solution(String s) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm");
        Map<String, Integer> days = new HashMap<>() {{
            put("Mon", 0);put("Tue", 1);put("Wed", 2);put("Thu", 3);put("Fri", 4);put("Sat", 5);put("Sun", 6);
        }};
        String[] lines = s.split(System.lineSeparator());
        Map<Integer, List<int[]>> meetings = new TreeMap<>();
        for(String line: lines) {
            String day = line.split(" ")[0];
            String startTime = line.split(" ")[1].split("-")[0];
            String endTime = line.split(" ")[1].split("-")[1];
            int dayNumber = days.get(day);

            LocalTime stt = LocalTime.parse(startTime, df);
            int startHour = startTime.equals("00:00") ? 0 : stt.get(ChronoField.CLOCK_HOUR_OF_DAY);
            int startMin = stt.get(ChronoField.MINUTE_OF_HOUR);
            int startVal = 24*60*dayNumber + 60*startHour + startMin;

            LocalTime ett = LocalTime.parse(endTime, df);
            int endHour = endTime.equals("00:00") ? 24 : ett.get(ChronoField.CLOCK_HOUR_OF_DAY);
            int endMin = ett.get(ChronoField.MINUTE_OF_HOUR);
            int endVal = 24*60*dayNumber + 60*endHour + endMin;

            System.out.print(day + " " + startTime + " " + endTime);
            System.out.print(" ======== ");
            System.out.print(dayNumber + " " + startVal + " " + endVal);
            System.out.println();
            meetings.putIfAbsent(dayNumber, new ArrayList<>());
            meetings.get(dayNumber).add(new int[] {startVal, endVal});
        }

        List<int[]> meetingsList = meetings.values().stream()
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(i -> i[1]))
                .collect(Collectors.toList());
//        System.out.println(meetingsList.stream().map(Arrays::toString).collect(Collectors.joining()));

        int size = meetingsList.size();
        int maxWindow = meetingsList.get(0)[0];
        maxWindow = Integer.max(maxWindow, 10080 - meetingsList.get(size-1)[1]);

        for(int i = 0; i<size-1; i++) {
            maxWindow = Integer.max(maxWindow, meetingsList.get(i+1)[0] - meetingsList.get(i)[1]);
        }
        return maxWindow;
    }
}
