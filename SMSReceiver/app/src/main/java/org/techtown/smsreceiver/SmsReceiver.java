package org.techtown.smsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {

    //로그 디버그 하기 위해 TAG 생성(구분자 역할)
    private static final String TAG = "SmsReceiver";

    //Date type을 String type으로 형변환 하기 위해 SimpleDateFormat 객체 생성 후 값(출력 패턴) 설정
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive() 메소드 호출됨.");

        //Bundle 객체 / extras 담고 있음
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);

        if (messages.length > 0) {
            //getOriginatingAddress() : 발신자 번호
            String sender = messages[0].getOriginatingAddress();
            Log.d(TAG, "sender : " + sender);

            //getMessageBody().toString() : 메세지 내용
            String contents = messages[0].getMessageBody().toString();
            Log.d(TAG, "contents : " + contents);

            //getTimestampMillis() : 수신 시간(밀리초 단위) -> Date로 형변환 하여 현재 날짜와 시분초 찍힘
            Date receivedDate = new Date(messages[0].getTimestampMillis());
            Log.d(TAG, "received date : " + receivedDate);

            //Context 객체를 onReceive() 메소드 안에서만 사용할 수 있으므로 sendToActivity() 메소드에 대입하도록 설정ㄴ
            sendToActivity(context, sender, contents, receivedDate);
        }
    }

    //onReceive() 메소드에서 대입 받은 context 객체를 사용할 수 있음
    private void sendToActivity(Context context, String sender, String contents, Date receivedDate) {
        Intent intent = new Intent(context, SmsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|
                        Intent.FLAG_ACTIVITY_SINGLE_TOP|
                        Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //Intent 객체 활용하여 sender, contents, receivedDate의 값을 extra로 전달ㄴ
        intent.putExtra("sender", sender);
        intent.putExtra("contents", contents);
        intent.putExtra("receivedDate", format.format(receivedDate));

        //intent 객체를 대입하여 Activity 실행하도록 함
        context.startActivity(intent);
    }

    //intent로 대입하는 것도 가능
    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        //pdus : sms 데이터 처리하는 국제 프로토콜 smpp 안에 들어가 있는 데이터 형식
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        for (int i = 0; i < objs.length; i++) {
            //안드로이드 버전이 마쉬멜로우 이상인 경우
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[])objs[i], format);
                //안드로이드 버전이 마쉬멜로우 미만인 경우
            } else {
                //get으로 뽑아낸 pdu 던져주도록 설정
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }
        return messages;
    }
}
