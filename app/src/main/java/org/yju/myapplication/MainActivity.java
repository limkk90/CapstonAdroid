package org.yju.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapMarkerItem2;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import org.yju.myapplication.Community.CommunityAcitivty;
import org.yju.myapplication.MyPage.MyPageActivity;
import org.yju.myapplication.Reservation.ReserVationActivity;
import org.yju.myapplication.data.Marker;
import org.yju.myapplication.databinding.ActivityActionbarBinding;
import org.yju.myapplication.databinding.ActivityDrawerBinding;
import org.yju.myapplication.databinding.ActivityMainBinding;
import org.yju.myapplication.qna.QnaAcitivty;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{
    TMapView tmapview;
    String id;
    //TMapMarkerItem markerItem1;
    ArrayList<Marker> mapApi;
    DataService dataService  = new DataService();
    Context context;
    TextView txtMyPage,txtCommunity ,txtQna, txtIntro, txtFeeInfo, txtFacilReco, txtCard, txtLogin, txtJoin;
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
    private String u_id;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding =ActivityMainBinding.inflate(getLayoutInflater());
        drawerBinding=ActivityDrawerBinding.inflate(getLayoutInflater());
        actionbarBinding=ActivityActionbarBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
//        AlertDialog.Builder myAlert = new AlertDialog.Builder(MainActivity.this);
//        myAlert.setTitle("Alert");
//        myAlert.setMessage("Click OK to continue, or Cancel to stop");
//        myAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(MainActivity.this, "???????????????", Toast.LENGTH_SHORT).show();
//            }
//        });
//        myAlert.create().show();




        LinearLayout linearLayoutTmap = (LinearLayout)findViewById(R.id.tmap);
        tmapview = new TMapView(this);
        tmapview.setSKTMapApiKey("l7xx8ab0ebd2f81548f586a1838fdbe5bc1b");
        tmapview.setHttpsMode(true);
        linearLayoutTmap.addView(tmapview);

        TMapPoint tMapPoint = new TMapPoint(35.855770, 128.620935);
        TMapPoint tMapPointStart = new TMapPoint(35.855770, 128.606922);
        tmapview.setCenterPoint( 128.620935, 35.896463 ); //?????? ?????? ??? ???????????? ????????????
////        ===============?????? ??????======================
        dataService.select.getMarker().enqueue(new Callback<ArrayList<Marker>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<ArrayList<Marker>> call, Response<ArrayList<Marker>> response) {
                ArrayList<Marker> body = response.body();
                //?????? body.size()
                for(int i=0; i<100; i++){
//                    tMapMarkerItem2  = new TMapMarkerItem2();
                    TMapMarkerItem markerItem = new TMapMarkerItem();
                    id = body.get(i).getStat_id();
                    Log.i("Reeservation", "onResponse: " + id);
                    String stat_nm = body.get(i).getStat_nm();
                    String stat_addr = body.get(i).getStat_addr();
                    String stat_lng = body.get(i).getStat_lng();
                    String stat_lat = body.get(i).getStat_lat();
                    double stat_lng_d = Double.parseDouble(stat_lng);
                    double stat_lat_d = Double.parseDouble(stat_lat);

                    Log.i("Reeservation", "onResponse: " + stat_nm);
                    Log.i("Reeservation", "onResponse: " + stat_addr);


                    TMapPoint tMapPoint1 = new TMapPoint(stat_lat_d, stat_lng_d); // SKT??????
                    // ?????? ?????????
                    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pin_r_m_a);


                    markerItem.setID(id);
                    markerItem.setIcon(bitmap); // ?????? ????????? ??????
                    markerItem.setPosition(0.5f, 1.0f); // ????????? ???????????? ??????, ???????????? ??????
                    markerItem.setTMapPoint( tMapPoint1 ); // ????????? ?????? ??????
                    markerItem.setName(stat_addr); // ????????? ????????? ??????
                    markerItem.setCanShowCallout(true); //????????? ????????????


                    Log.i("Reeservation1", "onResponse: " + markerItem.getID());
                    if(markerItem.getCanShowCallout()){
                        markerItem.setCalloutTitle(stat_nm); //????????? ?????? ??? ?????? ??????
                        Bitmap bitmapImage = createmarkerIcon(R.drawable.reservation);
                        Bitmap roadImage = createmarkerLeftImage(R.drawable.road);
                        markerItem.setCalloutRightButtonImage(bitmapImage);
                        markerItem.setCalloutLeftImage(roadImage);
                    }
                    tmapview.setOnCalloutRightButtonClickListener(new TMapView.OnCalloutRightButtonClickCallback() {
                        @Override
                        public void onCalloutRightButton(TMapMarkerItem tMapMarkerItem) {
                            Log.i("onCall", "onCalloutRightButton: "+tMapMarkerItem.getID());
                            Log.i("onCall", "onCalloutRightButton: "+tMapMarkerItem.getTMapPoint());
                            Log.i("onCall", "onCalloutRightButton: "+tMapMarkerItem.getTMapPoint().getLatitude());
                            Log.i("onCall", "onCalloutRightButton: "+tMapMarkerItem.getTMapPoint().getLongitude());
                            intent = new Intent(MainActivity.this, ReserVationActivity.class);
                            intent.putExtra("statId", tMapMarkerItem.getID());
                            intent.putExtra("statNm", tMapMarkerItem.getCalloutTitle());
                            intent.putExtra("statAddr", tMapMarkerItem.getName());
                            intent.putExtra("statLat", tMapMarkerItem.getTMapPoint().getLatitude());
                            intent.putExtra("statLong", tMapMarkerItem.getTMapPoint().getLongitude());
                            u_id = getIntent().getStringExtra("u_id");
                            intent.putExtra("user_id", u_id);
                            startActivity(intent);
                        }
                    });

                    tmapview.addMarkerItem("markerItem" + id, markerItem); // ????????? ?????? ??????
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Marker>> call, Throwable t) {
                Log.i("Marker", "?????? ??????");
            }
        });


        //=======================================
        context = getApplicationContext();
        //??????????????? ??????====================================
        iv_menu = (ImageView)findViewById(R.id.iv_back);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);
        //==================================================
        //??????????????? ???????????? ??????=============================
        txtMyPage = (TextView)findViewById(R.id.txt_myPage); //??????????????? ??????????????? ?????????
        txtCommunity = (TextView)findViewById(R.id.txt_community); // ??????????????? ???????????? ?????????
        txtQna = (TextView)findViewById(R.id.txt_qna); //??????????????? QNA?????????
        txtIntro = (TextView)findViewById(R.id.txt_intro); //??????????????? ?????? ?????????
        txtCard = (TextView)findViewById(R.id.txt_card);
        txtFacilReco = (TextView)findViewById(R.id.txt_facilReco);
        txtLogin = (TextView)findViewById(R.id.txt_Login);
        txtJoin = (TextView)findViewById(R.id.txt_Join);
        //===================================================
//        intent = new Intent(MainActivity.this, MyPageActivity.class);
//        u_id = getIntent().getStringExtra("u_id");
//        if(u_id != null){
//            Log.i("??????????????????:", "onCreate: " + u_id);
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putString("userid", u_id);
//            editor.commit();
//        }



//        drawerBinding.txtMyPage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                intent = new Intent(MainActivity.this, MyPageActivity.class);
//                startActivity(intent);
//            }
//        });
        txtMyPage.setOnClickListener(new View.OnClickListener() { //??????????????? ??????
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, MyPageActivity.class);
                u_id = getIntent().getStringExtra("u_id");
                intent.putExtra("u_id", u_id);
                Log.i("TAG", "onClick: ?????? -> ??????????????? id?????????" + u_id);
                startActivity(intent);
            }
        });
        txtCommunity.setOnClickListener(new View.OnClickListener() { //???????????? ??????
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, CommunityAcitivty.class);
                u_id = getIntent().getStringExtra("u_id");
                intent.putExtra("u_id", u_id);
                Log.i("TAG", "onClick: ?????? -> ???????????? id?????????" + u_id);
                startActivity(intent);
            }
        });
        txtQna.setOnClickListener(new View.OnClickListener() { //???????????? ??????
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, QnaAcitivty.class);
                u_id = getIntent().getStringExtra("u_id");
                intent.putExtra("u_id", u_id);
                Log.i("TAG", "onClick: ?????? -> QNA id?????????" + u_id);
                startActivity(intent);
            }
        });
        txtIntro.setOnClickListener(new View.OnClickListener(){ //??????????????? ??????
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, IntroActivity.class);
                startActivity(intent);
            }
        });
        txtCard.setOnClickListener(new View.OnClickListener() { //?????? ????????? ??????
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, CardActivity.class);
                startActivity(intent);
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        txtJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        //============================================

        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerView);
            }
        });
    }


    private Bitmap createmarkerIcon(int image){
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), image);
        bitmap = Bitmap.createScaledBitmap(bitmap, 70, 70 ,false);
        return bitmap;
    }

    private Bitmap createmarkerLeftImage(int image){
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), image);
        bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100 ,false);
        return bitmap;
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("onStart", "onStart: " + "??????");
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void test(){
        TMapData tMapData;
        Log.i("test", "test: "+tmapdata);
    }

}