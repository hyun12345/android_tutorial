package org.techtown.paintboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PaintBoard extends View {

    Paint paint;

    Bitmap mBitmap;
    Canvas mCanvas;

    int oldX;
    int oldY;


    public PaintBoard(Context context) {
        super(context);
        init(context);
    }

    public PaintBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    //필요한 초기화 작업 실행
    private void init(Context context) {

        paint = new Paint();
        paint.setColor(Color.MAGENTA);
    }

    public void setColor(int color) {
        paint.setColor(color);
    }

    public void setLineWidth(float lineWidth) {
        paint.setStrokeWidth(lineWidth);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        mCanvas = new Canvas();
        mCanvas.setBitmap(mBitmap);

        mCanvas.drawColor(Color.LTGRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int curX = (int) event.getX();
        int curY  = (int) event.getY();

        //손가락 눌렀을 때
        if (action == MotionEvent.ACTION_DOWN) {
            oldX = curX;
            oldY = curY;

            //손가락 누른 채로 움직였을 때
        } else if (action == MotionEvent.ACTION_MOVE) {
            if(oldX > 0 || oldY > 0) {
                mCanvas.drawLine(oldX, oldY, curX, curY, paint);
            }
            //손가락 움직이는동안 이전 좌표, 현재 좌표 계속 호출됨
                oldX = curX;
                oldY = curY;
            //손가락 뗐을 때
        } else if (action == MotionEvent.ACTION_UP) {

        }
        //invalidate() : 다시 그려짐(그린 값 무효됨)
        invalidate();
        return true;
    }
}
