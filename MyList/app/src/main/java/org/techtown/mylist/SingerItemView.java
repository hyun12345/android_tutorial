package org.techtown.mylist;

import android.content.Context;
import android.media.Image;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//뷰가 LinearLayout에 출력되므로 LinearLayout 상속 받음
//뷰 default 생성자는 두 개가 되어야 함(generate로 import)
public class SingerItemView extends LinearLayout {

    ImageView imageView;
    TextView textView;
    TextView textView2;

    public SingerItemView(Context context) {
        super(context);
        init(context);
    }

    public SingerItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    //서비스 : 화면 없이 동작 되는 기능 -> 그 중 LayoutInflater 사용
    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflater 사용함으로써 xml 레이아웃의 텍스트, 이미지 참조 가능
        inflater.inflate(R.layout.singer_items, this, true);

        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
    }

    public void setName(String name) {
        textView.setText(name);
    }

    public void setMobile(String mobile) {
        textView2.setText(mobile);
    }

    public void setImage(int resId) {
        imageView.setImageResource(resId);
    }

}
