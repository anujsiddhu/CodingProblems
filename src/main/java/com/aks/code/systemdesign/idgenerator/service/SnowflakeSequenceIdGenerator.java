package com.aks.code.systemdesign.idgenerator.service;

import com.aks.code.systemdesign.idgenerator.Constants;
import com.aks.code.systemdesign.idgenerator.GetNodeInfo;
import com.aks.code.systemdesign.idgenerator.exception.NodeIdOutOfBoundException;

import java.time.Instant;

public class SnowflakeSequenceIdGenerator implements IdGenerator {
    private final Integer generatingNodeId;

    private final long maxSequence = (long) Math.pow(2, Constants.SEQUENCE_BIT_LEN);
    private final int maxNodeVal = (int) Math.pow(2, Constants.NODE_ID_BIT_LEN);
    private final long EPOCH_START = Instant.EPOCH.toEpochMilli();

    private volatile long currentSequence = -1L;
    private final Object lock = new Object();
    private volatile long lastTimestamp = -1L;


    public SnowflakeSequenceIdGenerator() throws NodeIdOutOfBoundException {
        generatingNodeId = GetNodeInfo.generatingNodeId();
        checkNodeIdBounds();
    }

    public void checkNodeIdBounds() throws NodeIdOutOfBoundException {
        if (generatingNodeId < 0 || generatingNodeId > maxNodeVal) {
            throw new NodeIdOutOfBoundException("Node id is < 0 or > " + maxNodeVal);
        }
    }

    @Override
    public String generateId() throws NodeIdOutOfBoundException {
        checkNodeIdBounds();
        synchronized (lock) {
            long currentTimeStamp = getTimeStamp();
            if (currentTimeStamp == lastTimestamp) {
                currentSequence = (currentSequence + 1) % maxSequence;
                if (currentSequence != 0) {
                    currentTimeStamp = waitNextMillis(currentTimeStamp);
                }
            } else {
                currentSequence = 0;
            }
            lastTimestamp = currentTimeStamp;
            long id = currentTimeStamp << (Constants.NODE_ID_BIT_LEN + Constants.SEQUENCE_BIT_LEN);
            id |= ((long) generatingNodeId << Constants.SEQUENCE_BIT_LEN);
            id |= currentSequence;
            return String.valueOf(id);
        }
    }

    private long getTimeStamp() {
        return Instant.now().toEpochMilli() - EPOCH_START;
    }

    private long waitNextMillis(long currentTimeStamp) {
        while (currentTimeStamp == lastTimestamp) {
            currentTimeStamp = getTimeStamp();
        }
        return currentTimeStamp;
    }
}
