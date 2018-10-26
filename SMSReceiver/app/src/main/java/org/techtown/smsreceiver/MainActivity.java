package org.techtown.smsreceiver;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //메니페스트에 설정했던 동일 권한 있는지 체크 가능
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        //PERMISSION_GRANTED : 권한이 주어졌을 때
        if(permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "SMS 수신 권한 주어져 있음.", Toast.LENGTH_LONG).show();
        //PERMISSION_DENIED : 권한이 거부되었을 때
        } else {
            Toast.makeText(this, "SMS 수신 권한 없음.", Toast.LENGTH_LONG).show();

//            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {
//                Toast.makeText(this, "SMS 권한 설명 필요함.", Toast.LENGTH_LONG).show();
//            } else {
                //권한 부여 여부 창 출력하도록 함
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECEIVE_SMS}, 100);
//            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED ) {
                        Toast.makeText(this, "SMS 수신 권한을 사용자가 승인함.", Toast.LENGTH_LONG).show();
                    } else if(grantResults[0] == PackageManager.PERMISSION_DENIED) {
                        Toast.makeText(this, "SMS 수신 권한을 사용자가 거부함.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "SMS 수신 권한을 사용자가 부여받지 못함.", Toast.LENGTH_LONG).show();
                }
        }

    }
}
