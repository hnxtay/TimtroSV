package com.dev.kd1412.timtrosv.network;

import com.dev.kd1412.timtrosv.model.Room;
import com.dev.kd1412.timtrosv.model.Rooms;
import com.dev.kd1412.timtrosv.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RoomService {
    @GET("/rooms")
    Call<List<Room>> getRooms();

    @GET("/rooms/{id}")
    Call<Room> getRoom(@Path("id") int id);

    @POST("/rooms")
    Call<Room> postRoom(@Body Room room);

    @GET("/users")
    Call<List<User>> getUsers();

    @GET("/users/{id}")
    Call<User> getUser(@Path("id") String id);

    @POST("/users")
    Call<User> postUser(@Body User user);


}
