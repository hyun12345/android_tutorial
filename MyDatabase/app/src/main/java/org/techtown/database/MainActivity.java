package org.techtown.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;

    EditText editText2;

    EditText editText3;
    EditText editText4;
    EditText editText5;

    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);

        editText2 = (EditText) findViewById(R.id.editText2);

        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String databaseName = editText.getText().toString();
                openDatabase(databaseName);
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tableName = editText2.getText().toString();
                createTable(tableName);
            }
        });

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editText3.getText().toString().trim();
                String ageStr = editText4.getText().toString().trim();
                String mobile = editText5.getText().toString().trim();

                int age = -1;
                try {
                    age = Integer.parseInt(ageStr);
                } catch (Exception e){}

                insertData(name, age, mobile);
            }
        });

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tableName = editText2.getText().toString();
                selectData(tableName);
            }
        });
    }

    public void openDatabase(String databaseName) {
        println("openDatabase() 호출됨.");
        /*
        database = openOrCreateDatabase(databaseName, MODE_PRIVATE, null);

        if(database != null) {
            println("데이터베이스 생성/오픈됨.");
        }
        */

    DatabaseHelper helper = new DatabaseHelper(this, databaseName, null, 3);
    database = helper.getWritableDatabase();

    }

    public void createTable(String tableName) {
        println("createTable() 호출됨.");

        if(database != null) {
            String sql = "create table " + tableName + "(_id integer PRIMARY KEY autoincrement, name text, age integer, mobile, text)";
            database.execSQL(sql);
            println("테이블 생성됨.");
        } else {
            println("먼저 데이터베이스를 오픈하세요.");
        }
    }

    public void insertData(String name, int age, String mobile) {
        println("insertData() 호출됨.");

        if(database != null) {
            String sql = "insert into customer(name, age, mobile) values(?, ?, ?)";
            Object[] parameter = {name, age, mobile};
            database.execSQL(sql, parameter);
            println("데이터 추가함.");
        } else {
            println("먼저 데이터베이스를 오픈하세요.");
        }
    }

    public void selectData(String tableName) {
        println("selectData() 호출됨.");

        if (database != null) {
            String sql = "select name, age, mobile from " + tableName;
            //Cursor = ResultSet과 유사
            Cursor cursor = database.rawQuery(sql, null);
            println("조회된 데이터 개수 : " + cursor.getCount());

            for(int i = 0 ; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                String name = cursor.getString(0);
                int age = cursor.getInt(1);
                String mobile = cursor.getString(2);

                println("#" + i + " -> " + name + ", " + age + ", " + mobile);
            }

            cursor.close();
        }
    }

    public void println(String data) {
        textView.append(data + "\n");
    }

    class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        //기존 테이블 사용하지 않은 경우
        @Override
        public void onCreate(SQLiteDatabase db) {
            println("onCreate() 호출됨.");

            String tableName = "customer";

                String sql = "create table if not exists " + tableName + "(_id integer PRIMARY KEY autoincrement, name text, age integer, mobile, text)";
                db.execSQL(sql);
                println("테이블 생성됨.");
            createTable(tableName);


        }

        //기존 테이블 사용한 경우
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //버전 정보 확인가능하도록 출력문 설정
            println("onUpgrade() 호출됨 : " + oldVersion + ", " + newVersion);
            String tableName = "customer";

                //ver.1 -> ver.2~
                if (newVersion > 1) {
                    db.execSQL("drop table if exists " + tableName);
                    println("테이블 삭제함");
                }
                String sql = "create table if not exists " + tableName + "(_id integer PRIMARY KEY autoincrement, name text, age integer, mobile, text)";
                db.execSQL(sql);
                println("테이블 생성됨.");

        }
    }
}