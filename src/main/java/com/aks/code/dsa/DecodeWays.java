package com.aks.code.dsa;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/decode-ways/
public class DecodeWays {
    Map<String, Integer> dp = new HashMap<>();
    public static void main(String[] args) {
        System.out.println(new DecodeWays().solveRec("12"));
        System.out.println(new DecodeWays().solveRec("12212345"));
    }

    private int solveRec(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        if(s.charAt(0) == '0') {
            return 0;
        }
        if(s.length() == 1) {
            return 1;
        }
        if(dp.containsKey(s)) {
            return dp.get(s);
        }
        int res = solveRec(s.substring(1));

        int sv = 10 * Character.getNumericValue(s.charAt(0))
                + Character.getNumericValue(s.charAt(1));

        if(sv <= 26) {
            String sub = s.substring(2);
            if(sub == null || sub.length() == 0) {
                res++;
            } else {
                res += solveRec(s.substring(2));
            }
        }
        dp.put(s, res);
        return res;
    }
}
