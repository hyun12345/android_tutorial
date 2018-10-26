package org.techtown.myintent;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //xml layout 파일 서로 연결시켜주고 있으므로 MainActivity.java와 activity_main.xml은 한 쌍의 파일임을 알 수 있음
        //-> xml layout 파일을 layout inflation 했음을 알 수 있음
        //layout inflation : xml 파일의 버튼 등을 메모리 객체화 -> 자바 소스코드에서 말하는 화면에 갖다 붙임

        Button button  = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getApplicationContext(), MenuActivity.class);
                startActivityForResult(intent, 101);
            }
        });
    }

    //MenuActivity에서 intent로 extraData 전달

    //응답을 받아주는 메소드(intent 전달 받기 가능)
    //requestCode : 101
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101) {
            String name = data.getStringExtra("name");
            Toast.makeText(getApplicationContext(), "메뉴화면으로부터 응답 : " + name, Toast.LENGTH_LONG).show();

        }
    }
}
