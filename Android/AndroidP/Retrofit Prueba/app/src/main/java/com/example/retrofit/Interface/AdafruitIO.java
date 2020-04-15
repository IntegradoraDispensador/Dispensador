package com.example.retrofit.Interface;

import com.example.retrofit.Model.Example;
import com.example.retrofit.Model.DistanciaWrapper;
import com.example.retrofit.Model.Example;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface AdafruitIO {




    @GET("ultrasonico")
    Call<Example> getDistancia(@Header("X-AIO-Key") String token);

}
