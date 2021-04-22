package org.yju.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_acitivty);




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


}