package org.techtown.danjibank;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    MenuFragment1 menuFragment1;
    MenuFragment2 menuFragment2;
    MenuFragment3 menuFragment3;
    MenuFragment4 menuFragment4;

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
        menuFragment4 = new MenuFragment4();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        Fragment selected = null;
                        switch (item.getItemId()) {
                            case R.id.menu:
                                selected = menuFragment1;
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
                                return true;

                            case R.id.menu2:
                                selected = menuFragment2;
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
                                return true;

                            case R.id.menu3:
                                selected = menuFragment3;
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
                                return true;

                            case R.id.menu4:
                                selected = menuFragment4;
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
                                return true;
                        }
                        return false;
                    }
                });
    }
}
