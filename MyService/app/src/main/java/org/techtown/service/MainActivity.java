package org.techtown.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();

                //Context 객체 활용 / MyService.class 서비스 객체 이름 지정
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                intent.putExtra("command", "show");
                intent.putExtra("name", name);
                startService(intent);
                //화면 띄울 때는 startActivity() / 서비스 시작할 때는 startService()
            }
        });

//        앱 시작과 동시에 실행되므로 null 값 받아옴
//        Intent passedIntent = getIntent();
//        processCommand(passedIntent);
    }

    //onCreate 호출하지 않고 이 메소드 호출(이미 실행되어 있는 경우)
    @Override
    protected void onNewIntent(Intent intent) {
        processCommand(intent);
        super.onNewIntent(intent);
    }

    private void processCommand(Intent intent) {
        if(intent != null) {
            //서비스쪽에서 보내온 데이터 받아오는 문자열 변수 각각 생성
            String command = intent.getStringExtra("command");
            String name = intent.getStringExtra("name");

            Toast.makeText(this, "서비스로부터 전달받은 데이터 : " + command + ", " + name, Toast.LENGTH_LONG).show();
        }
    }
}
