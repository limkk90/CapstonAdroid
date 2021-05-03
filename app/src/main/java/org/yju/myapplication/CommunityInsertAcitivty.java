package org.yju.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.yju.myapplication.data.Board;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//게시글 등록하는 액티비티
public class CommunityInsertAcitivty extends AppCompatActivity {
    DataService dataService = new DataService();
    Board board;
    String title, content;
    EditText cm_editTitle, cm_editContent;
    Button cm_btnInsert, cm_btnCancle, cm_btn_ImageInsert;
    ImageView cm_imageView;
    RadioButton cm_radioFree, cm_radioTip;
    RadioGroup cm_radioGroup;
    String u_id;
    char cat_cd;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_insert);

        Intent intent = getIntent();
        u_id = intent.getStringExtra("u_id");
        Log.i("TAG", "onClick: 유저아이디뜨내?123123" + u_id);

        //제목
        cm_editTitle = findViewById(R.id.cm_editTitle);
        //내용
        cm_editContent = findViewById(R.id.cm_editContent);
        //등록버튼
        cm_btnInsert = findViewById(R.id.cm_btnInsert);
        //취소버튼
        cm_btnCancle = findViewById(R.id.cm_btnCancle);
        //첨부파일 버튼
        cm_btn_ImageInsert = findViewById(R.id.cm_btn_ImageInsert);
        //라디오 버튼
        cm_radioFree = findViewById(R.id.cm_radioFree);
        //라디오 버튼
        cm_radioTip = findViewById(R.id.cm_radioTip);
        //라디오 그룹
        cm_radioGroup = findViewById(R.id.cm_radioGroup);
        cm_radioFree.setChecked(true);


        //등록버튼 눌렀을 때
        cm_btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cm_radioFree.isChecked())
                    cat_cd = '1';
                if(cm_radioTip.isChecked())
                    cat_cd = '2';
                title = cm_editTitle.getText().toString();
                content = cm_editContent.getText().toString();

                Log.i("boardI", "onClick: "+title);
                Log.i("boardI", "onClick: "+content);
                Log.i("boardI", "onClick: "+cat_cd);
                board = new Board();
                board.setCat_cd(cat_cd);
                board.setB_title(title);
                board.setB_content(content);
                board.setU_id(u_id);
                Log.i("TAG", "onClick: 유저아이디뜨내?" + u_id);

                dataService.insert.boardInsert(board).enqueue(new Callback(){
                    @Override
                    public void onResponse(Call call, Response response) {
                        Log.i("CmInsert:", "성공");
                        finish();
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Log.i("CmInsert", "실패");
                    }
                });


            }
        });

        cm_btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}