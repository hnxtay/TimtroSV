package com.dev.kd1412.timtrosv.network;

import com.dev.kd1412.timtrosv.model.Room;
import com.dev.kd1412.timtrosv.model.Rooms;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RoomService {
    @GET("/rooms")
    Call<Rooms> getRooms();
}
