package org.techtown.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

    Bitmap bitmap;

    Bitmap mBitmap;
    Canvas mCanvas;

    public MyView(Context context) {
        super(context);
        init(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    //이미지 로딩
    private void init(Context context) {

        //비트맵 객체 생성하도록 함
        //drawable에서 star 리소스 가져와 비트맵 객체 생성
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.star);

    }

    //뷰 크기 바뀔 때 자동으로 호출하는 메소드
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //메모리에 생성된 비트맵 객체
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        //캔버스 객체 생성하여 비트맵 설정할 수 있도록 함
        mCanvas = new Canvas();
        mCanvas.setBitmap(mBitmap);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        mCanvas.drawLine(100, 100, 400, 200, paint);
        mCanvas.drawBitmap(bitmap, 0, 0, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap, 0, 0, null);

    }
}
