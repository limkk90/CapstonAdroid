package org.yju.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.yju.myapplication.data.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends AppCompatActivity {

    private TextView tv_id, tv_pass, tv_email, tv_phone;
    DataService dataService = new DataService();
    private String u_id1, u_pwd, u_email, u_phone;
    private Button btn_user, btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_user_profile);

        tv_id = findViewById(R.id.tv_id);
        tv_pass = findViewById(R.id.tv_pass);
        tv_email = findViewById(R.id.tv_email);
        tv_phone = findViewById(R.id.tv_phone);
        btn_user = findViewById(R.id.btn_user);
        btn_home = findViewById(R.id.btn_home);


        Intent intent = getIntent();

        String u_id = intent.getStringExtra("u_id");



        dataService.select.prifile(u_id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i("TAG", "onResponse: response =  ++++++++" + response.body());
                u_id1 = response.body().getU_id();
                u_pwd = response.body().getU_pwd();
                u_email = response.body().getU_email();
                u_phone = response.body().getU_phone();

                tv_id.setText(u_id1);
                tv_pass.setText(u_pwd);
                tv_email.setText(u_email);
                tv_phone.setText(u_phone);

            }


            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }


        });

        btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(org.yju.myapplication.UserProfileActivity.this, UpdateProfile.class);
                intent.putExtra("u_id", u_id);
                Log.i("TAG", "onClick: putput" + u_id);
                startActivity(intent);
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(org.yju.myapplication.UserProfileActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });




    }

}