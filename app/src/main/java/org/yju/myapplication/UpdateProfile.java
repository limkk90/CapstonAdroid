package org.yju.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.yju.myapplication.data.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfile extends AppCompatActivity {

    private EditText et_newPwd, et_rePwd;
    private Button btn_update;
    private String u_id;
    private boolean a = true;
    DataService dataService = new DataService();
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);


        btn_update = findViewById(R.id.btn_update);


        Intent intent = getIntent();
        u_id = intent.getStringExtra("u_id");


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_newPwd = findViewById(R.id.et_newPwd);
                String newPwd = et_newPwd.getText().toString();
                et_rePwd = findViewById(R.id.et_rePwd);
                String rePwd = et_rePwd.getText().toString();

                Log.i("TAG", "onClick: n " + newPwd);
                Log.i("TAG", "onClick: r " + rePwd);
                if (!rePwd.equals(newPwd)) {
                    Toast.makeText(org.yju.myapplication.UpdateProfile.this,"비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                    a = false;
                } else {
                    Toast.makeText(org.yju.myapplication.UpdateProfile.this,"변경되었습니다", Toast.LENGTH_SHORT).show();
                    a = true;
                }

                user.setU_id(u_id);
                user.setU_pwd(newPwd);

                if (a == true) {

                    dataService.update.update(user).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {

                            Log.i("TAG", "onResponse: I love it" + response.body());

                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });

                    Intent intent = new Intent(org.yju.myapplication.UpdateProfile.this, UserProfileActivity.class);
                    intent.putExtra("u_id", u_id);
                    Log.i("TAG", "onClick: rererego" + user);
                    startActivity(intent);
                }






            }
        });

    }
}