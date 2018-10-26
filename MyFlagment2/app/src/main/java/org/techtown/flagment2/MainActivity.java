package org.techtown.flagment2;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ListFragment fragment1;
    ViewerFragment fragment2;

    //fragment는 fragmentmanager 필요
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        fragment1 = new ListFragment();
//        fragment2 = new ViewerFragment();

        manager = getSupportFragmentManager();

        //각 클래스로 객체 형변환하여 id 값 찾아 객체 값으로 설정
        fragment1 = (ListFragment) manager.findFragmentById(R.id.listFragment);
        fragment2 = (ViewerFragment) manager.findFragmentById(R.id.viewerFragment);
    }

    public void onImageChange(int index) {
        //ViewFragment 클래스에서 setImage()메소드 정의하였기 때문에 그대로 호출하여 사용 가능
        fragment2.setImage(index);
    }
}
