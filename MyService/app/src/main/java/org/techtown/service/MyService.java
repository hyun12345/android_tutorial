package org.techtown.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = "MyService";

    public MyService() {
    }

    //한 번 실행하면 서비스는 계속 실행되어 있음 / 여러번 실행해도 새로 만들어지지 않음 / intent 객체 확인 불가능
    //서비스가 이미 실행되어 있는 상태이면 Log에 onCrate() 출력되지 않음 / 처음 실행했을 때만 Log에 출력됨
    //시스템이 서비스 자동적으로 실행시키기 때문에 이러한 경우 발생
    //onCreate() : 서비스 실행
    @Override
    public void onCreate() {
        super.onCreate();
        //디버그 로그
        Log.d(TAG, "onCreate() 호출됨.");
    }

    //위의 문제 때문에 onStartCommand 메소드 활용하여 intent 객체 확인 가능
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() 호출됨.");

        if(intent == null) {
            return Service.START_STICKY;
            //서비스 종료되도 다시 실행하도록 return 값으로 START_STICKY 설정
        } else {
            processCommand(intent);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent) {
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");

        Log.d(TAG, "전달받은 데이터 : " + command + ", " + name);

        //5초동안 중지(5초 후에 showIntent 보냄)
        try {
            Thread.sleep(5000);
        } catch (Exception e) {}

        //MainActivity에 showIntent 보내기 위해 설정
        Intent showIntent = new Intent(getApplicationContext(), MainActivity.class);

        //서비스에서 화면 띄우기 위해(TASK 정보 부여) 옵션을 주기 위해 FLAG 설정(일반적으로 아래 세 옵션 함께 사용)
        //FLAG_ACTIVITY_NEW_TASK : 화면 없는 곳에서 화면 있는 것 띄울 수 있음
        //FLAG_ACTIVITY_SINGLE_TOP : 만들어져 있는 액티비티 재활용하도록(이미 화면에 띄워져 있음)
        //FLAG_ACTIVITY_CLEAR_TOP : 다른 액티비티가 위에 떠 있는 경우 제거
        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|
                            Intent.FLAG_ACTIVITY_SINGLE_TOP|
                            Intent.FLAG_ACTIVITY_CLEAR_TOP);
        showIntent.putExtra("command", "show");
        showIntent.putExtra("name", name + " from service.");
        startActivity(showIntent);
        //화면 TASK에 묶여있음(각각 화면 연속적으로 띄우는 역할 : TASK)
        //화면이 없는 서비스에서 화면 띄우려고 하면 오류 발생(TASK 정보 없기 때문에)

    }

    //onDestroy() : 서비스 종료
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() 호출됨.");
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
