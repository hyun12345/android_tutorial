package org.techtown.tab;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1). commit();

        //TabLayout 객체 생성하여 탭 생성할 수 있도록 함 / addTab(newTab()) 메소드 활용
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("친구"));
        tabs.addTab(tabs.newTab().setText("1:1 채팅"));
        tabs.addTab(tabs.newTab().setText("기타"));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //탭 버튼 중 하나 클릭했을 때 자동 실행
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //몇 번 째 탭인지 인덱스 값 받아옴
                int position = tab.getPosition();

                Fragment selected = null;
                if(position == 0) {
                    selected = fragment1;
                } else if (position == 1) {
                    selected = fragment2;
                } else if (position == 2) {
                    selected = fragment3;
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
