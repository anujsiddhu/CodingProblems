package com.aks.code.systemdesign.trafficcontrolsystem;

public enum States {
    RED,
    GREEN,
    ORANGE;

    public States nextState() {
        if (this == States.GREEN) return States.ORANGE;
        if (this == States.ORANGE) return States.RED;
        if (this == States.RED) return States.GREEN;
        return this;
    }
}
