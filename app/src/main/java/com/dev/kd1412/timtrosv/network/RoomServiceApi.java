package com.dev.kd1412.timtrosv.network;

import com.dev.kd1412.timtrosv.model.Room;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoomServiceApi {
    public final static String BASE_URL = "http://192.168.42.113:3000";
    private static RoomService roomService;

    public synchronized static RoomService getInstance() {
        if (roomService == null) {
            Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory
                    .create()).baseUrl(BASE_URL).build();
            roomService = retrofit.create(RoomService.class);

            return roomService;
        }
        return roomService;
    }

    ;
}
