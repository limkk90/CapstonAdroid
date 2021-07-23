package org.yju.myapplication.Reservation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;

import org.yju.myapplication.R;
import org.yju.myapplication.data.Poi;

import java.util.ArrayList;

public class FacilRecoActivity extends AppCompatActivity {
    Intent intent;
    ArrayList<TMapPOIItem> arrayList = new ArrayList<>();
    double stat_lat, stat_Long;

    RecyclerView recyclerView = null;
    FacilRecoAdapter adapter = null;
    LinearLayoutManager linearLayoutManager = null;
    ArrayList<Poi> pList = new ArrayList<>();
    TMapPoint tMapPoint;
    TMapData tMapData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facil_reco2);


        intent = getIntent();
        stat_lat = intent.getDoubleExtra("statLat", 0);
        stat_Long = intent.getDoubleExtra("statLong", 0);
        tMapPoint =new TMapPoint(stat_lat ,stat_Long);
        tMapData = new TMapData();

        //리사이클러뷰 처리
        recyclerView = findViewById(R.id.facil_recyclerview);
        adapter = new FacilRecoAdapter(pList);
        recyclerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        pList.clear();
        getListBoard();

    }

    public void addItem(String name, String address){
        Log.i("FacilRecoaddItem", "onFindAroundNamePOI: "+ name + "a" + address);
        Poi item = new Poi();
        item.setPoiName(name);
        item.setPoiAddress(address);
        pList.add(item);
//        adapter.notifyDataSetChanged();
        Log.i("좆같은:", item.toString());
    }

    public void getListBoard(){
        Log.i("FacilRecoActivity1", "onFindAroundNamePOI: "+ stat_lat + "a" + stat_Long);
        tMapData.findAroundNamePOI(tMapPoint, "편의점, PC방, 노래방, 카페, 목욕탕" , new TMapData.FindAroundNamePOIListenerCallback() {
            @Override
            public void onFindAroundNamePOI(ArrayList<TMapPOIItem> arrayList) {
//                adapter.addList(arrayList);
                Log.i("FacilRecoActivity위", "onFindAroundNamePOI: "+ arrayList);
                for(int i=0; i<arrayList.size(); i++){
                    TMapPOIItem item = arrayList.get(i);
                    Log.i("FacilRecoActivity아래", "POI Name: " + item.getPOIName().toString() + ", " +
                            "Address: " + item.getPOIAddress().replace("null", "")  + ", " +
                            "Point: " + item.getPOIPoint().toString());
                    addItem(item.getPOIName().toString(), item.getPOIAddress().replace("null", ""));
                }
                runOnUiThread(()-> adapter.notifyDataSetChanged());
            }
        });


    }

}