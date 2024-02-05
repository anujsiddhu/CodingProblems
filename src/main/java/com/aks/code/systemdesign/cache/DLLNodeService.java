package com.aks.code.systemdesign.cache;

import lombok.Data;

@Data
public class DLLNodeService {
    private DLLNode head, tail;

    public DLLNodeService() {
        head = null;
        tail = null;
    }

    // always add to head
    public DLLNode addNode(DLLNode node) {

        return head;
    }

    // detached node from list
    public DLLNode detached(DLLNode node) {

        return node;
    }

    public void deleteNode(DLLNode node) {

    }

    public DLLNode moveToFront(DLLNode node) {

        return head;
    }
}
