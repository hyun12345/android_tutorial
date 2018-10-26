package org.techtown.sliding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout page;

    Animation translateLeft;
    Animation translateRight;

    Button button;

    Boolean isPageOpen = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page = (LinearLayout) findViewById(R.id.page);

        translateLeft = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRight = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        //리스너 객체 생성
        SlidingAnimationListener listener = new SlidingAnimationListener();
        //끝나기 직전에 리스너에 생성한 callback 함수 실행
        translateLeft.setAnimationListener(listener);
        translateRight.setAnimationListener(listener);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //isPageOpen = true : 페이지 열려있음
                if (isPageOpen) {
                    //닫기 위한 애니메이션 동작
                    page.startAnimation(translateRight);
                } else {
                    //페이지 보이도록 설정
                    page.setVisibility(View.VISIBLE);
                    page.startAnimation(translateLeft);
                }
            }
        });
    }

    //내부 클래스(리스너 애니메이션 객체에 설정)
    class SlidingAnimationListener implements Animation.AnimationListener {
        //애니메이션 시작할 때
        @Override
        public void onAnimationStart(Animation animation) {

        }

        //애니메이션 끝날 때
        @Override
        public void onAnimationEnd(Animation animation) {
            //페이지 오픈 된 상태
            if(isPageOpen) {
                //페이지 안보이도록 함
                page.setVisibility(View.INVISIBLE);
                button.setText("OPEN");
                isPageOpen = false;
            //페이지 오픈 되지 않은 상태
            } else {
                button.setText("CLOSE");
                isPageOpen = true;
            }
        }

        //애니메이션 반복될 때
        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
