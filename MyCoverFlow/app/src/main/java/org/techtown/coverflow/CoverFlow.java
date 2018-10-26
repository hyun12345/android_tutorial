package org.techtown.coverflow;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.media.Image;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.ImageView;

//각도 돌려주기 위하여 생성
public class CoverFlow extends Gallery {

    //software camera
    Camera camera = new Camera();

    //회전 최대 각도
    public static int maxRotationAngle = 55;
    //최대 줌인 정도
    public static int maxZoom= -60;

    private int centerPoint;

    public CoverFlow(Context context) {
        super(context);
        init(context);
    }

    public CoverFlow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

        //각 아이템 변형 가능하도록 true 값으로 설정
        setStaticTransformationsEnabled(true);
    }

    //각각 아이템 이미지 들어가면 변형된 상태로 아이템 보여지도록
    @Override
    protected boolean getChildStaticTransformation(View child, Transformation t) {
        int childCenter = child.getLeft() + child.getWidth() / 2;
        int childWidth = child.getWidth();

        int rotationAngle = 0;
        t.clear();
        t.setTransformationType(Transformation.TYPE_MATRIX);

        //아이템 가운데에 있음
        if(childCenter == centerPoint) {
            transformImageBitmap((ImageView) child, t, 0);
        } else {
            rotationAngle = (int) (((float)(centerPoint - childCenter) / childWidth) * maxRotationAngle);
            if(Math.abs(rotationAngle) > maxRotationAngle) {
                rotationAngle = (rotationAngle < 0 )? -maxRotationAngle : maxRotationAngle;
            }
            transformImageBitmap((ImageView) child, t, rotationAngle);
        }
        return true;
    }

    //비트맵 이미지 회전시키는 메소드
    private void transformImageBitmap(ImageView child, Transformation t, int rotationAngle) {
         camera.save();
         Matrix matrix = t.getMatrix();
         int imageHeight = child.getLayoutParams().height;
         int imageWidth = child.getLayoutParams().width;
         int rotation = Math.abs(rotationAngle);

         //앞 뒤로 이동하는 것 조정
         camera.translate(0.0f, 0.0f, 100.0f);

         if(rotation < maxRotationAngle) {
             float zoomAmount = (float) (maxZoom + (rotation * 1.5));
             camera.translate(0.0f, 0.0f, zoomAmount);
         }

         camera.rotateZ(rotationAngle);
         camera.getMatrix(matrix);

         matrix.preTranslate(-(imageWidth/2), -(imageHeight/2));
         matrix.postTranslate((imageWidth/2), (imageHeight/2));

         camera.restore();

    }

    //뷰 크기 변경될 때 호출됨
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //getPadding() 띄어져있는 간격
        //가로방향 중 가운데가 값이 됨
        centerPoint = (getWidth() - getPaddingLeft() - getPaddingRight()) / 2 + getPaddingLeft();
    }
}
