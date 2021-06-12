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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_road);
        intent = getIntent();

        LinearLayout linearLayoutTmap = (LinearLayout)findViewById(R.id.tmap);
        tmapview = new TMapView(this);
        tmapview.setSKTMapApiKey("l7xx8ab0ebd2f81548f586a1838fdbe5bc1b");
        tmapview.setHttpsMode(true);
        TMapTapi tMapTapi = new TMapTapi(this);
        tMapTapi.invokeRoute("T타워", 126.984098f, 37.566385f);
        linearLayoutTmap.addView(tmapview);

        TMapPoint tMapPointStart = new TMapPoint(35.855770, 128.606922);

        stat_lat = intent.getDoubleExtra("statLat", 0);
        stat_long = intent.getDoubleExtra("statLong", 0);
        Log.i("searchRoad", "onCreate: " + stat_lat);
        Log.i("searchRoad", "onCreate: " + stat_long);
        TMapPoint tMapPointEnd = new TMapPoint(stat_lat, stat_long);
        FindCarPathTask findCarPathTask = new FindCarPathTask(getApplicationContext(), tmapview);
        findCarPathTask.execute(tMapPointStart, tMapPointEnd);
        tmapview.setCenterPoint( 128.606922, 35.855770 );

    }
}