package org.techtown.event;

import android.gesture.Gesture;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    //손가락 움직임 자동으로 계산되도록 만들 수 있음
    GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        View view = findViewById(R.id.view);
        view.setOnTouchListener(new View.OnTouchListener() {
            //손가락으로 터치하면 onTouch() 메소드 호출(손가락으로 누르거나 떼거나 움직였을 때 등)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                //손가락으로 누르고 있는 x, y 좌표(float:실수)
                float curX = event.getX();
                float curY = event.getY();

                //MotionEvent
                //ACTION_DOWN : 손가락 누르고 있을 때
                if(action == MotionEvent.ACTION_DOWN) {
                    println("손가락 눌렸음 | x : " + curX + " & y : " + curY);
                //ACTION_MOVE : 손가락 움직였을 때
                } else if(action == MotionEvent.ACTION_MOVE) {
                    println("손가락 움직임 | x : " + curX + " & y : " + curY);
                //ACTION_UP : 손가락 뗐을 때
                } else if(action == MotionEvent.ACTION_UP) {
                    println("손가락 떼졌음 | x : " + curX + " & y : " + curY);
                }
                return true;
            }
        });

        //GestureDetector : Touch 이벤트로 전달되는 이벤트 계산
        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                println("onDown() 호출됨.");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                println("onShowPress() 호출됨.");

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                println("onSingleTapUp() 호출됨.");
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                println("onScroll() 호출됨 | x : " + distanceX + " & y : " + distanceY);
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                println("onLongPress() 호출됨.");

            }

            //속도 자동계산하여 파라미터로 넘겨줌
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                println("onFling() 호출됨 | x : " + velocityX + " & y : " + velocityY);
                return true;
            }
        });

        View view2 = findViewById(R.id.view2);
        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //detector 객체가 있으면 event 객체 전달
                detector.onTouchEvent(event);
                return true;
            }
        });
    }

    //key가 눌렸을 때 이벤트 처리
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //keyCode
        //KEYCODE_BACK : 시스템 BACK 키 눌림
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(this, "시스템 BACK 버튼 눌림.", Toast.LENGTH_LONG).show();
            //onKeyDown Down 처리 못하도록 함
            return true;
        }
        return false;
    }

    public void println(String data) {
        //append() : 전달받은 data(문자) 뿌리도록 설정 / 한줄씩 뿌리도록 개행 설정
        textView.append(data + "\n");
    }
}
