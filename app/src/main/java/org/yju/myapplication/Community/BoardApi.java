package org.yju.myapplication.Community;

import org.yju.myapplication.data.Board;
import org.yju.myapplication.data.BoardInfo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BoardApi {
    @GET("api/allboardlist")
    Call<ArrayList<Board>> ListCall();

    @GET("api/boardlist?cat_cd=2")
    Call<ArrayList<Board>> TipBoard();

    @GET("api/boardlist?cat_cd=1")
    Call<ArrayList<Board>> FreeBoard();

    @POST("api/gboard")
    Call<ArrayList<Board>> gBoard(@Body BoardInfo boardInfo);

    @POST("api/board/make")
    Call<String> boardInsert(@Body Board board);

    @POST("api/board/make")
    Call<Void> removeBoard(@Body Board board);
}
