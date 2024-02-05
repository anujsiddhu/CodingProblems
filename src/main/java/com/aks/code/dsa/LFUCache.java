package com.aks.code.dsa;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache {
    private DLLNode head;
    private final int capacity;
    private final Map<Integer, Integer> values;
    private final Map<Integer, DLLNode> nodes;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        values = new HashMap<>();
        nodes = new HashMap<>();
        head = null;
    }

    public int get(int key) {
        if (values.containsKey(key)) {
            increaseFreq(key);
            return values.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (values.containsKey(key)) {
            values.put(key, value);
        } else {
            if (values.size() < capacity) {
                values.put(key, value);
            } else {
                removeOld();
                values.put(key, value);
            }
            addToHead(key);
        }
        increaseFreq(key);
    }

    private void addToHead(int key) {
        if (head == null) {
            head = new DLLNode(0);
            head.keys.add(key);
        } else if (head.count > 0) {
            DLLNode node = new DLLNode(0);
            node.keys.add(key);
            node.next = head;
            head.prev = node;
            head = node;
        } else {
            head.keys.add(key);
        }
        nodes.put(key, head);
    }

    private void increaseFreq(int key) {
        DLLNode node = nodes.get(key);
        node.keys.remove(key);

        if (node.next == null) {
            node.next = new DLLNode(node.count + 1);
            node.next.prev = node;
            node.next.keys.add(key);
        } else if (node.next.count == node.count + 1) {
            node.next.keys.add(key);
        } else {
            DLLNode tmp = new DLLNode(node.count + 1);
            tmp.keys.add(key);
            tmp.prev = node;
            tmp.next = node.next;
            node.next.prev = tmp;
            node.next = tmp;
        }

        nodes.put(key, node.next);
        if (node.keys.isEmpty()) {
            remove(node);
        }
    }

    private void removeOld() {
        if (head == null) {
            return;
        }
        int old = head.keys.stream().findFirst().orElse(0);
        head.keys.remove(old);
        if (head.keys.isEmpty()) {
            remove(head);
        }
        nodes.remove(old);
        values.remove(old);
    }

    private void remove(DLLNode node) {
        if (node.prev == null) {
            head = node.next;
        } else {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
    }

    static class DLLNode {
        int count;
        LinkedHashSet<Integer> keys;
        DLLNode prev;
        DLLNode next;

        public DLLNode(int count) {
            this.count = count;
            keys = new LinkedHashSet<>();
            prev = next = null;
        }
    }
}
