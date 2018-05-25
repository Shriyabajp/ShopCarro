package com.example.shriyabajpai.logindemo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IUser {
    @POST("getAll")
    Call<Boolean> doLogin(@Body User user);
}
