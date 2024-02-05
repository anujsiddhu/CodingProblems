package com.aks.code.systemdesign.cache;

import lombok.Data;

@Data
public class DLLNode {
    private String key;
    private String value;
    private DLLNode next;
    private DLLNode previous;

    public DLLNode(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
