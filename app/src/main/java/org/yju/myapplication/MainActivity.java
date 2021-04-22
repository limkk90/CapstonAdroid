package org.yju.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapView;

import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import org.yju.myapplication.databinding.ActivityActionbarBinding;
import org.yju.myapplication.databinding.ActivityDrawerBinding;
import org.yju.myapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView txtMyPage,txtCommunity ,txtQna, txtIntro, txtFeeInfo, txtFacilReco, txtCard;
    Intent intent;
    ActivityMainBinding mainBinding;
    ActivityDrawerBinding drawerBinding;
    ActivityActionbarBinding actionbarBinding;
    TMapData tmapdata;
    public String strData;
    private Toolbar toolbar;
    private ImageView iv_menu;
    private DrawerLayout drawerLayout;
    private View drawerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding =ActivityMainBinding.inflate(getLayoutInflater());
        drawerBinding=ActivityDrawerBinding.inflate(getLayoutInflater());
        actionbarBinding=ActivityActionbarBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        //네비게이션 변수====================================
        iv_menu = (ImageView)findViewById(R.id.iv_menu);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);
        //==================================================
        //네비게이션 텍스트뷰 변수=============================
        txtMyPage = (TextView)findViewById(R.id.txt_myPage); //네비게이션 마이페이지 텍스트
        txtCommunity = (TextView)findViewById(R.id.txt_community); // 네비게이션 커뮤니티 텍스트
        txtQna = (TextView)findViewById(R.id.txt_qna); //네비게이션 QNA텍스트
        txtIntro = (TextView)findViewById(R.id.txt_intro); //네비게이션 소개 텍스트
        txtFeeInfo = (TextView)findViewById(R.id.txt_feeInfo);
        txtCard = (TextView)findViewById(R.id.txt_card);
        txtFacilReco = (TextView)findViewById(R.id.txt_facilReco);
        //===================================================


//        drawerBinding.txtMyPage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                intent = new Intent(MainActivity.this, MyPageActivity.class);
//                startActivity(intent);
//            }
//        });
        txtMyPage.setOnClickListener(new View.OnClickListener() { //마이페이지 이동
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, MyPageActivity.class);
             startActivity(intent);
            }
        });
        txtCommunity.setOnClickListener(new View.OnClickListener() { //커뮤니티 이동
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, CommunityAcitivty.class);
                startActivity(intent);
            }
        });
        txtQna.setOnClickListener(new View.OnClickListener() { //문의하기 이동
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, QnaAcitivty.class);
                startActivity(intent);
            }
        });
        txtIntro.setOnClickListener(new View.OnClickListener(){ //소개페이지 이동
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, IntroActivity.class);
                startActivity(intent);
            }
        });
        txtFeeInfo.setOnClickListener(new View.OnClickListener() { //요금정보 이동
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, FeeInfoAcitivty.class);
                startActivity(intent);
            }
        });
        txtFacilReco.setOnClickListener(new View.OnClickListener() { //주변시설 추천 이동
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, FacilRecoActivity.class);
                startActivity(intent);
            }
        });
        txtCard.setOnClickListener(new View.OnClickListener() { //카드 페이지 이동
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, CardActivity.class);
                startActivity(intent);
            }
        });

        drawerBinding.txtLogin.setOnClickListener(v ->{ //로그인 이동
            //나중에 합침
        });
        drawerBinding.txtJoin.setOnClickListener(v ->{ //회원가입 이동
           //나중에 합침
        });
        LinearLayout linearLayoutTmap = (LinearLayout)findViewById(R.id.tmap);
        TMapView tmapview = new TMapView(this);
        tmapview.setSKTMapApiKey("l7xx8ab0ebd2f81548f586a1838fdbe5bc1b");
        tmapview.setHttpsMode(true);
        linearLayoutTmap.addView(tmapview);

        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerView);
            }
        });
    }



}