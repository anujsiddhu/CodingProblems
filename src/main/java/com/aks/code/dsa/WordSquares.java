package com.aks.code.dsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://techdevguide.withgoogle.com/resources/former-interview-question-word-squares
// https://leetcode.ca/all/425.html
public class WordSquares {
    public static void main(String[] args) {
        String[] words = {"AREA", "BALL", "DEAR", "LADY", "LEAD", "YARD"};
        List<List<String>> wordSquare = new WordSquares().wordSquare(words);
        System.out.println(wordSquare);
    }

    private List<List<String>> wordSquare(String[] words) {
        List<List<String>> res = new ArrayList<>();
        int k = words[0].length();
        Map<String, List<String>> preMap = new HashMap<>();
        buildPrefixMap(words, preMap);

        for (String str : words) {
            List<String> list = new ArrayList<>();
            list.add(str);
            backtracking(1, k, list, res, preMap);
        }

        return res;
    }

    private void backtracking(int i, int k, List<String> list, List<List<String>> res, Map<String, List<String>> preMap) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s.charAt(i));
        }
        List<String> prefix = preMap.getOrDefault(sb.toString(), new ArrayList<>());
        for (String pr : prefix) {
            list.add(pr);
            backtracking(i + 1, k, list, res, preMap);
            list.remove(list.size() - 1);
        }
    }

    private void buildPrefixMap(String[] words, Map<String, List<String>> map) {
        for (String str : words) {
            for (int i = 1; i <= str.length(); i++) {
                String pre = str.substring(0, i);
                map.putIfAbsent(pre, new ArrayList<>());
                map.get(pre).add(str);
            }
        }
    }
}