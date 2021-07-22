package org.yju.myapplication.Reservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import org.yju.myapplication.Community.CommunityAdapter;
import org.yju.myapplication.DataService;
import org.yju.myapplication.R;
import org.yju.myapplication.data.Rsvt;

import java.util.ArrayList;

public class CheckTodayRsvt extends AppCompatActivity {
    DataService dataService = new DataService();
    RecyclerView recyclerView=null;
    ArrayList<Rsvt> rsvtList = new ArrayList<>();
    CheckTodayRsvtAdapter adapter = null;
    LinearLayoutManager linearLayoutManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_today_rsvt2);

        //리사이클러뷰 처리;
        recyclerView = findViewById(R.id.check_recyclerVIew);
        adapter = new CheckTodayRsvtAdapter(rsvtList);
        recyclerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        adapter.notifyDataSetChanged();


    }
}