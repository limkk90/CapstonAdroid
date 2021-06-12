package org.yju.myapplication.Reservation;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.yju.myapplication.DataService;
import org.yju.myapplication.R;
import org.yju.myapplication.data.Charger;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReserVationActivity extends AppCompatActivity {

    Button res_btn, res_cancle, res_facil, res_findRoad;
    Intent intent;
    Spinner spinnerStartH, spinnerStartM, spinnerEndH, spinnerEndM;
    String hour[] = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17",
                     "18", "19", "20", "21", "22", "23"};
    String minute[] = {"00", "10", "20", "30", "40", "50"};
    TextView txt_station_name, txt_station_addr;
    String stat_id, stat_nm, stat_addr;
    double stat_lat, stat_Long;

    RecyclerView recyclerView = null;
    ReservationAdapter adapter = null;
    ArrayList<Charger> cList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager = null;
    DataService dataService = new DataService();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reser_vation);
        res_btn = findViewById(R.id.res_reserBtn);
        res_facil = findViewById(R.id.res_cancleBtn);
        res_findRoad = findViewById(R.id.res_searchRoad);

        intent = getIntent();
         stat_id = intent.getStringExtra("statId").substring(10);
         stat_nm = intent.getStringExtra("statNm");
         stat_addr = intent.getStringExtra("statAddr");
        stat_lat = intent.getDoubleExtra("statLat", 0);
        stat_Long = intent.getDoubleExtra("statLong", 0);

        Log.i("Reversation1", "onCreate: " + stat_id);
        Log.i("Reversation1", "onCreate: " + stat_nm);
        Log.i("Reversation1", "onCreate: " + stat_addr);
        Log.i("Reversation1", "onCreate: " + stat_lat);
        Log.i("Reversation1", "onCreate: " + stat_Long);


        txt_station_name = findViewById(R.id.txt_stationName);
        txt_station_addr = findViewById(R.id.txt_stationAddr);
        spinnerStartH = findViewById(R.id.startSpinnerKara);
        spinnerStartM = findViewById(R.id.startSpinnerMade);
        spinnerEndH = findViewById(R.id.endSpinnerKara);
        spinnerEndM = findViewById(R.id.endSpinnerMade);

        txt_station_name.setText(stat_nm);
        txt_station_addr.setText(stat_addr);
        getListCharger();
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

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerStartM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), "선택된 아이템:"+ spinnerStartM.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerEndH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), "선택된 아이템:"+ spinnerEndH.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerEndM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), "선택된 아이템:"+ spinnerEndM.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //길 찾기 버튼 클릭 시에
        res_findRoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReserVationActivity.this, SearchRoad.class);
                intent.putExtra("statLat", stat_lat);
                intent.putExtra("statLong", stat_Long);
                startActivity(intent);
            }
        });


        //=============리사이클러뷰 처리
        recyclerView= findViewById(R.id.reservation_recyclerView);

        adapter = new ReservationAdapter(cList);
        recyclerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        getListCharger();
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(new ReservationAdapter.OnItemCLickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                v.setBackgroundColor(Color.BLACK);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        cList.clear();
        getListCharger();
        adapter.notifyDataSetChanged();
    }

    public void addItem(String chargeuse){
        Charger item = new Charger();
        item.setChg_rsvt(chargeuse);
        cList.add(item);
        adapter.notifyDataSetChanged();

    }

    public void getListCharger(){
        dataService.reservationApi.getCharger(stat_id).enqueue(new Callback<List<Charger>>() {
            @Override
            public void onResponse(Call<List<Charger>> call, Response<List<Charger>> response) {
                List<Charger> chargers = response.body();
                Log.i("getListCharger", "onResponse: "+chargers);
                for(int i=0; i<chargers.size(); i++){
                    addItem(chargers.get(i).getChg_rsvt());
                }
            }

            @Override
            public void onFailure(Call<List<Charger>> call, Throwable t) {
                Log.i("getListCharger", "onFailure: 실패 ㅋㅋ");
            }
        });

        }
}