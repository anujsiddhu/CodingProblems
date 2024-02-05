package com.aks.code.dsa;

public class TimeFormatMissingReplace {

    public static void main(String[] args) {
        TimeFormatMissingReplace missingReplace = new TimeFormatMissingReplace();

        System.out.println("2?:45 -> " + missingReplace.solve("2?:45"));
        System.out.println("?9:32 -> " +missingReplace.solve("?9:32"));
        System.out.println("0?:?0 -> " + missingReplace.solve("0?:?0"));
        System.out.println("?4:0? -> " + missingReplace.solve("?4:0?"));
        System.out.println("29:01 -> " + missingReplace.solve("29:01"));
    }
    private int solve(String s) {
        int res = 0;
        String[] split = s.split(":");
        char  h0 = split[0].charAt(0);
        char  h1 = split[0].charAt(1);
        char  m0 = split[1].charAt(0);
        char  m1 = split[1].charAt(1);

        if(h0 == '?') {
            if(h1 == '?') {
                res += 24;
            } else {
                if(h1 <= '3') {
                    res += 3;
                } else {
                    res += 2;
                }
            }
        } else if(h1 == '?') {
            if(h0 == '2') {
                res += 4;
            } else {
                res += 10;
            }
        }
        int resM = 0;
        if(m0 == '?') {
            if(m1 == '?') {
                resM += 60;
            } else {
                resM += 6;
            }
        } else if(m1 == '?') {
            resM += 10;
        }
//      System.out.println(res + " " + resM);
        if(res ==0 && resM == 0) return 0;
        return (res == 0 ? 1 : res) * (resM == 0 ? 1 : resM);
    }
}
