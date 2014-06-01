package com.kevin.drools.demo.basic.stateful;

/**
 * @author Kevin
 * @date 2014/4/22.
 */
public class Fire {
    private Room room;

    public Fire(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
