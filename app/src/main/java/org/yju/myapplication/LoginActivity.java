package org.yju.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.Profile;
import com.kakao.usermgmt.response.model.UserAccount;
import com.kakao.util.OptionalBoolean;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import org.yju.myapplication.data.User;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    public static String userID = null;
    private TextView txt_findId, txt_findPwd, txt_Join, txt_logout;
    private EditText et_id, et_pass;
    private Button btn_login, btn_register, btn_find, btn_pwFind, testButton;
    DataService dataService = new DataService();
    private User user = new User();
    private static final String TAG = "TEST";
    //    private ISessionCallback callback = new ISessionCallback();
    com.kakao.auth.Session session;
    public static final int RC_SIGN_IN = 1;
    private Button kButton;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
//        btn_find = findViewById(R.id.btn_find);
//        btn_pwFind = findViewById(R.id.btn_pwFind);
        txt_findId = (TextView) findViewById(R.id.txt_findId);
        txt_findPwd = (TextView) findViewById(R.id.txt_findPwd);
        txt_Join = (TextView) findViewById(R.id.txt_Join);
        txt_logout = (TextView) findViewById(R.id.txt_logout);

//     ======================????????? ??????===============
//        // ???????????? ????????? ?????? ??? ??????
//        txt_Join.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        // ????????? ??????
//        txt_findId.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, FindUserActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        // ???????????? ??????
//        txt_findPwd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, FindPwActivity.class);
//                startActivity(intent);
//            }
//        });
//  =====================================================================

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> map = new HashMap();
                // EditText??? ?????? ?????????????????? ?????? get(????????????)?????????.
                String u_id = et_id.getText().toString();
                String u_pwd = et_pass.getText().toString();

                user.setU_id(u_id);
                user.setU_pwd(u_pwd);

                dataService.select.login(user).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        try {
                            if (!response.body().isEmpty()) {
                                Log.i("TAG", "onResponse: " + response.body());
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("u_id", u_id);

                                startActivity(intent);
                            }
                        } catch (NullPointerException e) {
                            Log.i("TAG", "onResponse: ??????????????????" + response.body());
                            Toast.makeText(LoginActivity.this, "????????? ?????? ???????????? ???????????????", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });


//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        kButton = findViewById(R.id.k_button);

        // ????????? ??????
        session = com.kakao.auth.Session.getCurrentSession();
        session.addCallback(callback);
//        session.checkAndImplicitOpen();

        kButton.setOnClickListener(v -> {
            Log.i(TAG, "onCreate: kbutton");
            session.open(AuthType.KAKAO_LOGIN_ALL, LoginActivity.this);

        });
        txt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickUnlink();
            }
        });

    }

    public void findId(View view) {
        Intent intent = new Intent(LoginActivity.this, FindUserActivity.class);
        startActivity(intent);
    }

    public void findPwd(View view) {
        Intent intent = new Intent(LoginActivity.this, FindPwActivity.class);
        startActivity(intent);
    }

    public void join(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // ?????? ?????? ??????
        com.kakao.auth.Session.getCurrentSession().removeCallback(callback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        // ????????????|????????? ??????????????? ?????? ????????? ????????? SDK??? ??????
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    ISessionCallback callback = new ISessionCallback() {
        @Override
        public void onSessionOpened() {
            requestMe();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {

        }
    };

    private void requestMe() {
        UserManagement.getInstance()
                .me(new MeV2ResponseCallback() {
                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {
                        Log.e("KAKAO_API", "????????? ?????? ??????: " + errorResult);
                    }

                    @Override
                    public void onFailure(ErrorResult errorResult) {
                        Log.e("KAKAO_API", "????????? ?????? ?????? ??????: " + errorResult);
                    }

                    @Override
                    public void onSuccess(MeV2Response result) {

                        String user_name = null;
                        String user_email = null;
                        String user_id = null;

                        user_id = Long.toString(result.getId());
                        Log.i("KAKAO_API", "????????? ?????????: " + result.getId());

                        UserAccount kakaoAccount = result.getKakaoAccount();
                        if (kakaoAccount != null) {

                            // ?????????
                            String email = kakaoAccount.getEmail();

                            if (email != null) {
                                Log.i("KAKAO_API", "email: " + email);
                                user_email = email;

                            } else if (kakaoAccount.emailNeedsAgreement() == OptionalBoolean.TRUE) {
                                // ?????? ?????? ??? ????????? ?????? ??????
                                // ???, ?????? ????????? ???????????? ????????? ????????? ?????? ???????????? ????????? ????????? ????????? ???????????? ???????????? ?????????.

                            } else {
                                // ????????? ?????? ??????
                            }

                            // ?????????
                            Profile profile = kakaoAccount.getProfile();

                            if (profile != null) {
                                Log.d("KAKAO_API", "nickname: " + profile.getNickname());
                                Log.d("KAKAO_API", "profile image: " + profile.getProfileImageUrl());
                                Log.d("KAKAO_API", "thumbnail image: " + profile.getThumbnailImageUrl());
                                user_name = profile.getNickname();

                            } else if (kakaoAccount.profileNeedsAgreement() == OptionalBoolean.TRUE) {
                                // ?????? ?????? ??? ????????? ?????? ?????? ??????

                            } else {
                                // ????????? ?????? ??????
                            }
                        }

                    }
                });
    }

    private void onClickUnlink() {
        // ??? ?????? ????????? ????????? ???????????? ????????? ???????????? ?????? ????????? ?????? ?????????????????? ??????????????? ???????????? ??? ?????? ????????? ?????? ????????? ????????????.
        // ??? ?????? ????????? ????????? ???????????? ??????????????? ????????? ??????????????? ????????? ????????? ???????????? ????????? ????????? ??? ??????.
        // ???, ?????? ??? ????????? ?????? ????????? ???????????? ????????? ????????? ???????????? ????????? ?????? ??????.
        final String appendMessage = getString(R.string.com_kakao_confirm_unlink);
        new AlertDialog.Builder(this)
                .setMessage(appendMessage)
                .setPositiveButton(getString(R.string.com_kakao_ok_button),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                UserManagement.getInstance().requestUnlink(new UnLinkResponseCallback() {
                                    @Override
                                    public void onFailure(ErrorResult errorResult) {
                                        Logger.e(errorResult.toString());
                                    }

                                    @Override
                                    public void onSessionClosed(ErrorResult errorResult) {
//                                        redirectLoginActivity();
                                    }

                                    @Override
                                    public void onNotSignedUp() {
                                        LoginActivity loginActivity = new LoginActivity();
//                                        loginActivity.redirectSignupActivity();
                                    }

                                    @Override
                                    public void onSuccess(Long userId) {
//                                        redirectLoginActivity();
                                    }
                                });
                                dialog.dismiss();
                            }
                        })
                .setNegativeButton(getString(R.string.com_kakao_cancel_button),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
    }
}