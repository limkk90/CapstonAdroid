package org.yju.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

//게시글 등록하는 액티비티
public class CommunityInsertAcitivty extends AppCompatActivity {
    String title, content;
    EditText cm_editTitle, cm_editContent;
    Button cm_btnInsert, cm_btnCancle, cm_btn_ImageInsert;
    ImageView cm_imageView;
    RadioButton cm_radioFree, cm_radioTip;
    RadioGroup cm_radioGroup;
    char cat_cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_insert);

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
            }
        });


    }
}