package com.aks.code.dsa;

import java.util.*;

// https://www.baeldung.com/cs/stable-marriage-problem
public class StableMarriage {

    public static void main(String[] args) {

        Map<Character, List<Character>> men = new HashMap<>();
        men.put('J', List.of('B', 'A', 'R'));
        men.put('S', List.of('B', 'R', 'A'));
        men.put('T', List.of('R', 'B', 'A'));
        Map<Character, List<Character>> women = new HashMap<>();
        women.put('A', List.of('S', 'T', 'J'));
        women.put('B', List.of('T', 'J', 'S'));
        women.put('R', List.of('S', 'T', 'J'));

        System.out.println(match(men, women));
    }

    private static Map<Character, Character> match(Map<Character, List<Character>> men, Map<Character, List<Character>> women) {
        Map<Character, Character> match = new HashMap<>();
        Map<Character, Character> match2 = new HashMap<>();
        Set<Character> freeMen = new HashSet<>(men.keySet());
        Set<Character> freeWomen = new HashSet<>(women.keySet());

        while (!freeMen.isEmpty()) {
            char fm = getRandom(freeMen);
            List<Character> wForFm = men.get(fm);
            for (char w : wForFm) {
                boolean success = false;
                if (freeWomen.contains(w)) {
                    match.put(fm, w);
                    match2.put(w, fm);
                    success = true;
                    freeMen.remove(fm);
                    freeWomen.remove(w);
                } else {
                    char cmm = match2.get(w);
                    List<Character> wl = women.get(w);
                    int iOfFm = wl.indexOf(fm);
                    int iOfCmm = wl.indexOf(cmm);
                    if (iOfFm < iOfCmm) {
                        // change woman preference
                        freeMen.add(cmm);
                        match.put(fm, w);
                        match2.put(w, fm);
                        freeMen.remove(fm);
                        success = true;
                    }
                }
                if(success) {
                    break;
                }
            }
        }

        return match;
    }

    private static char getRandom(Set<Character> mySet) {
        List<Character> asList = new ArrayList<>(mySet);
        Collections.shuffle(asList);
        return asList.get(0);
    }

}
