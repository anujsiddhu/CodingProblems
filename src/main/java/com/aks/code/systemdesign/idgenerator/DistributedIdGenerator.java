package com.aks.code.systemdesign.idgenerator;

import com.aks.code.systemdesign.idgenerator.service.IdGenerator;
import com.aks.code.systemdesign.idgenerator.service.SnowflakeSequenceIdGenerator;
import com.aks.code.systemdesign.idgenerator.service.UUIDGenerator;

public class DistributedIdGenerator {
    public static void main(String[] args) throws Exception {
        IdGenerator uuidGenerator = new UUIDGenerator();
        System.out.println(uuidGenerator.generateId());

        IdGenerator sequenceGenerator = new SnowflakeSequenceIdGenerator();
        System.out.println(sequenceGenerator.generateId());
        System.out.println(sequenceGenerator.generateId());
        System.out.println( new SnowflakeSequenceIdGenerator().generateId());
    }
}
