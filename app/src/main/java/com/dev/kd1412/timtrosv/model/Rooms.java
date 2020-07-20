package com.dev.kd1412.timtrosv.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Rooms {
    @SerializedName("rooms")
    private List<Room> rooms;

    public Rooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
