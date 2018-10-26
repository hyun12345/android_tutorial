package org.techtown.resort;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;

public class RoomActivity extends AppCompatActivity {
    Toolbar toolbar;

    RoomFragment1 roomFragment1;
    RoomFragment2 roomFragment2;
    RoomFragment3 roomFragment3;

    Button button1;
    Button button2;
    Button button3;
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        roomFragment1 = new RoomFragment1();
        roomFragment2 = new RoomFragment2();
        roomFragment3 = new RoomFragment3();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, roomFragment1).commit();

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("스위트룸"));
        tabs.addTab(tabs.newTab().setText("더블룸"));
        tabs.addTab(tabs.newTab().setText("싱글룸"));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment selected = null;
                if (position == 0) {
                    selected = roomFragment1;
                } else if (position == 1) {
                    selected = roomFragment2;
                } else if (position == 2) {
                    selected = roomFragment3;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

    }
//
//        button1 = (Button) findViewById(R.id.button);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "button1 : HOME", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);            }
//        });
//
//        button2 = (Button) findViewById(R.id.button2);
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "button2 : 스위트룸", Toast.LENGTH_LONG).show();
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, roomFragment1).commit();
//            }
//        });
//
//        button3 = (Button) findViewById(R.id.button3);
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "button3 : 더블룸", Toast.LENGTH_LONG).show();
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, roomFragment2).commit();
//            }
//        });
//
//        button4 = (Button) findViewById(R.id.button4);
//        button4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "button4 : 싱글룸", Toast.LENGTH_LONG).show();
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, roomFragment3).commit();
//            }
//        });
//    }
//
//    public void onFragmentChange(int index) {
//        if (index == 0) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.container, roomFragment1).commit();
//        } else if (index == 1) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.container, roomFragment2).commit();
//        } else if (index == 2) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.container, roomFragment3).commit();
//        }
//    }
}
