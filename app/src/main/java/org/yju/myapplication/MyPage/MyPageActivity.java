package org.yju.myapplication.MyPage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.yju.myapplication.R;

public class MyPageActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private MyPageUseInfoFrag mp_useInfo;
    private MyPageUserInfoFrag mp_userInfo;
    private MyPageCardFrag mp_card;
    private MyPageSetFrag mp_set;
    private String u_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        Intent intent = getIntent();
        u_id = intent.getStringExtra("u_id");
        Log.i("TAG", "onCreate: 마이페이지 엑티비티 유저아이디받기" + u_id);


        mp_userInfo = new MyPageUserInfoFrag();
        mp_useInfo = new MyPageUseInfoFrag();
        mp_card = new MyPageCardFrag();
        mp_set = new MyPageSetFrag();

        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mp_userInfo:
                        setFrag(0);
                        Bundle bundle = new Bundle();
                        bundle.putString("u_id", u_id);
                        mp_userInfo.setArguments(bundle);
                        Log.i("TAG", "onCreate: u_id 확인확인" + u_id);
                        break;
                    case R.id.mp_useInfo:
                        setFrag(1);
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("u_id", u_id);
                        mp_useInfo.setArguments(bundle2);
                        break;
                    case R.id.mp_card:
                        setFrag(2);
                        Bundle bundle3 = new Bundle();
                        bundle3.putString("u_id", u_id);
                        mp_card.setArguments(bundle3);
                        break;
                    case R.id.mp_set:
                        setFrag(3);
                        Bundle bundle4 = new Bundle();
                        bundle4.putString("u_id", u_id);
                        mp_set.setArguments(bundle4);
                        break;
                }
                return true;
            }
        });
        setFrag(0);

    }
    private void setFrag(int n){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction(); //프레그먼트 교체하는 행위

        switch (n){
            case 0 :
                ft.replace(R.id.myPg_frame, mp_userInfo);
                ft.commit();
                break;
            case 1 :
                ft.replace(R.id.myPg_frame, mp_useInfo);
                ft.commit();
                break;
            case 2 :
                ft.replace(R.id.myPg_frame, mp_card);
                ft.commit();
                break;
            case 3 :
                ft.replace(R.id.myPg_frame, mp_set);
                ft.commit();
                break;
        }
    }
}