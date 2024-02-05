package com.aks.code.dsa;

public class LongestPalindromicSubstring {
    int max = 0;
    int ri=0, rj=0;

    public static void main(String[] args) {

        String s = "ccc";
        LongestPalindromicSubstring lp = new LongestPalindromicSubstring();
        lp.solveTab(s);

        System.out.println(s.substring(lp.ri, lp.rj+1));
    }

    public void solveTab(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        max = 1;
        ri = 0;
        rj = 0;
        dp[0][0] = true;

        for(int j = 1; j < n; j++) {
            int i = j-1;
            dp[j][j] = true;
            if(s.charAt(i) == s.charAt(j)) {
                if(max < 2) {
                    max = 2;
                    ri = i;
                    rj = j;
                }
                dp[i][j] = true;
            }
        }

        for(int l = 3; l <= n; l++) {
            for(int i = 0; i <= n-l; i++) {
                int j = i+l-1;
                if(s.charAt(i) == s.charAt(j)) {
                    if(dp[i+1][j-1]) {
                        // update mx and result
                        if(max < l) {
                            max =  l;
                            ri = i;
                            rj = j;
                        }
                        dp[i][j] = true;
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
    }

}
