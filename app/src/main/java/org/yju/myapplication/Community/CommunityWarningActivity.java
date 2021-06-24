package org.yju.myapplication.Community;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.yju.myapplication.DataService;
import org.yju.myapplication.R;
import org.yju.myapplication.data.Warning;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityWarningActivity extends AppCompatActivity {
    Intent intent;
    DataService dataService = new DataService();
    private EditText cm_ud_editContent;
    private RadioGroup cm_radioGroup;
    private RadioButton cm_w_a, cm_w_b, cm_w_c, cm_w_d, cm_w_e;
    private Button cm_btnCancle, cm_btnInsert;
    private String u_id, content;
    private char w_cat, w_reason;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_warning);

        cm_ud_editContent = findViewById(R.id.cm_ud_editContent);
        cm_radioGroup = findViewById(R.id.cm_radioGroup);
        cm_w_a = findViewById(R.id.cm_w_a);
        cm_w_b = findViewById(R.id.cm_w_b);
        cm_w_c = findViewById(R.id.cm_w_c);
        cm_w_d = findViewById(R.id.cm_w_d);
        cm_w_e = findViewById(R.id.cm_w_e);
        cm_btnCancle = findViewById(R.id.cm_btnCancle);
        cm_btnInsert = findViewById(R.id.cm_btnInsert);

        intent = getIntent();
        u_id = intent.getStringExtra("u_id");


        // 등록 버튼 눌렀을 시
        cm_btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cm_w_a.isChecked())
                    w_reason = '0';
                else if (cm_w_b.isChecked())
                    w_reason = '1';
                else if (cm_w_c.isChecked())
                    w_reason = '2';
                else if (cm_w_d.isChecked())
                    w_reason = '3';
                else if (cm_w_e.isChecked())
                    w_reason = '9';

                w_cat = '1';

                content = cm_ud_editContent.getText().toString();

                Warning warning = new Warning();
                warning.setU_id(u_id);
                warning.setW_cat(w_cat);
                warning.setW_content(content);
                warning.setW_reason(w_reason);

                dataService.boardApi.warning(warning).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.i("TAG", "onResponse: 성공");
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

            }
        });


        // 취소 버튼 눌렀을시
        cm_btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}