package org.yju.myapplication.Reservation;

import org.yju.myapplication.data.Charger;
import org.yju.myapplication.data.Rsvt;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReservationApi {

    //충전소안에 충전기 가져오기
    @GET("api/{stat_id}/chargerlist")
    Call<List<Charger>> getCharger(@Path("stat_id") String stat_id);

    //예약하기
    @POST("api/reservation")
    Call<Boolean> insertRsvt(@Body Rsvt rsvt);

    //충전 싹 다 조회
    @GET("api/todays-reservation/{chg_id}/{stat_id}")
    Call<List<Rsvt>> getTodaysRsvt(@Path("chg_id") String chg_id, @Path("stat_id") String stat_id);


}
