package com.aks.code.systemdesign.cache;

public interface EvictionPolicy {

    DLLNode nodeAccessed(DLLNode node);

    DLLNode evict();

    DLLNode delete(DLLNode node);

}
