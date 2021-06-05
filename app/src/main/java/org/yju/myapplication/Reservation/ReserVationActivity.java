package org.yju.myapplication.Reservation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.yju.myapplication.DataService;
import org.yju.myapplication.R;
import org.yju.myapplication.data.Charger;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReserVationActivity extends AppCompatActivity {
    DataService dataService = new DataService();
    Intent intent;
    Spinner spinnerStartH, spinnerStartM, spinnerEndH, spinnerEndM;
    String hour[] = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17",
                     "18", "19", "20", "21", "22", "23"};
    
    String minute[] = {"00", "10", "20", "30", "40", "50"};
    TextView txt_station_name, txt_station_addr;
    String stat_id, stat_nm, stat_addr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reser_vation);
        intent = getIntent();
        Log.i("Reversation1", "onCreate: " + intent.getStringExtra("statId").substring(10));
         stat_id = intent.getStringExtra("statId").substring(10);
         stat_nm = intent.getStringExtra("statNm");
         stat_addr = intent.getStringExtra("statAddr");
        Log.i("Reversation1", "onCreate: " + stat_id);
        Log.i("Reversation1", "onCreate: " + stat_nm);
        Log.i("Reversation1", "onCreate: " + stat_addr);


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
                Toast.makeText(getApplicationContext(), "선택된 아이템:"+ spinnerStartH.getItemAtPosition(position), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerStartM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "선택된 아이템:"+ spinnerStartM.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerEndH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "선택된 아이템:"+ spinnerEndH.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerEndM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "선택된 아이템:"+ spinnerEndM.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void getListCharger(){
        dataService.reservationApi.getCharger(stat_id).enqueue(new Callback<List<Charger>>() {
            @Override
            public void onResponse(Call<List<Charger>> call, Response<List<Charger>> response) {
                List<Charger> chargers = response.body();
                Log.i("getListCharger", "onResponse: "+chargers);
            }

            @Override
            public void onFailure(Call<List<Charger>> call, Throwable t) {

            }
        });

        }
}