package com.aks.code.dsa;

import java.util.*;

// https://leetcode.com/problems/maximum-frequency-stack/?envType=list&envId=ojr0xpim

public class DesignFreqStack {
    public static void main(String[] args) {
        DesignFreqStack stack = new DesignFreqStack();
        stack.push(5);
        stack.push(7);
        stack.push(5);
        stack.push(7);
        stack.push(4);
        stack.push(5);
        System.out.println("push " + stack.set);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    Map<Integer, Node> map;
    TreeSet<Node> set;
    int c;

    public DesignFreqStack() {
        map = new HashMap<>();
        set = new TreeSet<>();
        c = 0;
    }

    public void push(int val) {
        Node nn;
        if (map.containsKey(val)) {
            nn = map.get(val);
            nn.pos.push(c++);
            removeId(val);
        } else {
            nn = new Node(val);
            nn.pos.push(c++);
            map.put(val, nn);
        }
        set.add(nn);
//        System.out.println("push " + set);
    }

    public int pop() {
        Node last = set.first();
        last.pos.pop();
        removeId(last.val);
        if (last.pos.isEmpty()) {
            map.remove(last.val);
        } else {
            set.add(last);
        }
//        System.out.println("pop " + set);
        return last.val;
    }

    void removeId(int id) {
        Iterator<Node> itr = set.iterator();
        while (itr.hasNext()) {
            if (itr.next().val == id) {
                itr.remove();
                break;
            }
        }
    }


    class Node implements Comparable {
        int val;
        Stack<Integer> pos;

        Node(int v) {
            val = v;
            pos = new Stack<>();
        }


        @Override
        public int hashCode() {
            return val;
        }

        @Override
        public boolean equals(Object obj) {
            return val == ((Node) obj).val;
        }

        public String toString() {
            return val + " " + pos + " ";
        }

        @Override
        public int compareTo(Object o) {
            Node a = (Node) o;
            if (pos.isEmpty()) {
                return 1;
            } else if (a.pos.isEmpty()) {
                return -1;
            } else if (pos.size() == a.pos.size()) {
                return a.pos.peek() - pos.peek();
            } else {
                return a.pos.size() - pos.size();
            }
        }
    }
}