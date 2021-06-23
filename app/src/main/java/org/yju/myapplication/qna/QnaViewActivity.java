package org.yju.myapplication.qna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.yju.myapplication.DataService;
import org.yju.myapplication.R;
import org.yju.myapplication.data.Question;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QnaViewActivity extends AppCompatActivity {

    private TextView tv_q_title, tv_q_content;
    private Button tv_q_update, tv_q_delete, btn_ans;
    private EditText et_q_title;
    DataService dataService = new DataService();
    private String title, content, a_content, q_dtt, q_u_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna_view);

        tv_q_title = findViewById(R.id.tv_q_title);
        tv_q_content = findViewById(R.id.tv_q_content);
        tv_q_update = findViewById(R.id.tv_q_update);
        tv_q_delete = findViewById(R.id.tv_q_delete);
        btn_ans = findViewById(R.id.btn_ans);
        et_q_title = findViewById(R.id.et_q_title);

        Intent intent = getIntent();
        q_dtt = intent.getExtras().getString("q_dtt");
        q_u_id = intent.getExtras().getString("u_id");
        Log.i("TAG", "onCreate: q_dtt 확인" + q_dtt);
        Log.i("TAG", "onCreate: q_u_id 확인" + q_u_id);

        dataService.qnaApi.gQuest(q_dtt).enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                Question question = new Question();
                question = question.ObjToQuestion(response.body().get("question"));

//                Object o = response.body().get("answer");
//                ObjectMapper mapper = new ObjectMapper();
//                ArrayList arrayList = mapper.convertValue(o, ArrayList.class);
                Log.i("TAG", "onResponse: 글조회 확인" + question);

                title = question.getQ_title();
                content = question.getQ_content();
                tv_q_title.setText(title);
                tv_q_content.setText(content);
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {

            }
        });




    }
}