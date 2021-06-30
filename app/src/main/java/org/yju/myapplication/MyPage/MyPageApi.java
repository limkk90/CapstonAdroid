package org.yju.myapplication.MyPage;

import org.yju.myapplication.data.Board;
import org.yju.myapplication.data.Rsvt;
import org.yju.myapplication.data.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyPageApi {

    @POST("api/updated/{u_id}")
    Call<String> updateUser(@Body User user);

    @GET("api/{u_id}/boardlist")
    Call<ArrayList<Board>> myBoard(@Path("u_id") String u_id);

    @GET("api/{u_id}/reservation")
    Call<ArrayList<Rsvt>> myRsvt(@Path("u_id") String u_id);
}
