package com.example.retrofitdos.Interface;

import com.example.retrofitdos.Models.Servomotor.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface Adafruit {

    @GET("onoff")
    Call<Example> getDistancia(@Header("X-AIO-Key") String token);
}
