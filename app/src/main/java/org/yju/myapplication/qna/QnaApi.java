package org.yju.myapplication.qna;


import org.yju.myapplication.data.Question;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface QnaApi {

    // 문의 등록
    @POST("api/question")
    Call<Void> setQ(@Body Question question);

    // 문의리스트
    @GET("api/questions")
    Call<ArrayList<Question>> qList();

    // 나의 문의리스트
    @GET("api/{u_id}/my-question")
    Call<ArrayList<Question>> myQList(@Path("u_id") String u_id);

    // 문의 조회
    @GET("api/question/go/{q_dtt}")
    Call<Map<String, Object>> gQuest(@Path("q_dtt") String q_dtt);
}
