package org.yju.myapplication.qna;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna_acitivty);

        qna_faq = new QnaFaqFrag();
        qna_myQna = new QnaMyQna();
        qna_oneONone = new QnaOneonOne();

        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.qna_FAQ :
                        setFrag(0);
                        break;
                    case R.id.qna_oneQNA :
                        setFrag(1);
                        break;
                    case R.id.qna_myQNA :
                        setFrag(2);
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