package com.aks.code.dsa;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

// https://leetcode.com/problems/dinner-plate-stacks/
public class DinnerPlates {
    List<Stack<Integer>> stList;
    TreeSet<Integer> available;
    int capacity;

    public DinnerPlates(int capacity) {
        stList = new ArrayList<>();
        available = new TreeSet<>();
        this.capacity = capacity;
    }

    public void push(int val) {
        if (available.isEmpty()) {
            stList.add(new Stack<>());
            available.add(stList.size() - 1);
        }
        Stack<Integer> leftStackSpace = stList.get(available.first());
        leftStackSpace.push(val);

        if (leftStackSpace.size() == capacity) {
            available.pollFirst();
        }
    }

    public int pop() {
        if (stList.isEmpty()) {
            return -1;
        }
        int val = stList.get(stList.size() - 1).pop();
        available.add(stList.size() - 1);

        while (!stList.isEmpty() && stList.get(stList.size() - 1).isEmpty()) {
            stList.remove(stList.size() - 1);
            available.pollLast();
        }

        return val;
    }

    public int popAtStack(int index) {
        if (index >= stList.size()) {
            return -1;
        }
        if (index == stList.size() - 1) {
            return pop();
        }

        Stack<Integer> requiredStack = stList.get(index);
        int val = requiredStack.isEmpty() ? -1 : requiredStack.pop();
        available.add(index);
        return val;
    }
}
