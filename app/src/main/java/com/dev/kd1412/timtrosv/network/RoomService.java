/*
 * Copyright (c) 2020.  by kd1412
 */

package com.dev.kd1412.timtrosv.network;

import com.dev.kd1412.timtrosv.model.Room;
import com.dev.kd1412.timtrosv.model.Rooms;
import com.dev.kd1412.timtrosv.model.User;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RoomService {
    @GET("/rooms")
    Call<List<Room>> getRooms();

    @GET("/rooms/{id}")
    Call<Room> getRoom(@Path("id") int id);

    @GET("/rooms/userId/{user_id}")
    Call<List<Room>> getRoomByUserID(@Path("user_id") String user_id);

    @PUT("rooms/{id}")
    Call<Room> updateRoom(@Body Room room, @Path("id") int id);

    @DELETE("rooms/{id}")
    Call<Room> deleteRoom(@Path("id") int id);

    @GET("/rooms/location/{location}")
    Call<List<Room>> getRoomLocation(@Path("location")  String location);

    @Multipart
    @POST("/rooms")
    Call<Room> postRoom(@Part("room") Room room, @Part MultipartBody.Part part);

    @GET("/users")
    Call<List<User>> getUsers();

    @GET("/users/{id}")
    Call<User> getUser(@Path("id") String id);

    @POST("/users")
    Call<User> postUser(@Body User user);


}
