package org.yju.myapplication.Reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapTapi;
import com.skt.Tmap.TMapView;

import org.yju.myapplication.R;

public class SearchRoad extends AppCompatActivity {
    Intent intent;
    TMapView tmapview;
    double stat_lat, stat_long;
    float stat_latF, stat_longF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_road);
        intent = getIntent();
        stat_lat = intent.getDoubleExtra("statLat", 0);
        stat_long = intent.getDoubleExtra("statLong", 0);

        LinearLayout linearLayoutTmap = (LinearLayout)findViewById(R.id.tmap);
        tmapview = new TMapView(this);
        tmapview.setSKTMapApiKey("l7xx8ab0ebd2f81548f586a1838fdbe5bc1b");
        tmapview.setHttpsMode(true);

        stat_latF = (float) stat_lat; //37.41787629
        stat_longF = (float) stat_long; //128.0027692

        //거류체육공원 주차장 128.405541
        //거류체육공원 주차장 34.983127
        TMapTapi tMapTapi = new TMapTapi(this); //Tmap실행
//        tMapTapi.invokeRoute("T타워", 126.984098f,37.566385f);  //여기 주소 넣어야됨
//        tMapTapi.invokeSetLocation("영진전문대", stat_longF, stat_latF);
        tMapTapi.invokeRoute("T타워", stat_longF, stat_latF);  //여기 주소 넣어야됨

//        tMapTapi.invokeRoute("T타워", (float) stat_long,(float) stat_lat);
//        tMapTapi.invokeNavigate("영진전문대", (float) stat_long, (float) stat_lat, 0, true);


        linearLayoutTmap.addView(tmapview);


        //PloyLine
        TMapPoint tMapPointStart = new TMapPoint(35.855770, 128.606922);

        Log.i("searchRoad", "onCreate: " + stat_lat);
        Log.i("searchRoad", "onCreate: " + stat_long);
        Log.i("searchRoad", "이렇게바뀌네요: " + stat_latF);
        Log.i("searchRoad", "이렇게바뀌네요: " + stat_longF);

        TMapPoint tMapPointEnd = new TMapPoint(stat_lat, stat_long);
        FindCarPathTask findCarPathTask = new FindCarPathTask(getApplicationContext(), tmapview);
        findCarPathTask.execute(tMapPointStart, tMapPointEnd);
        tmapview.setCenterPoint( 128.606922, 35.855770 );

    }
}