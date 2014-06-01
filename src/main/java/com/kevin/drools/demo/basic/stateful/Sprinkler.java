package com.kevin.drools.demo.basic.stateful;

/**
 * @author Kevin
 * @date 2014/4/22.
 */
public class Sprinkler {
    private Room room;
    private boolean on;

    public Sprinkler(Room room) {
        this.room = room;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
