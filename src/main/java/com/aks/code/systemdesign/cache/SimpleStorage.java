package com.aks.code.systemdesign.cache;

import java.util.HashMap;
import java.util.Map;

public class SimpleStorage implements Storage {
    private final Map<String, DLLNode> map;

    public SimpleStorage() {
        map = new HashMap<>();

    }

    @Override
    public DLLNode put(String key, String value) {
        DLLNode node = new DLLNode(key, value);
        if (map.containsKey(key)) {
            node = map.get(key);
            node.setValue(value);
        }
        map.put(key, node);
        return node;
    }

    @Override
    public DLLNode get(String key) {
        return map.get(key);
    }

    @Override
    public DLLNode delete(String key) {
        DLLNode node = map.get(key);
        if (node != null) {
            map.remove(key);
        }
        return node;
    }
}
