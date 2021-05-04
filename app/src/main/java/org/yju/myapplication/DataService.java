package org.yju.myapplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.yju.myapplication.data.Board;
import org.yju.myapplication.data.BoardInfo;
import org.yju.myapplication.data.Criteria;
import org.yju.myapplication.data.Email;
import org.yju.myapplication.data.Marker;
import org.yju.myapplication.data.User;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class DataService {
    private String BASE_URL = "http://172.26.1.253:7777/"; // TODO REST API 퍼블릭 IP로 변경

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

    @GET("api/allboardlist")
    Call<ArrayList<Board>> ListCall();

    @GET("api/boardlist?cat_cd=2")
    Call<ArrayList<Board>> TipBoard();

    @GET("api/boardlist?cat_cd=1")
    Call<ArrayList<Board>> FreeBoard();

    @POST("api/gboard")
    Call<ArrayList<Board>> gBoard(@Body BoardInfo boardInfo);

    @GET("api/map/marker")
    Call<ArrayList<Marker>> getMarker();
    // 추가하기

}

interface InsertAPI{
    @POST("insert")
    Call<Member> insertOne(@Body Map<String, String> map);

    @POST("api/join")
    Call<String> signup(@Body User user);

    @POST("api/board/make")
    Call<String> boardInsert(@Body Board board);
}

interface UpdateAPI{
    @POST("update/{id}")
    Call<Member> updateOne(@Path("id") long id, @Body Map<String, String> map);

    @POST("api/update/{u_id}")
    Call<User> update(@Body User user);

    @POST("api/board/make")
    Call boardInsert(@Body Board board);
}

interface DeleteAPI{
    @POST("delete/{id}")
    Call<ResponseBody> deleteOne(@Path("id") long id);
}

