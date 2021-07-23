package org.yju.myapplication.Reservation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import org.yju.myapplication.DataService;
import org.yju.myapplication.R;
import org.yju.myapplication.data.Question;
import org.yju.myapplication.data.Rsvt;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckTodayRsvt extends AppCompatActivity {
    DataService dataService = new DataService();
    RecyclerView recyclerView=null;
    CheckTodayRsvtAdapter adapter = null;
    LinearLayoutManager linearLayoutManager = null;
    ArrayList<Rsvt> rList = new ArrayList<>();
    String stat_id, chg_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_today_rsvt2);
        Intent intent = getIntent(); //getIntent해줘야됨
        stat_id = intent.getStringExtra("stat_id");
        chg_id = intent.getStringExtra("chg_id");
        Log.i("CheckTodayRsvt", "onResponse충전소: " + stat_id);
        Log.i("CheckTodayRsvt", "onResponse충전기: " + chg_id);

        //리사이클러뷰 처리;
        recyclerView = findViewById(R.id.check_recyclerview);
        adapter = new CheckTodayRsvtAdapter(rList);
        recyclerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        rList.clear();
        getListBoard();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addItem(String start, String end, String u_id) {
        Rsvt item = new Rsvt();
        item.setRsvt_start(start);
        item.setRsvt_end(end);
        item.setU_id(u_id);
        rList.add(item);
        adapter.notifyDataSetChanged();
    }

    public void getListBoard(){
        dataService.reservationApi.getTodaysRsvt(chg_id, stat_id).enqueue(new Callback<ArrayList<Rsvt>>() {
            @Override
            public void onResponse(Call<ArrayList<Rsvt>> call, Response<ArrayList<Rsvt>> response) {
                ArrayList<Rsvt> body = response.body();
                for(int i=0; i<body.size(); i++){
                    Log.i("CheckTodayRsvt", "onResponse스: " + body.get(i).getRsvt_start());
                    Log.i("CheckTodayRsvt", "onResponse엔: " + body.get(i).getRsvt_end());
                    Log.i("CheckTodayRsvt", "onResponse유: " + body.get(i).getU_id());
                    addItem(body.get(i).getRsvt_start(), body.get(i).getRsvt_end(), body.get(i).getU_id());
                }
//                addItem(null, null, null);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ArrayList<Rsvt>> call, Throwable t) {
                Log.i("CheckTodayRsvt", "onResponse실패: ");
                t.printStackTrace();
            }

        });
    }
}