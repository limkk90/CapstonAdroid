package org.yju.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FreeCommunityInsert extends AppCompatActivity {

    private EditText et_free_cmt_id_insert, et_free_cmt_content_insert;
    private Button btn_fileGo, btn_free_cmd_insert, btn_free_cmd_no;
    private TextView tv_fileName;
    private String title, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_community_insert);

        et_free_cmt_content_insert = findViewById(R.id.et_free_cmt_id_insert);
        et_free_cmt_content_insert = findViewById(R.id.et_free_cmt_content_insert);
        btn_fileGo = findViewById(R.id.btn_fileGo);
        btn_free_cmd_insert = findViewById(R.id.btn_free_cmd_insert);
        btn_free_cmd_no = findViewById(R.id.btn_free_cmd_no);
        tv_fileName = findViewById(R.id.tv_fileName);



        btn_free_cmd_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = et_free_cmt_id_insert.getText().toString();
                content = et_free_cmt_content_insert.getText().toString();

                // BoardDto 불러오기

            }
        });
    }
}