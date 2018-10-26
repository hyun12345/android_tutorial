package org.techtown.danjiresort;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//    MainFragment mainFragment;
    MenuFragment1 menuFragment1;
    MenuFragment2 menuFragment2;
//    MenuFragment3 menuFragment3;

    Button button1;
    Button button2;
    Button button3;
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);

//        mainFragment = new MainFragment();
        menuFragment1 = new MenuFragment1();
        menuFragment2 = new MenuFragment2();
//        menuFragment3 = new MenuFragment3();

        button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "button1 : HOME", Toast.LENGTH_LONG).show();
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();
            }
        });

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "button2 : 리조트 소개", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
                startActivity(intent);
            }
        });

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "button3 : 찾아오기", Toast.LENGTH_LONG).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, menuFragment1).commit();
            }
        });

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "button4 : 예약하기", Toast.LENGTH_LONG).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, menuFragment2).commit();
            }
        });
    }

    public void onFragmentChange(int index) {
        if (index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, menuFragment1).commit();
        } else if (index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, menuFragment2).commit();
        }
    }
}
