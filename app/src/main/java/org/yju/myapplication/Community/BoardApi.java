package org.yju.myapplication.Community;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.yju.myapplication.data.Board;
import org.yju.myapplication.data.Reply;
import org.yju.myapplication.data.Warning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BoardApi {
    // 게시글전체리스트
    @GET("api/allboardlist")
    Call<ArrayList<Board>> ListCall();

    // 팁게시판
    @GET("api/boardlist?cat_cd=2")
    Call<ArrayList<Board>> TipBoard();

    // 자유게시판
    @GET("api/boardlist?cat_cd=1")
    Call<ArrayList<Board>> FreeBoard();

    // 글조회
    @POST("api/board/{b_no}")
    Call<Map<String, Object>> gBoard(@Path("b_no") String b_no);

    // 글작성
    @POST("api/board/make")
    Call<String> boardInsert(@Body Board board);

    // 글삭제
    @POST("api/board/remove")
    Call<Void> removeBoard(@Body Board board);

    // 글수정
    @PUT("api/board/update")
    Call<Void> updateBoard(@Body Board board);

    // 공지사항
    @GET("api/main/notify")
    Call<ArrayList<Board>> BoardNotify();

    // 뉴스
    @GET("api/main/news")
    Call<JsonArray> news();

    //댓글 삭제
    @POST("api/reply/delete")
    Call<Void> replyDelete(@Body Reply reply);
    // post로 바꾼후 fragment확인하기!

    // 댓글 등록
    @POST("api/reply")
    Call<String> replyAdd(@Body Reply reply);

    // 신고 하기
    @POST("api/user/warning")
    Call<Void> warning(@Body Warning warning);
}
