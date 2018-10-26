package org.techtown.calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView monthText;
    GridView monthView;

    MonthAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monthText = (TextView) findViewById(R.id.monthText);

        monthView = (GridView) findViewById(R.id.monthView);

        adapter = new MonthAdapter();
        monthView.setAdapter(adapter);

        monthText.setText(adapter.getCurrentYear() + "년" + adapter.getCurrentMonth() + "월");


        Button monthPrevious = (Button) findViewById(R.id.monthPrevious);
        monthPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setPreviousMonth();
                adapter.notifyDataSetChanged();
                monthText.setText(adapter.getCurrentYear() + "년" + adapter.getCurrentMonth() + "월");
            }
        });

        Button monthNext = (Button) findViewById(R.id.monthNext);
        monthNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setNextMonth();
                adapter.notifyDataSetChanged();
                monthText.setText(adapter.getCurrentYear() + "년" + adapter.getCurrentMonth() + "월");
            }
        });


    }

    //어댑터에서 날짜 데이터 관리
    class MonthAdapter extends BaseAdapter {

        MonthItem[] items;
        Calendar calendar;
        int firstDay;
        int lastDay;

        int curYear;
        int curMonth;

        public MonthAdapter() {
            items = new MonthItem[7 * 6];

            //calendar에 현재 시간 설정
            Date date = new Date();
            calendar = Calendar.getInstance();
            calendar.setTime(date);


            recalculate();
            resetDayNumbers();
        }

        public void setPreviousMonth() {
            calendar.add(Calendar.MONTH, -1);
            recalculate();
            resetDayNumbers();
        }

        public void setNextMonth() {
            calendar.add(Calendar.MONTH, 1);
            recalculate();
            resetDayNumbers();
        }

        public int getCurrentYear() {
            return curYear;
        }

        public int getCurrentMonth() {
            return curMonth;
        }

        //시작하는 요일 정보 확인 메소드
        public void recalculate() {
            //당월 1일로 시작 일 설정
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            //해당 주에 몇 번째 일인지 변수 값으로 설정
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            //당월 첫번째 일 확인 가능(column index(result) 값으로)
            firstDay = getFirstDay(dayOfWeek);

            curYear = calendar.get(Calendar.YEAR);
            curMonth = calendar.get(Calendar.MONTH);
            lastDay = getLastDay();

            //그 주의 첫 날을 어느 요일로 할 것인지 정보 조회 가능
//            int firstDayOfWeek = calendar.getFirstDayOfWeek();
//            int startDay = getFirstDayOfWeek(firstDayOfWeek);

        }

        public void resetDayNumbers() {
            for (int i = 0; i < 42; i++) {
                int dayNumber = (i+1) - firstDay;
                if (dayNumber < 1 || dayNumber > lastDay) {
                    dayNumber = 0;
                }

                items[i] = new MonthItem(dayNumber);

            }
        }

        public int getFirstDay(int dayOfWeek) {
            //result = column index
            int result = 0;

            if (dayOfWeek == Calendar.SUNDAY) {
                result = 0;
            } else if (dayOfWeek == Calendar.MONDAY) {
                result = 1;
            } else if (dayOfWeek == Calendar.TUESDAY) {
                result = 2;
            } else if (dayOfWeek == Calendar.WEDNESDAY) {
                result = 3;
            } else if (dayOfWeek == Calendar.THURSDAY) {
                result = 4;
            } else if (dayOfWeek == Calendar.FRIDAY) {
                result = 5;
            } else if (dayOfWeek == Calendar.SATURDAY) {
                result = 6;
            }

            return result;
        }

        //월별 마지막 일 구하는 메소드
        public int getLastDay() {
            switch(curMonth) {
                case 0 :
                case 2 :
                case 4 :
                case 6 :
                case 7 :
                case 9 :
                case 11 :
                    return 31;
                case 3 :
                case 5 :
                case 8 :
                case 10 :
                    return 30;
                default :
                    if (((curYear%4 == 0) && (curYear%100 != 0)) || (curYear%400 == 0)) {
                        return 29;
                    } else {
                        return 28;
                }
            }
        }

        @Override
        public int getCount() {
            return 42;
        }

        @Override
        public Object getItem(int position) {
            return items[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        //어댑터 안에서 리턴해줄 뷰 객체 필요(각각 아이템에 대한 뷰)
        //어댑터 안에서 MonthItemView view 객체로 생성하여 return
        //convertView : 재사용 할 수 있는 뷰
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MonthItemView view = null;
            if (convertView == null) {
                view = new MonthItemView(getApplicationContext());
            } else {
                view = (MonthItemView) convertView;
            }

            view.setDay(items[position].day);

            return view;
        }
    }
}
