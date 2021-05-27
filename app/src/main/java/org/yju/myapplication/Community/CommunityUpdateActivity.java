package org.yju.myapplication.Community;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.yju.myapplication.DataService;
import org.yju.myapplication.R;
import org.yju.myapplication.data.Board;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityUpdateActivity extends AppCompatActivity {

    private Button cm_ud_btnInsert, cm_ud_btnCancle;
    private EditText cm_ud_editTitle, cm_ud_editContent;
    private RadioButton cm_ud_radioFree, cm_ud_radioTip;
    private RadioGroup cm_ud_radioGroup;
    private char cat_cd;
    private String title, content, b_no, u_id;
    Board board;
    private Intent intent;
    DataService dataService = new DataService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_update_acitivy);

        intent = getIntent();
        b_no = intent.getStringExtra("b_no");
        u_id = intent.getStringExtra("u_id");
        Log.i("TAG", "onCreate: 게시글번호 수정으로넘어오냐??" + b_no);
        Log.i("TAG", "onCreate: 유저아이디 수정으로넘어오냐??" + u_id);

        cm_ud_editTitle = findViewById(R.id.cm_ud_editTitle);
        cm_ud_editContent = findViewById(R.id.cm_ud_editContent);
        cm_ud_btnCancle = findViewById(R.id.cm_ud_btnCancle);
        cm_ud_btnInsert = findViewById(R.id.cm_ud_btnInsert);
        cm_ud_radioFree = findViewById(R.id.cm_ud_radioFree);
        cm_ud_radioTip = findViewById(R.id.cm_ud_radioTip);
        cm_ud_radioGroup = findViewById(R.id.cm_ud_radioGroup);
        cm_ud_radioFree.setChecked(true);

        //수정버튼 클릭시
        cm_ud_btnInsert.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(cm_ud_radioFree.isChecked())
                    cat_cd = '1';
                if(cm_ud_radioTip.isChecked())
                    cat_cd = '2';
                title = cm_ud_editTitle.getText().toString();
                content = cm_ud_editContent.getText().toString();
                board = new Board();
                board.setCat_cd(cat_cd);
                board.setB_title(title);
                board.setB_content(content);
                board.setB_no(b_no);

                dataService.boardApi.updateBoard(board).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.i("tag","성공");
                        Intent intent1 = new Intent();
                        intent1.putExtra("u_id", u_id);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.i("tag","실패");
                    }
                });
            }

        });

        // 취소버튼 클릭시시
        cm_ud_btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("u_id", u_id);
                finish();
            }
        });
    }}