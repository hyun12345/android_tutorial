package org.techtown.optionmenu;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ActionBar abar = getSupportActionBar();
//        abar.hide();
    }

    //OptionsMenu : 안드로이드 기본 메뉴
    //메뉴 화면에 출력하도록 하는 메소드
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    //옵션 메뉴가 선택됐을 때 호출되는 메소드 / 메뉴아이템이 클릭됐을 때 호출되는 메소든
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int curId = item.getItemId();
        switch(curId) {
            case R.id.menu_refresh :
                Toast.makeText(this, "새로고침 메뉴 클릭됨.", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_search :
                Toast.makeText(this, "검색 메뉴 클릭됨.", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_settings :
                Toast.makeText(this, "설정 메뉴 클릭됨.", Toast.LENGTH_LONG).show();
                break;
            default :
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
