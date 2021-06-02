package org.yju.myapplication.Reservation;

import org.yju.myapplication.data.Charger;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ReservationApi {
    //충전소안에 충전기 가져오기
    @GET("api/{stat_id}/chargerlist")
    Call<Charger> getCharger(@Path("stat_id") String stat_id);
}
