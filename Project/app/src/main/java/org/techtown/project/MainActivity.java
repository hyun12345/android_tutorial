package org.techtown.project;

import android.drm.DrmStore;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    MenuFragment1 menuFragment1;
    MenuFragment2 menuFragment2;
    MenuFragment3 menuFragment3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        menuFragment1 = new MenuFragment1();
        menuFragment2 = new MenuFragment2();
        menuFragment3 = new MenuFragment3();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, menuFragment1).commit();

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("HOME"));
        tabs.addTab(tabs.newTab().setText("예약하기"));
        tabs.addTab(tabs.newTab().setText("찾아오는 길"));

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

                                message.setText("검색 버튼을 눌렀습니다.");

                                return true;

                            case R.id.menu2:

                                message.setText("검색 버튼을 눌렀습니다.");

                                return true;

                            case R.id.menu3:

                                message.setText("환경설정 버튼을 눌렀습니다.");

                                return true;

                            case R.id.menu4:

                                message.setText("환경설정 버튼을 눌렀습니다.");

                                return true;
                        }
                        return false;
                    }
                });

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment selected = null;
                if(position == 0) {
                    selected = menuFragment1;
                } else if(position == 1) {
                    selected = menuFragment2;
                } else if(position == 2) {
                    selected = menuFragment3;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
