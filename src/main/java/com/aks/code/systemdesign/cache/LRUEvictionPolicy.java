package com.aks.code.systemdesign.cache;

public class LRUEvictionPolicy implements EvictionPolicy {

    private final DLLNodeService dllNodeService;

    public LRUEvictionPolicy() {
        this.dllNodeService = new DLLNodeService();
    }
    @Override
    public DLLNode nodeAccessed(DLLNode node) {
        dllNodeService.moveToFront(node);
        return dllNodeService.getHead();
    }

    @Override
    public DLLNode evict() {
        dllNodeService.deleteNode(dllNodeService.getTail());
        return dllNodeService.getHead();
    }

    @Override
    public DLLNode delete(DLLNode node) {
        dllNodeService.deleteNode(node);
        return dllNodeService.getHead();
    }
}
