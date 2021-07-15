package org.yju.myapplication.Reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.yju.myapplication.DataService;
import org.yju.myapplication.LoginActivity;
import org.yju.myapplication.R;
import org.yju.myapplication.data.Rsvt;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class ReservationDetail extends AppCompatActivity {
    SharedPreferences preferences;
    Rsvt rsvt;
    String strStartTime, strendTime;
    DataService dataService;
    TextView txt_chg_id, txt_chg_type, txt_chg_method, txt_chg_st;
    Intent intent;
    String stat_id;
    String chg_id, chg_rsvt, chg_method;
    char chg_st;
    char chg_type;
    Button btn_reservation, btn_reserCancle;
    Spinner spinnerStartH, spinnerStartM, spinnerEndH, spinnerEndM;
    String hour[] = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17",
            "18", "19", "20", "21", "22", "23"};
    String minute[] = {"00", "10", "20", "30", "40", "50"};
    String strSpinStartH, strSpinStartM, strSpinEndH, strSpinEndM;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_detail);
        txt_chg_id = findViewById(R.id.txt_chg_id);
        txt_chg_method = findViewById(R.id.txt_chg_method);
        txt_chg_type = findViewById(R.id.txt_chg_type);
        txt_chg_st = findViewById(R.id.txt_chg_st);
        btn_reservation = findViewById(R.id.res_reserBtn);
        btn_reserCancle = findViewById(R.id.res_reserCancle);

        spinnerStartH = findViewById(R.id.startSpinnerKara);
        spinnerStartM = findViewById(R.id.startSpinnerMade);
        spinnerEndH = findViewById(R.id.endSpinnerKara);
        spinnerEndM = findViewById(R.id.endSpinnerMade);



        intent= getIntent();
        stat_id = intent.getStringExtra("stat_id");
        chg_id = intent.getStringExtra("chg_id");
        chg_type = intent.getCharExtra("chg_type", 'a');
        chg_method = intent.getStringExtra("chg_method");
        chg_st = intent.getCharExtra("chg_st", 'a');
        chg_rsvt = intent.getStringExtra("chg_rsvt");
        user_id = intent.getStringExtra("user_id");

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
            txt_chg_method.setText("Unknown");
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

        ArrayAdapter<String> startHouradapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hour);
        startHouradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStartH.setAdapter(startHouradapter);
        spinnerEndH.setAdapter(startHouradapter);


        ArrayAdapter<String> startMinAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, minute);
        startMinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStartM.setAdapter(startMinAdapter);
        spinnerEndM.setAdapter(startMinAdapter);

        spinnerStartH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), "선택된 아이템:"+ spinnerStartH.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                strSpinStartH = (String) spinnerStartH.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerStartM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "선택된 아이템:"+ spinnerStartM.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                strSpinStartM = (String) spinnerStartM.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerEndH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), "선택된 아이템:"+ spinnerEndH.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                strSpinEndH = (String) spinnerEndH.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerEndM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), "선택된 아이템:"+ spinnerEndM.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                strSpinEndM = (String) spinnerEndM.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rsvt = new Rsvt();
                if(user_id == null){
                    Toast.makeText(getApplicationContext(), "로그인을 해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                //충전기 아이디
                Log.i("ReservationDetail", "onCreate: " + chg_id);
                //충전소 아이디
                Log.i("ReservationDetail", "onCreate: " + stat_id);
                //시작시간
                //=======날짜 구하기=====
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String year = simpleDateFormat.format(date);
                //=======날짜구하기======

                Log.i("ReservationDetail", "onCreate: " + year);
                strStartTime = year + " " +strSpinStartH + ":" + strSpinStartM;
                strendTime  = year + " " +strSpinEndH + ":" + strSpinEndM;


//                Log.i("ReservationDetail", "onCreate: " + strStart);
//                //스피너값
//                Log.i("ReservationDetail", "onCreate: " + strSpinStartH);
//                Log.i("ReservationDetail", "onCreate: " + strSpinStartM);
//                Log.i("ReservationDetail", "onCreate: " + strSpinEndH);
//                Log.i("ReservationDetail", "onCreate: " + strSpinEndM);
//                //스피너값
                Log.i("ReservationDetail", "onCreate: " + strStartTime);
                Log.i("ReservationDetail", "onCreate: " + strendTime);
                Log.i("ReservationDetail", "onCreate: " + user_id);
                rsvt.setChg_id(chg_id);
                rsvt.setStat_id(stat_id);
                rsvt.setRsvt_start(strStartTime);
                rsvt.setRsvt_end(strendTime);

//                dataService.reservationApi.insertRsvt()
                //끝나는시간
            }
        });
    }
}