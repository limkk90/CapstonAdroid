package org.yju.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.yju.myapplication.data.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindUserActivity extends AppCompatActivity {

    private EditText et_findEm;
    private Button btn_findEm;
    private TextView tv_findEm;
    DataService dataService = new DataService();
    User user = new User();
    private String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_user);

        et_findEm = findViewById(R.id.et_findEm);
        btn_findEm = findViewById(R.id.btn_findEm);
        tv_findEm = findViewById(R.id.tv_findEm);

        btn_findEm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u_email = et_findEm.getText().toString();

                user.setU_email(u_email);

                dataService.select.findId(u_email).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.i(TAG, "onResponse: 돌아가는편??" + response.body());
                        try {
                            if (!u_email.equals(null)) {
                                tv_findEm.setText(response.body().getU_id());
                            }
                        } catch (NullPointerException e) {
                            Toast.makeText(FindUserActivity.this, "이메일을 적어주세요", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(FindUserActivity.this, "이메일을 확인해주세요", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }
}