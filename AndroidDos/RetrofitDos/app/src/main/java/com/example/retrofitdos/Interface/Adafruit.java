package com.example.retrofitdos.Interface;


import com.example.retrofitdos.Model.Example;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Adafruit {

    @POST("data")
    Call<Example> getDistancia(@Body Example example, @Header("X-AIO-Key") String token);
}
