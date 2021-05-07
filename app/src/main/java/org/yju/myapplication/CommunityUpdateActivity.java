package org.yju.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CommunityUpdateActivity extends AppCompatActivity {

    private Button cm_ud_btnInsert, cm_ud_btnCancle;
    private EditText cm_ud_editTitle, cm_ud_editContent;
    private RadioButton cm_ud_radioFree, cm_ud_radioTip;
    private RadioGroup cm_ud_radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_update_acitivy);

        cm_ud_editTitle = findViewById(R.id.cm_ud_editTitle);
        cm_ud_editContent = findViewById(R.id.cm_ud_editContent);
        cm_ud_btnCancle = findViewById(R.id.cm_ud_btnCancle);
        cm_ud_btnInsert = findViewById(R.id.cm_ud_btnInsert);
        cm_ud_radioFree = findViewById(R.id.cm_ud_radioFree);
        cm_ud_radioTip = findViewById(R.id.cm_ud_radioTip);
        cm_ud_radioGroup = findViewById(R.id.cm_ud_radioGroup);
        cm_ud_radioFree.setChecked(true);


    }
}