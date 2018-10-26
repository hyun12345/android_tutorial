package org.techtown.mylifecycle;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //순서 : onCreate -> onStart -> onResume -> onPause -> onStop -> onDestroy

    @Override
    //시작점
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "onCreate() 호출됨", Toast.LENGTH_LONG).show();

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(this, "onStart() 호출됨", Toast.LENGTH_LONG).show();
    }

    //뒤로 돌아가면서 완전히 중지된 상태
    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(this, "onStop() 호출됨", Toast.LENGTH_LONG).show();
    }

    //화면이 포커스 이룸
    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(this, "onPause() 호출됨", Toast.LENGTH_LONG).show();

        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        //edit() : Editor 객체 호출

        editor.putString("name", "단지");
        editor.commit();
        //coomit()으로 저장해야 함
        //중지되는 시점에 저장
    }

    //onCreate()와 반대 : 메모리에서 리소스 다 없어짐
    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "onDestroy() 호출됨", Toast.LENGTH_LONG).show();
    }

    //화면 전환되기 바로 전
    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(this, "onResume() 호출됨", Toast.LENGTH_LONG).show();

        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if(pref != null) {
            String name = pref.getString("name", "");
            //name의 값이 없는 경우 빈 값("")을 가져옴
            Toast.makeText(this, "복구된 이름 : " + name, Toast.LENGTH_LONG).show();
        }
        //다시 실행하는 경우 저장된 이름 호출 
    }

}
