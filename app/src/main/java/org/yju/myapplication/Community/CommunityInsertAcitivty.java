package org.yju.myapplication.Community;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.yju.myapplication.DataService;
import org.yju.myapplication.R;
import org.yju.myapplication.data.Board;

import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//게시글 등록하는 액티비티
public class CommunityInsertAcitivty extends AppCompatActivity {
    Intent intent;
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
    Bitmap bitmap;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_insert);

        intent = getIntent();
        u_id = intent.getStringExtra("u_id");
        Log.i("TAG", "onClick: 유저아이디뜨내?123123" + u_id);

        //제목
        cm_editTitle = findViewById(R.id.cm_ud_editTitle);
        //내용
        cm_editContent = findViewById(R.id.cm_ud_editContent);
        //등록버튼
        cm_btnInsert = findViewById(R.id.cm_ud_btnInsert);
        //취소버튼
        cm_btnCancle = findViewById(R.id.cm_ud_btnCancle);
        //첨부파일 버튼
        cm_btn_ImageInsert = findViewById(R.id.cm_btn_ImageInsert);
        cm_imageView = findViewById(R.id.cm_imageView);
        //라디오 버튼
        cm_radioFree = findViewById(R.id.cm_ud_radioFree);
        //라디오 버튼
        cm_radioTip = findViewById(R.id.cm_ud_radioTip);
        //라디오 그룹
        cm_radioGroup = findViewById(R.id.cm_ud_radioGroup);
        cm_radioFree.setChecked(true);

        cm_btn_ImageInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);

            }
        });

            //등록버튼 눌렀을 때
            cm_btnInsert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cm_radioFree.isChecked())
                        cat_cd = '1';
                    if (cm_radioTip.isChecked())
                        cat_cd = '2';
                    title = cm_editTitle.getText().toString();
                    content = cm_editContent.getText().toString();

                    board = new Board();
                    board.setCat_cd(cat_cd);
                    board.setB_title(title);
                    board.setB_content(content);
                    board.setU_id(u_id);
                    Log.i("TAG", "onClick: 유저아이디뜨내?" + u_id);

                    dataService.boardApi.boardInsert(board).enqueue(new Callback() {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            try{
                InputStream in = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(in);
                in.close();
                cm_imageView.setImageBitmap(bitmap);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}