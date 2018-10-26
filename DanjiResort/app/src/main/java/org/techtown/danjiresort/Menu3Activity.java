package org.techtown.danjiresort;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Menu3Activity extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;
    Button button4;

    TextView textView;
    DatePicker picker;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu3);

        textView = (TextView) findViewById(R.id.textView);
        picker = (DatePicker) findViewById(R.id.picker);

        picker.setOnDateChangeListener(new OnDateChangeListener() {
            @Override
              public void onDateChange(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //Calendar 객체의 값을 받아와 TextView에 대입되도록 설정(선택한 시간 텍스트뷰에 표시)

                calendar.set(year, monthOfYear, dayOfMonth);

                String curTime = format.format(calendar.getTime());
                textView.setText(curTime);
               }
            });

//        calendar.set(picker.getYear(), picker.getMonth(), picker.getDayOfMonth, picker.getCurrentHour(), picker.getCurrentMinute());
        textView.setText(format.format(calendar.getTime()));

        button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "button2 : Suitroom", Toast.LENGTH_LONG).show();
            }
        });

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "button3 : Doubleroom", Toast.LENGTH_LONG).show();
            }
        });

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "button4 : Singleroom", Toast.LENGTH_LONG).show();
            }
        });
    }

}
