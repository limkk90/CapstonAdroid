package org.yju.myapplication.Reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.yju.myapplication.R;

public class ReservationDetail extends AppCompatActivity {

    TextView txt_chg_id, txt_chg_type, txt_chg_method, txt_chg_st;
    Intent intent;
    String statid;
    String chg_id, chg_rsvt, chg_method;
    char chg_st;
    char chg_type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_detail);
        txt_chg_id = findViewById(R.id.txt_chg_id);
        txt_chg_method = findViewById(R.id.txt_chg_method);
        txt_chg_type = findViewById(R.id.txt_chg_type);
        txt_chg_st = findViewById(R.id.txt_chg_st);

        intent= getIntent();
        statid = intent.getStringExtra("statid");
        chg_id = intent.getStringExtra("chg_id");
        chg_type = intent.getCharExtra("chg_type", 'a');
        chg_method = intent.getStringExtra("chg_method");
        chg_st = intent.getCharExtra("chg_st", 'a');
        chg_rsvt = intent.getStringExtra("chg_rsvt");

        Log.i("ReservationDetail", "addItem: " + chg_id + "," + chg_type + "," + chg_method + "," + chg_st + "," + chg_rsvt);


        if(chg_id.equals("1")){
            txt_chg_id.setText(chg_id+"번 충전기");
        }else if(chg_id.equals("2")){
            txt_chg_id.setText(chg_id+"번 충전기");
        }else if(chg_id.equals("3")){
            txt_chg_id.setText(chg_id+"번 충전기");
        }else if(chg_id.equals("4")){
            txt_chg_id.setText(chg_id+"번 충전기");
        }else if(chg_id.equals("5")){
            txt_chg_id.setText(chg_id+"번 충전기");
        }else if(chg_id.equals("6")){
            txt_chg_id.setText(chg_id+"번 충전기");
        }else{
            txt_chg_id.setText("번 충전기");
        }


        if(chg_type == '1'){
            txt_chg_type.setText("완속");
        }else if(chg_type == '2'){
            txt_chg_type.setText("급속");
        }else if(chg_type == '3'){
            txt_chg_type.setText("급속/완속");
        }

        if(chg_method == null){
            txt_chg_method.equals("Unknown");
        }if(chg_method.equals("1")){
            txt_chg_method.setText("B타입(5핀)");
        }if(chg_method.equals("2")){
            txt_chg_method.setText("C타입(5핀)");
        }if(chg_method.equals("3")){
            txt_chg_method.setText("BC타입(5핀)");
        }if(chg_method.equals("4")){
            txt_chg_method.setText("BC타입(7핀)");
        }if(chg_method.equals("5")){
            txt_chg_method.setText("DC차데모");
        }if(chg_method.equals("6")){
            txt_chg_method.setText("DC콤보");
        }if(chg_method.equals("7")){
            txt_chg_method.setText("AC완속");
        }if(chg_method.equals("8")){
            txt_chg_method.setText("AC3상");
        }if(chg_method.equals("9")){
            txt_chg_method.setText("DC차데모 +DC콤보");
        }if(chg_method.equals("10")){
            txt_chg_method.setText("DC차데모 +AC3상");
        }if(chg_method.equals("11")){
            txt_chg_method.setText("DC차데모 +DC콤보+AC3상");
        }

        if(chg_st =='1'){
            txt_chg_st.setText("충전가능");
        }else if(chg_st == '2'){
            txt_chg_st.setText("충전중");
        }else if(chg_st == '3'){
            txt_chg_st.setText("고장/점검");
        }else if(chg_st == '4'){
            txt_chg_st.setText("통신장애");
        }else if(chg_st == '5'){
            txt_chg_st.setText("통신미연결");
        }
    }
}