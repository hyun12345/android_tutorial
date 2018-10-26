package org.techtown.button;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

//필수 생성자 두 개 import 해 줌
public class BitmapButton extends AppCompatButton {

    public BitmapButton(Context context) {
        super(context);

        init(context);
    }

    public BitmapButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        setBackgroundResource(R.drawable.bitmap_button_normal);
        //자바 소스코드에서는 픽셀 사이즈로 설정됨
        //res/values/dimens.xml 파일 생성한 후 dp 값으로 설정하면 자동 인코딩 가능
        //getResources() : res() 폴더에서 가져옴
        float textSize = getResources().getDimension(R.dimen.text_size);
        setTextSize(textSize);
        setTextColor(Color.WHITE);
    }

    //버튼 터치했을 때 호출되는 메소드
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch(action) {
            //버튼 클릭하고 있을 때 버튼 기능
            case MotionEvent.ACTION_DOWN :
                setBackgroundResource(R.drawable.bitmap_button_clicked);
                break;

             //버튼 클릭하고 있지 않을 때 버튼 기능
            case MotionEvent.ACTION_UP :
                setBackgroundResource(R.drawable.bitmap_button_normal);
                break;
        }

        //이미지 바뀐 거 다시 그려오는 메소드
        invalidate();
        return true;
    }

}
