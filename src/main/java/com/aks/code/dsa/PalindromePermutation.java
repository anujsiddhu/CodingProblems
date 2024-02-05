package com.aks.code.dsa;

// https://leetcode.com/problems/palindrome-permutation/
public class PalindromePermutation {
    public static void main(String[] args) {
        System.out.println(new PalindromePermutation().canPermutePalindrome("code"));
        System.out.println(new PalindromePermutation().canPermutePalindrome("aab"));
        System.out.println(new PalindromePermutation().canPermutePalindrome("carer ac"));
        System.out.println(new PalindromePermutation().canPermutePalindrome("tact coa"));
    }

    private boolean canPermutePalindrome(String s) {
        int[] charr = new int[26];

        for(char  c : s.toCharArray()) {
            if(c == ' ') {
                continue;
            }
            int index = c - 'a';
            charr[index]++;
        }

        int count = 0;
        for(int i = 0; i<26; i++) {
            if(charr[i] != 0) {
                if(charr[i] % 2 != 0) {
                    count++;
                }
            }
        }
        return count <= 1;
    }
}
