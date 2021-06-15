package org.yju.myapplication.qna;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.yju.myapplication.R;

public class QnaAcitivty extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    private FragmentManager fm;
    private FragmentTransaction ft;
    private QnaFaqFrag qna_faq;
    private QnaMyQna qna_myQna;
    private QnaOneonOne qna_oneONone;
    private String u_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna_acitivty);

        qna_faq = new QnaFaqFrag();
        qna_myQna = new QnaMyQna();
        qna_oneONone = new QnaOneonOne();
        Intent intent = getIntent();
        u_id = intent.getStringExtra("u_id");
        Log.i("TAG", "onCreate: qna유저아이디 확인" + u_id);

        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.qna_FAQ :
                        setFrag(0);
                        Bundle bundle = new Bundle();
                        bundle.putString("u_id", u_id);
                        Log.i("TAG", "onCreate: 번들값찍히냐???" + bundle);
                        qna_faq.setArguments(bundle);
                        Log.i("TAG", "onCreate: 번들 값" + bundle);
                        break;
                    case R.id.qna_oneQNA :
                        setFrag(1);
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("u_id", u_id);
                        Log.i("TAG", "onCreate: 번들값찍히냐???" + bundle1);
                        qna_oneONone.setArguments(bundle1);
                        Log.i("TAG", "onCreate: 번들 값" + bundle1);
                        break;
                    case R.id.qna_myQNA :
                        setFrag(2);
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("u_id", u_id);
                        Log.i("TAG", "onCreate: 번들값찍히냐???" + bundle2);
                        qna_myQna.setArguments(bundle2);
                        Log.i("TAG", "onCreate: 번들 값" + bundle2);
                        break;
                }
                return true;
            }
        });

    }

    private void setFrag(int n){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction(); //프레그먼트 교체하는 행위

        switch (n){
            case 0 :
                ft.replace(R.id.cm_frame, qna_faq);
                ft.commit();
                break;
            case 1 :
                ft.replace(R.id.cm_frame, qna_oneONone);
                ft.commit();
                break;
            case 2 :
                ft.replace(R.id.cm_frame, qna_myQna);
                ft.commit();
                break;
        }
    }
}