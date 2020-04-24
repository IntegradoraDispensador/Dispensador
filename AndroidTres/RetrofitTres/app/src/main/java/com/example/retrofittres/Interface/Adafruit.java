package com.example.retrofittres.Interface;

import com.example.retrofittres.Model.Example;
import com.example.retrofittres.ModelUltrasonico.ExampleUltrasonico;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Adafruit {

    @POST("data")
    Call<Example> getDistancia(@Body Example example, @Header("X-AIO-Key") String token);

    @GET("data")
    Call<List<ExampleUltrasonico>> traerData(@Header("X-AIO-Key") String token, @Query("limit") int limite);
}
