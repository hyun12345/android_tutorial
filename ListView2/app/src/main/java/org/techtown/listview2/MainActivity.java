package org.techtown.listview2;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

    String[] items = {"Ohno", "Sakurai", "Aiba", "Ninomiya", "Matsumoto"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListAdapter(new ArrayAdapter<String>)(this, android.R.layout.simple_list_item_1, items));

protected void onListItem(ListView l, View v, int position, long id) {
    super.onListItemClick(l, v, position, id)l
        }

    }
}
