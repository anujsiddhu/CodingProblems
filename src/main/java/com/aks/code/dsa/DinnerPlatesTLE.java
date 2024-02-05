package com.aks.code.dsa;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// https://leetcode.com/problems/dinner-plate-stacks/
public class DinnerPlatesTLE {
    List<Stack<Integer>> stList;
    int capacity;
    int li;
    int ri;

    public DinnerPlatesTLE(int capacity) {
        stList = new ArrayList<>();
        stList.add(new Stack<>());
        this.capacity = capacity;
        li = 0;
        ri = 0;
    }

    public void push(int val) {
        if (stList.size() <= li || stList.get(li) == null) {
            stList.add(new Stack<>());
        }
        Stack<Integer> st = stList.get(li);
        if (st.size() == capacity) {
            li++;
            push(val);
        } else {
            ri = Integer.max(li, ri);
            st.push(val);
        }
    }

    public int pop() {
        Stack<Integer> st = stList.get(ri);
        if (st == null || st.isEmpty()) {
            if (ri == 0) {
                return -1;
            }
            ri--;
            return pop();
        }
        return st.pop();
    }

    public int popAtStack(int i) {
        if (stList.size() <= i) {
            return -1;
        }
        Stack<Integer> st = stList.get(i);
        if (st == null || st.isEmpty()) {
            return -1;
        }
        li = i;
        return st.pop();
    }
}
