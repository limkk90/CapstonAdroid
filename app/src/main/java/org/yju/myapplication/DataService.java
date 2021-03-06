package org.yju.myapplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.yju.myapplication.Community.BoardApi;
import org.yju.myapplication.MyPage.MyPageApi;
import org.yju.myapplication.Reservation.ReservationApi;
import org.yju.myapplication.data.Email;
import org.yju.myapplication.data.Marker;
import org.yju.myapplication.data.User;
import org.yju.myapplication.qna.QnaApi;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class DataService {
    private String BASE_URL = "http://192.168.1.75:7777/"; // TODO REST API 퍼블릭 IP로 변경

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    Retrofit retrofitClient =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(new OkHttpClient.Builder().build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

    SelectAPI select = retrofitClient.create(SelectAPI.class);
    InsertAPI insert = retrofitClient.create(InsertAPI.class);
    UpdateAPI update = retrofitClient.create(UpdateAPI.class);
    DeleteAPI delete = retrofitClient.create(DeleteAPI.class);
    public BoardApi  boardApi = retrofitClient.create(BoardApi.class);
    public ReservationApi reservationApi = retrofitClient.create(ReservationApi.class);
    public QnaApi qnaApi = retrofitClient.create(QnaApi.class);
    public MyPageApi myPageApi = retrofitClient.create(MyPageApi.class);
}

interface SelectAPI{
    @GET("select/{id}")
    Call<Member> selectOne(@Path("id") long id);

    @GET("select")
    Call<List<Member>> selectAll();

    @POST("api/andLogin")
    Call<String> login(@Body User user);

    @POST("api/profile/{u_id}")
    Call<User> prifile(@Path("u_id") String u_id);

    @POST("api/findId/{u_email}")
    Call<User> findId(@Path("u_email") String u_email);

    @POST("api/email")
    Call<String> sendMail(@Body Email email);

    @POST("api/confirm")
    Call<Integer> confirm(@Body Email email);


    @GET("api/map/marker")
    Call<ArrayList<Marker>> getMarker();

    // 추가하기

}

interface InsertAPI{
    @POST("insert")
    Call<Member> insertOne(@Body Map<String, String> map);

    @POST("api/join")
    Call<String> signup(@Body User user);


}

interface UpdateAPI{
    @POST("update/{id}")
    Call<Member> updateOne(@Path("id") long id, @Body Map<String, String> map);

    @POST("api/update/{u_id}")
    Call<User> update(@Body User user);


}

interface DeleteAPI{
    @POST("delete/{id}")
    Call<ResponseBody> deleteOne(@Path("id") long id);
}

