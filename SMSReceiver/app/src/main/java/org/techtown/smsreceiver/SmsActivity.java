package org.techtown.smsreceiver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SmsActivity extends AppCompatActivity {

    EditText editText;
    EditText editText2;
    EditText editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);

        Button button  = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 클릭하면 화면 닫아줌(MainActivity로 가게 됨)
                finish();
            }
        });

        //Intent 객체 passedIntent의 값을 Intent를 받아오는 값으로 설정ㄴ
        Intent passedIntent = getIntent();
        processCommand(passedIntent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processCommand(intent);
        super.onNewIntent(intent);
    }

    private void processCommand(Intent intent) {
        //intent의 값이 null이 아닌 경우 문자열 변수로 SmsReceiver에서 받아온 값을 저장
        if(intent != null) {
            String sender = intent.getStringExtra("sender");
            String contents = intent.getStringExtra("contents");
            String receivedDate = intent.getStringExtra("receivedDate");

            //plainText 값을 setText(변수)로 설정 가능
            editText.setText(sender);
            editText2.setText(contents);
            editText3.setText(receivedDate);

        }

    }

}
