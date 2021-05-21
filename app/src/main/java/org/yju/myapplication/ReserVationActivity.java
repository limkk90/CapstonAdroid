package org.yju.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import lombok.val;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class ReserVationActivity extends AppCompatActivity {
    Spinner spinnerStartH, spinnerStartM, spinnerEndH, spinnerEndM;
    String hour[] = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17",
                     "18", "19", "20", "21", "22", "23"};
    String minute[] = {"00", "10", "20", "30", "40", "50"};

    String hourEnd[] = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17",
            "18", "19", "20", "21", "22", "23"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reser_vation);

        spinnerStartH = findViewById(R.id.startSpinnerKara);
        spinnerStartM = findViewById(R.id.startSpinnerMade);
        spinnerEndH = findViewById(R.id.endSpinnerKara);
        spinnerEndM = findViewById(R.id.endSpinnerMade);

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
}