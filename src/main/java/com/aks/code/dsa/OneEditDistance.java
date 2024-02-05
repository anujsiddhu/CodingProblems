package com.aks.code.dsa;

public class OneEditDistance {
    public static void main(String[] args) {
        // NOTE: The following input values will be used for testing your solution.
        System.out.println(isOneAway("abcde", "abcd"));  // should return true
        System.out.println(isOneAway("abde", "abcde"));  // should return true
        System.out.println(isOneAway("a", "a"));  // should return true
        System.out.println(isOneAway("abcdef", "abqdef"));  // should return true
        System.out.println(isOneAway("abcdef", "abccef"));  // should return true
        System.out.println(isOneAway("abcdef", "abcde"));  // should return true
        System.out.println(isOneAway("aaa", "abc"));  // should return false
        System.out.println(isOneAway("abcde", "abc"));  // should return false
        System.out.println(isOneAway("abc", "abcde"));  // should return false
        System.out.println(isOneAway("abc", "bcc"));  // should return false
        System.out.println(isOneAway("a", "a"));  // should return true
    }

    // Implement your solution below.
    public static Boolean isOneAway(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        if (Math.abs(l1 - l2) > 1) {
            return false;
        }

        for (int i = 0; i < Math.min(l1, l2); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (l1 == l2) {
                    return s1.substring(i + 1).equals(s2.substring(i + 1));
                } else if (l1 < l2) {
                    return s1.substring(i).equals(s2.substring(i + 1));
                } else {
                    return s2.substring(i).equals(s1.substring(i + 1));
                }
            }
        }
        return Math.abs(l1 - l2) <= 1;
    }
}
