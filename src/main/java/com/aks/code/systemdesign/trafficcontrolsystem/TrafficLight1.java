package com.aks.code.systemdesign.trafficcontrolsystem;

import java.util.HashMap;
import java.util.Map;

public class TrafficLight1 implements TrafficLight {
    private final Map<States, Integer> stateTime;
    private States currentState;

    private boolean running;

    public TrafficLight1() {
        stateTime = new HashMap<>();
        stateTime.put(States.GREEN, 10);
        stateTime.put(States.RED, 15);
        stateTime.put(States.ORANGE, 3);
        currentState = States.GREEN;
    }

    @Override
    public void start() {
        running = true;
        new Thread(() -> {
            int wait = stateTime.get(currentState);
            while (running) {
                wait--;
                System.out.println(currentState + " timer " + wait);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (wait == 0) {
                    currentState = currentState.nextState();
                    wait = stateTime.get(currentState);
                }
            }
        }).start();
    }

    public void stop() {
        running = false;
    }
}
