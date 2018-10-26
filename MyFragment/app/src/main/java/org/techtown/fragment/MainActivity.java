package org.techtown.fragment;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    MainFragment fragment1;
    MenuFragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new MainFragment();
        fragment2 = new MenuFragment();


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment 추가 : FragmentManager 사용 / Activity 본따 만들어졌기 때문에
                //여러 개 한꺼번에 생성할 수 있으므로 beginTransaction으로 시작하도록 설정
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment 추가 : FragmentManager 사용
                //여러 개 한꺼번에 생성할 수 있으므로 beginTransaction으로 시작하도록 설정
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
            }
        });
    }

    public void onFragmentChange(int index) {
        if (index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
        } else if (index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
        }
    }
}
