package org.techtown.view3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
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
    int deviceWidth;
    int deviceHeight;

    ShapeDrawable drawable;

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
        paint.setColor(Color.RED);

        //단말 화면 속성
        WindowManager manager = (WindowManager) context.getSystemService((Context.WINDOW_SERVICE));
        Display display = manager.getDefaultDisplay();
        //단말 화면 가로 / 세로 구함
        deviceWidth = display.getWidth();
        deviceHeight = display.getHeight();

        //setShape() : 모양 설정 가능
        drawable  = new ShapeDrawable();
        RectShape rect = new RectShape();
        rect.resize(deviceWidth, deviceHeight);
        drawable.setShape(rect);
        drawable.setBounds(0, 0, deviceWidth, deviceHeight);

        //그라데이션 설정
        LinearGradient gradient = new LinearGradient(0, 0, 0, deviceHeight, Color.BLUE, Color.YELLOW, Shader.TileMode.CLAMP);
        Paint curPaint = drawable.getPaint();
        curPaint.setShader(gradient);

    }

    //view가 화면에 보여지기 전에 호출되어 Canvas 객체에 먼저 그려지도록 함
    //Canvas : 화면에 직접 그려지게 만들어주는 캔버스 객체
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //객체 단위로 관리하기 위하여 draw 활용
        drawable.draw(canvas);

        Paint pathPaint = new Paint();
        pathPaint.setAntiAlias(true);
        pathPaint.setColor(Color.MAGENTA);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeWidth(16.0f);
        pathPaint.setStrokeCap(Paint.Cap.BUTT);
        pathPaint.setStrokeJoin(Paint.Join.MITER);

        Path path = new Path();

        path.moveTo(20, 20);
        path.lineTo(120, 20);
        path.lineTo(160, 90);
        path.lineTo(180, 80);
        path.lineTo(200, 120);

        canvas.drawPath(path, pathPaint);
        pathPaint.setColor(Color.WHITE);
        pathPaint.setStrokeCap(Paint.Cap.ROUND);
        pathPaint.setStrokeJoin(Paint.Join.ROUND);

        path.offset(30,120);
        canvas.drawPath(path, pathPaint);

        pathPaint.setColor(Color.GREEN);
        pathPaint.setStrokeCap(Paint.Cap.SQUARE);
        pathPaint.setStrokeJoin(Paint.Join.BEVEL);

        path.offset(30, 120);
        canvas.drawPath(path, pathPaint);



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
