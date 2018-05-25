package com.example.shriyabajpai.logindemo;

import android.app.Application;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppController extends Application {

    private static Retrofit retrofit = null;
    public static AppController mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static AppController getInstance() {
        return mInstance;
    }

    public Retrofit getClient() {
        if (null == retrofit) {
            OkHttpClient client = new OkHttpClient.Builder().build();
            // Set the base url

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.177.2.111:8080/employee/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }


}
