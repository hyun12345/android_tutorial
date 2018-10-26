package org.techtown.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MonthItemView extends RelativeLayout {

    TextView textView;

    public MonthItemView(Context context) {
        super(context);
        init(context);
    }

    public MonthItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.month_item, this, true);

        textView = (TextView) findViewById(R.id.textView);
    }

    public void setDay(int day) {
        //정수형 문자 변수 day를 문자열로 형변환하여 textView에 대입할 수 있도록 설정
        textView.setText(String.valueOf(day));
    }
}
