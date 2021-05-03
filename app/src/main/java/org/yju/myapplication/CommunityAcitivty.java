package org.yju.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CommunityAcitivty extends AppCompatActivity {
    private ImageView iv_menu;
    private DrawerLayout drawerLayout;
    private View drawerView;

    private BottomNavigationView bottomNavigationView; //바텀 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;
    private CommunityFreeFrag cm_free;
    private CommunityNewsFrag cm_news;
    private CommunityNoticeFrag cm_notice;
    private CommunityTipFrag cm_tip;
    private String u_id;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_acitivty);

        Intent intent = getIntent();
        u_id = intent.getStringExtra("u_id");
        Log.i("TAG", "onCreate: 커뮤니티 엑티비티 유저아이디받기" + u_id);

        cm_notice = new CommunityNoticeFrag();
        cm_free = new CommunityFreeFrag();
        cm_tip = new CommunityTipFrag();
        cm_news = new CommunityNewsFrag();

        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_notice:
                        setFrag(0);
                        break;
                    case R.id.action_freeBoard:
                        setFrag(1);
                        Bundle bundle = new Bundle();
                        bundle.putString("u_id", u_id);
                        Log.i("TAG", "onCreate: 번들값찍히냐???" + bundle);
                        cm_free.setArguments(bundle);
                        Log.i("TAG", "onCreate: 번들 값" + bundle);
                        break;
                    case R.id.action_tip:
                        setFrag(2);
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("u_id", u_id);
                        Log.i("TAG", "onCreate: 번들값찍히냐???" + bundle1);
                        cm_free.setArguments(bundle1);
                        Log.i("TAG", "onCreate: 번들 값" + bundle1);
                        break;
                    case R.id.action_news:
                        setFrag(3);
                        break;
                }
                return true;
            }
        });




        setFrag(0);
//        //네비게이션 변수====================================
//        iv_menu = (ImageView)findViewById(R.id.iv_menu);
//        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
//        drawerView = (View)findViewById(R.id.drawer);
//
//        iv_menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.openDrawer(drawerView);
//            }
//        });
    }

    private void setFrag(int n){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction(); //프레그먼트 교체하는 행위

        switch (n){
            case 0 :
                ft.replace(R.id.cm_frame, cm_notice);
                ft.commit();
                break;
            case 1 :
                ft.replace(R.id.cm_frame, cm_free);
                ft.commit();
                break;
            case 2 :
                ft.replace(R.id.cm_frame, cm_tip);
                ft.commit();
                break;
            case 3 :
                ft.replace(R.id.cm_frame, cm_news);
                ft.commit();
                break;
        }
    }
}