package org.yju.myapplication.MyPage;

import org.yju.myapplication.data.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MyPageApi {

    @POST("api/updated/{u_id}")
    Call<String> updateUser(@Body User user);
}
