package com.aks.code.systemdesign.idgenerator.service;

import com.aks.code.systemdesign.idgenerator.exception.NodeIdOutOfBoundException;

public interface IdGenerator {
    String generateId() throws NodeIdOutOfBoundException;
}
