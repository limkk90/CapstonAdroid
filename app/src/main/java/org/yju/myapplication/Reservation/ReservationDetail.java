package org.yju.myapplication.Reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.yju.myapplication.R;

public class ReservationDetail extends AppCompatActivity {

    Intent intent;
    String statid;
    String chg_id, chg_rsvt, chg_method;
    char chg_st;
    char chg_type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_detail);
        intent= getIntent();
        statid = intent.getStringExtra("statid");
        chg_id = intent.getStringExtra("chg_id");
        chg_type = intent.getCharExtra("chg_type", 'a');
        chg_method = intent.getStringExtra("chg_method");
        chg_st = intent.getCharExtra("chg_st", 'a');
        chg_rsvt = intent.getStringExtra("chg_rsvt");

        Log.i("ReservationDetail", "addItem: " + chg_id + "," + chg_type + "," + chg_method + "," + chg_st + "," + chg_rsvt);

    }
}