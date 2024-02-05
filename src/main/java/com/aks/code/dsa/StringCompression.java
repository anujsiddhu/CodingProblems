package com.aks.code.dsa;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/string-compression/
public class StringCompression {
    public static void main(String[] args) {
        System.out.println(new StringCompression().compression("aabcc"));
        System.out.println(new StringCompression().compression("aabcccccaaa"));
        Queue<Integer> qu = new LinkedList<>();
    }

    private String compression(String s) {
        StringBuilder sb = new StringBuilder();

        char pc = s.charAt(0);
        int count = 1;

        for(int i = 1; i<s.length(); i++) {
            char cc = s.charAt(i);
            if(cc == pc) {
                count++;
            } else {
                sb.append(pc).append(count);
                pc = cc;
                count = 1;
            }
        }
        sb.append(pc).append(count);
        return sb.toString();
    }
}
