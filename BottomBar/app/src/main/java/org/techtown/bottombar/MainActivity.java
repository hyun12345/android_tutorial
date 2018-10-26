package org.techtown.bottombar;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 버튼 누른 결과를 보여주기 위해 TextView를 사용합니다.
        final TextView message = (TextView)findViewById(R.id.textview);


        // 버튼 클릭시 사용되는 리스너를 구현합니다.
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                        // 어떤 메뉴 아이템이 터치되었는지 확인합니다.
                        switch (item.getItemId()) {

                            case R.id.menu:

                                message.setText("새로고침 버튼을 눌렀습니다.");

                                return true;

                            case R.id.menu2:

                                message.setText("검색 버튼을 눌렀습니다.");

                                return true;

                            case R.id.menu3:

                                message.setText("환경설정 버튼을 눌렀습니다.");

                                return true;
                        }
                        return false;
                    }
                });
    }
}