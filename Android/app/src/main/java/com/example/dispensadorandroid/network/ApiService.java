package com.example.dispensadorandroid.network;

import com.example.dispensadorandroid.entities.AccessToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @POST("signup")
    @FormUrlEncoded
    Call<AccessToken> signup (@Field("name") String name, @Field("email") String email, @Field("password") String password, @Field("password_confirmation") String password_confirmation);


}
