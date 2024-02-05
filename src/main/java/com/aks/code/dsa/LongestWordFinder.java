package com.aks.code.dsa;

// https://techdevguide.withgoogle.com/resources/former-interview-question-find-longest-word/#!

import java.util.*;

// incorrect
public class LongestWordFinder {

    public static void main(String[] args) {
        String letters = "abppplee";
        List<String> words = Arrays.asList("ablee", "ale", "aple", "bale", "kangaroo");
        String result = findLongestWordInString(letters, words);

        System.out.println(result);
    }

    public static String findLongestWordInString(String letters, List<String> words) {
        Map<Character, List<Integer>> letterPositions = new HashMap<>();

        // For each letter in 'letters', collect all the indices at which it appears.
        // O(#letters) space and speed.
        for (int index = 0; index < letters.length(); index++) {
            char letter = letters.charAt(index);
            letterPositions.computeIfAbsent(letter, k -> new ArrayList<>()).add(index);
        }

        // For words, in descending order by length...
        // Bails out early on the first matched word and within the word on
        // impossible letter/position combinations, but the worst case is
        // O(#words * avg-len) * O(#letters / 26) time; constant space.
        // With some work, it could be O(#W * avg-len) * log2(#letters/26)
        // But since binary search has more overhead
        // than simple iteration, log2(#letters) is about as
        // expensive as simple iterations as long as
        // the length of the arrays for each letter is "small".
        // If letters are randomly present in the
        // search string, the log2 is about equal in speed to simple traversal
        // up to lengths of a few hundred characters.

        words.sort((w1, w2) -> Integer.compare(w2.length(), w1.length()));

        for (String word : words) {
            int pos = -1;
            boolean isValid = true;

            for (char letter : word.toCharArray()) {
                if (!letterPositions.containsKey(letter)) {
                    isValid = false;
                    break;
                }

                List<Integer> possiblePositions = letterPositions.get(letter);

                // Find the next valid position greater than the current position.
                int nextPos = pos + 1;
                while (nextPos < possiblePositions.size() && possiblePositions.get(nextPos) <= pos) {
                    nextPos++;
                }

                if (nextPos == possiblePositions.size()) {
                    isValid = false;
                    break;
                }
                if(nextPos < possiblePositions.size())
                    pos = possiblePositions.get(nextPos);
            }

            if (isValid) {
                return word;
            }
        }

        return null;
    }

}

