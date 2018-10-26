package org.techtown.bankproject;

import android.drm.DrmStore;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;

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
