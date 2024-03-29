package com.aks.code.systemdesign.idgenerator;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.SecureRandom;
import java.util.Enumeration;

public class GetNodeInfo {

    public static Integer generatingNodeId() {
        int maxNodeVal = (int) Math.pow(2, Constants.NODE_ID_BIT_LEN);
        int nodeId;
        try {
            StringBuilder sb = new StringBuilder();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                byte[] mac = networkInterface.getHardwareAddress();
                if (mac != null) {
                    for (int i = 0; i < mac.length; i++) {
                        sb.append(String.format("%02X", mac[i]));
                    }
                }
            }
            nodeId = sb.toString().hashCode();
        } catch (SocketException ex) {
            //in case of exception get a random number limited by max node size
            nodeId = (int) (new SecureRandom().nextInt() % Math.pow(2, 10));
        }
        nodeId = nodeId & maxNodeVal;
        return nodeId;
    }
}
