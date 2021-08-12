package org.yju.myapplication.Reservation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.yju.myapplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import kr.co.bootpay.Bootpay;
import kr.co.bootpay.BootpayAnalytics;
import kr.co.bootpay.enums.Method;
import kr.co.bootpay.enums.PG;
import kr.co.bootpay.enums.UX;
import kr.co.bootpay.listener.CancelListener;
import kr.co.bootpay.listener.CloseListener;
import kr.co.bootpay.listener.ConfirmListener;
import kr.co.bootpay.listener.DoneListener;
import kr.co.bootpay.listener.ErrorListener;
import kr.co.bootpay.listener.ReadyListener;
import kr.co.bootpay.model.BootExtra;
import kr.co.bootpay.model.BootUser;

public class KakaoPay extends AppCompatActivity {
    private int stuck = 10;
    Button btn_kakaoPay;
    String strStartTime, strEndTime;
    Intent intent;
    Date start_time = null, end_time = null;
    Calendar calendarStart, calendarEnd;
    double chargingPay;
    long diff, diffMinutes;
    EditText inputKW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakao_pay);
        //yyyy-MM-dd HH:mm:ss
        btn_kakaoPay = findViewById(R.id.btn_kakaoPay);

        intent = getIntent();
        strStartTime = intent.getStringExtra("start_time");
        strEndTime = intent.getStringExtra("end_time");
        Log.i("KakaoPay", "onCreate: " + strStartTime);
        Log.i("KakaoPay", "onCreate: " + strEndTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");  //날짜 포맷

        try {
            start_time = dateFormat.parse(strStartTime);
            end_time = dateFormat.parse(strEndTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.i("KakaoPay", "onCreate제: " + start_time);
        Log.i("KakaoPay", "onCreate발: " + end_time);

         diff = end_time.getTime() - start_time.getTime();
         diffMinutes = diff/(60*1000);  //예약종료 시간 - 예약시작 시간
        Log.i("KakaoPay", "onCreate되: " + diff);
        Log.i("KakaoPay", "onCreate라: " + diffMinutes);

        chargingPay = diffMinutes * 200;
        Log.i("KakaoPay", "onCreate뀨: " + chargingPay);

    }


    public void onClick_request(View v){
        BootUser bootUser = new BootUser().setPhone("010-1234-5678");
        BootExtra bootExtra = new BootExtra().setQuotas(new int[] {0,2,3});

        Bootpay.init(getFragmentManager())
                .setApplicationId("60f670177b5ba4001f1de8fc")
                .setPG(PG.INICIS) // 결제할 PG 사
                .setMethod(Method.CARD) //결제수단
                .setContext(this)
                .setBootUser(bootUser)
                .setBootExtra(bootExtra)
                .setUX(UX.PG_DIALOG)
                .setName("전기차 충전소 결제금액") //결제할 상품명
                .setOrderId("1234") //결제 고유번호expire_month
                .setPrice(chargingPay) // 결제할 금액
                .addItem("마우's 스", 1, "ITEM_CODE_MOUSE", 100) // 주문정보에 담길 상품정보, 통계를 위해 사용
                .addItem("키보드", 1, "ITEM_CODE_KEYBOARD", 200, "패션", "여성상의", "블라우스") // 주문정보에 담길 상품정보, 통계를 위해 사용
                .onConfirm(new ConfirmListener() { // 결제가 진행되기 바로 직전 호출되는 함수로, 주로 재고처리 등의 로직이 수행
                    @Override
                    public void onConfirm(@Nullable String message) {

                        if (0 < stuck) Bootpay.confirm(message); // 재고가 있을 경우.
                        else Bootpay.removePaymentWindow(); // 재고가 없어 중간에 결제창을 닫고 싶을 경우
                        Log.d("confirm", message);
                    }
                })
                .onDone(new DoneListener() { // 결제완료시 호출, 아이템 지급 등 데이터 동기화 로직을 수행합니다
                    @Override
                    public void onDone(@Nullable String message) {
                        Log.d("done", message);
                    }
                })
                .onReady(new ReadyListener() { // 가상계좌 입금 계좌번호가 발급되면 호출되는 함수입니다.
                    @Override
                    public void onReady(@Nullable String message) {
                        Log.d("ready", message);
                    }
                })
                .onCancel(new CancelListener() { // 결제 취소시 호출
                    @Override
                    public void onCancel(@Nullable String message) {

                        Log.d("cancel", message);
                    }
                })
                .onError(new ErrorListener() { // 에러가 났을때 호출되는 부분
                    @Override
                    public void onError(@Nullable String message) {
                        Log.d("error", message);
                    }
                })
                .onClose(
                        new CloseListener() { //결제창이 닫힐때 실행되는 부분
                            @Override
                            public void onClose(String message) {
                                Log.d("close", "close");
                            }
                        })
                        .request();
    }

}