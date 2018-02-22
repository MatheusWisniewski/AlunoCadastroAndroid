package com.cadastro.cadastroalunos.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by matheus on 21/02/2018.
 */

public class Network {
    private static final String BASE_URL = "http://192.241.173.247/api/v1/";

    private OkHttpClient getJamesHttpClient() {
        OkHttpClient httpClient = new OkHttpClient();
        OkHttpClient.Builder httpClientBuilder = httpClient.newBuilder();
        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });

        httpClient = httpClientBuilder.build();
        return httpClient;
    }

    public Retrofit getRetrofit() {
        return new Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(getJamesHttpClient())
                .build();
    }


}
