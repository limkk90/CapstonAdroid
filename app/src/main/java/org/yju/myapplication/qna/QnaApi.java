package org.yju.myapplication.qna;


import org.yju.myapplication.data.Question;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface QnaApi {

    // 문의 등록
    @POST("api/question")
    Call<Void> setQ(@Body Question question);

    // 문의리스트
    @GET("api/questions")
    Call<ArrayList<Question>> qList();
}
