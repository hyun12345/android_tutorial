package org.techtown.orientation2;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //설정 바뀔 때 호출되는 메소드(ex)방향 바뀔 때)
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        //파라미터로 전달된 newConfig 안에 orientation이라는 정보 있음
        //아래 if 조건의 조건식을 가로방향으로 돌렸을 때
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "가로방향 됨.", Toast.LENGTH_LONG).show();
            //세로방향으로 돌렸을 때
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "세로방향 됨.", Toast.LENGTH_LONG).show();
        }
    }
}
