package com.example.timetable;

public class Room {
    private int rooId;
    private String room;
    private int deleted;

    public int getRooId() {
        return rooId;
    }

    public void setRooId(int rooId) {
        this.rooId = rooId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public Room(int rooId, String room, int deleted) {
        this.rooId = rooId;
        this.room = room;
        this.deleted = deleted;
    }

    public Room() {
    }
}
