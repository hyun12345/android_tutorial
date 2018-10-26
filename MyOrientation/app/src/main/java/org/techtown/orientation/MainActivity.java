package org.techtown.orientation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //입력상자 객체 editText 생성
    EditText editText;
    //입력상자에 입력한 값 변수로 받기 위해 문자열 변수 name 생성
    String name;

    //onCreate() 메소드에 대입하는 Bundle 객체 == onSaveInstanceState() 메소드에 대입하는 Bundle 객체
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "onCreate() 호출됨", Toast.LENGTH_LONG).show();

        editText = (EditText) findViewById(R.id.editText);

        Button button = (Button) findViewById(R.id.button);
        //가로 화면의 경우 버튼을 생성하지 않았기 때문에 null값이 됨 / 따라서 null이 아닐 경우를 if 조건으로 처리해야 함
        if(button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = editText.getText().toString();
                    //this 참조 불가능 / new로 생성한 객체는 별도의 유효 범위 있기 때문에 / getApplicationContext()로 Context 객체 참조
                    Toast.makeText(getApplicationContext(), "입력한 값을 name 변수에 할당함", Toast.LENGTH_LONG).show();
                }
            });
        }

        //저장한 data 복원하는 상태
        if(savedInstanceState != null) {
            String name = savedInstanceState.getString("name");
            //layout-land 폴더의 editText의 ID를 layout 폴더의 editText ID와 동일하게 설정
            if(editText != null) {
                editText.setText("복원된 이름 : " + name);
                Toast.makeText(getApplicationContext(), "이름이 복원됨 : " + name, Toast.LENGTH_LONG).show();
            }
        }
    }

    //onSaveInstanceState() 메소드에 대입하는 Bundle 객체 == onCreate() 메소드에 대입하는 Bundle 객체
    //data 날아가지 않게 저장하는 상태
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("name", name);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(this, "onStart() 호출됨", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(this, "onStop() 호출됨", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "onDestroy() 호출됨", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(this, "onPause() 호출됨", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(this, "onResume() 호출됨", Toast.LENGTH_LONG).show();
    }
}
