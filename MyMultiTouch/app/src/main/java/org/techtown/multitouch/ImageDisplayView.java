package org.techtown.multitouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ImageDisplayView extends View {
    Paint paint;
    Matrix matrix;

    public ImageDisplayView(Context context) {
        super(context);
        init(context);
    }

    public ImageDisplayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        //그래픽 처리(그래픽 처리 : 페인트 / 이미지 확대*축소 : 매트릭스)
        paint = new Paint();
        matrix = new Matrix();
    }

    //레이아웃에 따라 크기 / 뷰 결정됨
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //메모리에 하나 더 만드는 이유 : 더블 버퍼링(미리 만들어 두고 메모리에서 그려준 정보 화면에 출력)
        //보다 효율적으로 동작할 수 있도록 함

    }

    //터치했을 때 비율 계산하여 확대 / 축소 기능에 활용되는 비율 계산
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    //메모리에 그려진 비트맵 이미지 그려짐
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
