package org.techtown.danjiresort;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import java.util.Calendar;
import java.util.Date;

public class DatePicker extends LinearLayout {

    android.widget.DatePicker datePicker;

    OnDateChangeListener listener;

    //필수 생성자 두 개 생성
    public DatePicker(Context context) {
        super(context);
        init(context);
    }

    public DatePicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.picker, this, true);

        datePicker = (android.widget.DatePicker) findViewById(R.id.datePicker);
        //현재 시간 참조
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        //연, 월, 일 정수 변수로 설정
        int curYear = calendar.get(Calendar.YEAR);
        int curMonth = calendar.get(Calendar.MONTH);
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);

        //날짜 선택 위젯 초기화
        //datePikcer.init() : 현재 시간으로 날짜 초기화
        datePicker.init(curYear, curMonth, curDay, new android.widget.DatePicker.OnDateChangedListener() {
            //onDateChanged : 날짜가 변경되었을 때 호출되는 메소드
            //사용자가 날짜를 바꿨을 때 새로 정의한 리스너 OnDateTimeChangeListener에 이벤트 전달
            @Override
            public void onDateChanged(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //리스너의 값이 null이 아닌 경우(시간이 변경되는 이벤트 발생) 새로운 리스너 메소드 호출
                if (listener != null) {
                    listener.onDateChange(DatePicker.this, year, monthOfYear, dayOfMonth);
                }
            }
        });

    }

    public void setOnDateChangeListener(OnDateChangeListener listener) {
        this.listener = listener;
    }

    //수정한 날짜 / 시간 값으로 설정되도록 하는 메소드
    public void updateDate(int year, int monthOfYear, int dayOfMonth) {
        datePicker.updateDate(year, monthOfYear, dayOfMonth);
    }
}
