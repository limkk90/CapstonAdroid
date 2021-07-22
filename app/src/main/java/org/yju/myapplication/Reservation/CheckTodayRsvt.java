package org.yju.myapplication.Reservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import org.yju.myapplication.DataService;
import org.yju.myapplication.R;
import org.yju.myapplication.data.Rsvt;

import java.util.ArrayList;

public class CheckTodayRsvt extends AppCompatActivity {
    DataService dataService = new DataService();
    RecyclerView recyclerView=null;
    ArrayList<Rsvt> rsvtList = new ArrayList<>();
    CheckTodayRsvtAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_today_rsvt2);


    }
}