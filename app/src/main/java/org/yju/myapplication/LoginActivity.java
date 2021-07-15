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

//     ======================변경전 코드===============
//        // 회원가입 버튼을 클릭 시 수행
//        txt_Join.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        // 아이디 찾기
//        txt_findId.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, FindUserActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        // 비밀번호 찾기
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
                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
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
                            Log.i("TAG", "onResponse: 가르쳐주세용" + response.body());
                            Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호 오류입니다", Toast.LENGTH_SHORT).show();
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

        // 카카오 세션
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

        // 세션 콜백 삭제
        com.kakao.auth.Session.getCurrentSession().removeCallback(callback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        // 카카오톡|스토리 간편로그인 실행 결과를 받아서 SDK로 전달
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
                        Log.e("KAKAO_API", "세션이 닫혀 있음: " + errorResult);
                    }

                    @Override
                    public void onFailure(ErrorResult errorResult) {
                        Log.e("KAKAO_API", "사용자 정보 요청 실패: " + errorResult);
                    }

                    @Override
                    public void onSuccess(MeV2Response result) {

                        String user_name = null;
                        String user_email = null;
                        String user_id = null;

                        user_id = Long.toString(result.getId());
                        Log.i("KAKAO_API", "사용자 아이디: " + result.getId());

                        UserAccount kakaoAccount = result.getKakaoAccount();
                        if (kakaoAccount != null) {

                            // 이메일
                            String email = kakaoAccount.getEmail();

                            if (email != null) {
                                Log.i("KAKAO_API", "email: " + email);
                                user_email = email;

                            } else if (kakaoAccount.emailNeedsAgreement() == OptionalBoolean.TRUE) {
                                // 동의 요청 후 이메일 획득 가능
                                // 단, 선택 동의로 설정되어 있다면 서비스 이용 시나리오 상에서 반드시 필요한 경우에만 요청해야 합니다.

                            } else {
                                // 이메일 획득 불가
                            }

                            // 프로필
                            Profile profile = kakaoAccount.getProfile();

                            if (profile != null) {
                                Log.d("KAKAO_API", "nickname: " + profile.getNickname());
                                Log.d("KAKAO_API", "profile image: " + profile.getProfileImageUrl());
                                Log.d("KAKAO_API", "thumbnail image: " + profile.getThumbnailImageUrl());
                                user_name = profile.getNickname();

                            } else if (kakaoAccount.profileNeedsAgreement() == OptionalBoolean.TRUE) {
                                // 동의 요청 후 프로필 정보 획득 가능

                            } else {
                                // 프로필 획득 불가
                            }
                        }

                    }
                });
    }

    private void onClickUnlink() {
        // 앱 연결 해제는 카카오 플랫폼에 연결된 사용자와 앱의 연결을 영구 해제함으로서 일반적으로 사용자가 앱 탈퇴 요청을 하는 경우와 비슷하다.
        // 앱 연결 해제가 수행된 사용자는 영구적으로 복구가 불가능하며 카카오 플랫폼 서비스를 더이상 사용할 수 없다.
        // 단, 다시 앱 연결을 통해 새로운 데이터로 카카오 플랫폼 서비스를 이용할 수는 있다.
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