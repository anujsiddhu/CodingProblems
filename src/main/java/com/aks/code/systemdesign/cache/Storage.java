package com.aks.code.systemdesign.cache;


import java.util.Map;

public interface Storage {

    DLLNode put(String key, String value);

    DLLNode get(String key);

    DLLNode delete(String key);
}

