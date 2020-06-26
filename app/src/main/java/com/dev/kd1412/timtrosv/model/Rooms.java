package com.dev.kd1412.timtrosv.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Rooms {
    @SerializedName("rooms")
    private List<Room> rooms;

    public List<Room> getRooms() {
        return rooms;
    }
}
