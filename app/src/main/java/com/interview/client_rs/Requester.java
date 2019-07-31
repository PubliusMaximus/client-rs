package com.interview.client_rs;

import android.app.Application;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Requester extends Application {
    public static final int textSize = 10;
    private static RequestInterface webserviceApi;
    private Retrofit retrofit;
    @Override
    public void onCreate() {
        super.onCreate();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://service-rs.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webserviceApi = retrofit.create(RequestInterface.class);
    }
    public static RequestInterface getApi() {
        return webserviceApi;
    }
}
