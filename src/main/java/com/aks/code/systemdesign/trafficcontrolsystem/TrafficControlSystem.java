package com.aks.code.systemdesign.trafficcontrolsystem;

// state design pattren
public class TrafficControlSystem {
    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight1();
        trafficLight.start();
    }
}
