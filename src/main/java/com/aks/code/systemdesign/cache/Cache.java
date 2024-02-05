package com.aks.code.systemdesign.cache;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Cache {
    private Storage storage;
    private final EvictionPolicy evictionPolicy;
    private final Integer capacity;
    private int currentSize;

    public Cache(EvictionPolicy evictionPolicy, Integer capacity) {
        this.storage = new SimpleStorage();
        this.evictionPolicy = evictionPolicy;
        this.capacity = capacity;
        this.currentSize = 0;
    }

    public String put(String key, String value) {
        if (currentSize >= capacity) {
            // evict
        }
        storage.put(key, value);
        return value;
    }

    public String get(String key) {
        DLLNode node =  storage.get(key);
        evictionPolicy.nodeAccessed(node);
        return node != null ? node.getValue() : null;
    }

    public void delete(String key) {
        DLLNode node =  storage.delete(key);
        evictionPolicy.delete(node);
    }
}
