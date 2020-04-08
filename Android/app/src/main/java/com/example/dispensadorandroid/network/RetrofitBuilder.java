package com.example.dispensadorandroid.network;

import com.example.dispensadorandroid.BuildConfig;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

public class RetrofitBuilder {

    private static final String BASE_URL = "http://127.0.0.1:8000/api/";

    private static OkHttpClient client;

    private static Retrofit retrofit;

    private static OkHttpClient buildClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Request request = chain.request();

                        Request.Builder builder =  request.newBuilder()
                                .addHeader("Accept", "application/json ")
                                .addHeader("Connection", "close");


                        request = builder.build();

                        return chain.proceed(request);

                    }
                });

        if (BuildConfig.DEBUG){
            builder.addNetworkInterceptor(new StethoInterceptor());
        }

        return builder.build();
    }





}
