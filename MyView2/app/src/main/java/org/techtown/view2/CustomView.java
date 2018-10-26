package org.techtown.view2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class CustomView extends View {

    //그래픽 그리는 속성 객체 / 색상 정보 등 설정
    Paint paint;

    //필수 생성자 2개
    public CustomView(Context context) {
        super(context);
        init(context);
    }

    //AttributeSet : xml 레이아웃에 태그로 View 객체 추가 / 인플레이션 과정으로 안드로이드 시스템이 자동으로 내부에서 만들어주는 경우 사용
    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();

    }

    //view가 화면에 보여지기 전에 호출되어 Canvas 객체에 먼저 그려지도록 함
    //Canvas : 화면에 직접 그려지게 만들어주는 캔버스 객체
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4.0f);
        paint.setColor(Color.GREEN);
        //각 숫자는 좌표값을 의미
        canvas.drawRect(10, 10, 100, 100, paint);

        paint.setStyle(Paint.Style.FILL);
        //128 : 반투명 / -> 푸른색 계열의 반투명
        paint.setARGB(128, 0, 0, 255);
        canvas.drawRect(120,10,210,100, paint);

        //Dash : 점선
        DashPathEffect dashEffect = new DashPathEffect(new float[] {5,5}, 1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5.0f);
        //점선 효과 넣어줌
        paint.setPathEffect(dashEffect);
        paint.setColor(Color.GREEN);
        canvas.drawRect(120,10,210,100, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.MAGENTA);
        canvas.drawCircle(50, 160, 40, paint);

        paint.setAntiAlias(true);
        canvas.drawCircle(160, 160, 40, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setColor(Color.MAGENTA);
        paint.setTextSize(40.0f);
        canvas.drawText("Text (Stroke)", 20, 260, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(40.0f);
        canvas.drawText("Hello.", 20, 320, paint);

    }

    //view 터치했을 때 호출되는 메소드
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            Toast.makeText(getContext(),"눌렸습니다.", Toast.LENGTH_LONG).show();
        }

        return true;
    }
}
