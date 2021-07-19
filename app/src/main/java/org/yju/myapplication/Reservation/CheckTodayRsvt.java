package org.yju.myapplication.Reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.yju.myapplication.DataService;
import org.yju.myapplication.R;

public class CheckTodayRsvt extends AppCompatActivity {
    DataService dataService = new DataService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_today_rsvt2);


    }
}