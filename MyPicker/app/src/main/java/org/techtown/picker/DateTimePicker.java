package org.techtown.picker;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class DateTimePicker extends LinearLayout {

    DatePicker datePicker;
    TimePicker timePicker;
    CheckBox checkTimePicker;

    OnDateTimeChangeListener listener;

    //필수 생성자 두 개 생성
    public DateTimePicker(Context context) {
        super(context);
        init(context);
    }

    public DateTimePicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.picker, this, true);

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        timePicker = (TimePicker) findViewById(R.id.timePicker);

        checkTimePicker = (CheckBox) findViewById(R.id.checkTimePicker);

        //현재 시간 참조
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        //연, 월, 일 정수 변수로 설정
        int curYear = calendar.get(Calendar.YEAR);
        int curMonth = calendar.get(Calendar.MONTH);
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);

        //시간, 분 정수 변수로 설정
        int curHour = calendar.get(Calendar.HOUR_OF_DAY);
        int curMinute = calendar.get(Calendar.MINUTE);

        //날짜 선택 위젯 초기화
        //datePikcer.init() : 현재 시간으로 날짜 초기화
        datePicker.init(curYear, curMonth, curDay, new DatePicker.OnDateChangedListener() {
            //onDateChanged : 날짜가 변경되었을 때 호출되는 메소드
            //사용자가 날짜를 바꿨을 때 새로 정의한 리스너 OnDateTimeChangeListener에 이벤트 전달
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //리스너의 값이 null이 아닌 경우(시간이 변경되는 이벤트 발생) 새로운 리스너 메소드 호출
                if (listener != null) {
                    listener.onDateTimeChange(DateTimePicker.this, year, monthOfYear, dayOfMonth, timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                }
            }
        });

        //현재 시, 분으로 시간 설정
        timePicker.setCurrentHour(curHour);
        timePicker.setCurrentMinute(curMinute);

        //시간 선택 위젯 이번트 처리
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                //리스너의 값이 null이 아닌 경우(시간이 변경되는 이벤트 발생) 새로운 리스너 메소드 호출
                if (listener != null) {
                    listener.onDateTimeChange(DateTimePicker.this, datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), hourOfDay, minute);
                }
            }
        });

        //체크박스 이벤트 처리
        checkTimePicker.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //체크박스 체크되면 isChecked == true
                timePicker.setEnabled(isChecked);
                //체크박스 체크되어있으면 보이도록, 그렇지 않으면 보이지 않도록 Visibility 설정
                timePicker.setVisibility(checkTimePicker.isChecked()? View.VISIBLE : View.INVISIBLE);
            }
        });
    }

    public void setOnDateTimeChangeListener(OnDateTimeChangeListener listener) {
        this.listener = listener;
    }

    //수정한 날짜 / 시간 값으로 설정되도록 하는 메소드
    public void updateDateTime(int year, int monthOfYear, int dayOfMonth, int hour, int minute) {
        datePicker.updateDate(year, monthOfYear, dayOfMonth);

        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);
    }
}
