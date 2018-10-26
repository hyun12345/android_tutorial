package org.techtown.mylist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SingerAdapter adapter;

    EditText editText;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);


        ListView listView = (ListView) findViewById(R.id.listView);

        //어댑터 사용할 수 있도록 어댑터 객체 생성 / 어댑터 안에서 관리할 데이터 생성
        adapter = new SingerAdapter();
        adapter.addItem(new SingerItem(R.drawable.love, "Ohno", "010-111-1111"));
        adapter.addItem(new SingerItem(R.drawable.free, "Sakurai", "010-222-2222"));
        adapter.addItem(new SingerItem(R.drawable.cloud, "Aiba", "010-333-3333"));
        adapter.addItem(new SingerItem(R.drawable.puzzle, "Ninomiya", "010-444-4444"));
        adapter.addItem(new SingerItem(R.drawable.star, "Matsumoto", "010-555-5555"));

        //리스트뷰를 어댑터의 값을 가져오도록 설정 / 리스트뷰 : 껍데기(화면에 출력만 함)
        listView.setAdapter(adapter);

        //position=index
        //리스트뷰에서 한 아이템 선택했을 때 동작 설정
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SingerItem item = (SingerItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "Selected : " + item.getName(), Toast.LENGTH_LONG).show();
            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                String mobile = editText2.getText().toString();

                adapter.addItem(new SingerItem(R.drawable.home, name, mobile));
                //갱신한 어뎁터의 데이터를 리스트뷰에서도 갱신할 수 있도록 함
                adapter.notifyDataSetChanged();

            }
        });
    }

    //어댑터 클래스 정의(일반적으로 BaseAdapter 상속)
    class SingerAdapter extends BaseAdapter {

        //items 배열 생성 시 SingerItem 클래스 배열 생성되도록 함(테이블과 유사하다고 생각하면 됨)
        ArrayList<SingerItem> items = new ArrayList<SingerItem>();

        //items 개수
        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(SingerItem item) {
            items.add(item);
        }

        //index : position
        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        //레이아웃으로 구성되어야(부분화면) 배열 내용, 사진 등 한꺼번에 출력 가능
        //SingerItemView 클래스 있어야함
        //convertView : 코드 재사용할 수 있게 함
        // 데이터가 100, 1000 등등 많은 경우 모두 new 통해 생성하면 비효율적이므로 convertView 사용
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SingerItemView view = new SingerItemView(getApplicationContext());;

//            SingerItemView view = null;
//            //재사용할 코드가 없는 경우에만 새로 생성해서 할당
//            if (convertView == null) {
//                new SingerItemView(getApplicationContext());
//            //재사용할 코드가 있는 경우 SingerItemView로 형변환만 하여 코드 재사용할 수 있도록 설정
//            } else { b
//                view = (SingerItemView) convertView;
//            }

            SingerItem item = items.get(position);

            view.setImage(item.getResId());
            view.setName(item.getName());
            view.setMobile(item.getMobile());

            return view;
        }
    }
}
