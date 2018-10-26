//package org.techtown.danjiresort;
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//public class Menu1Activity extends AppCompatActivity {
//
//    Button button1;
//    Button button2;
//    Button button3;
//    Button button4;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu1);
//
//        button1 = (Button) findViewById(R.id.button);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
//            }
//        });
//        button2 = (Button) findViewById(R.id.button2);
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "button2 : Suitroom", Toast.LENGTH_LONG).show();
//            }
//        });
//        button3 = (Button) findViewById(R.id.button3);
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "button3 : Doubleroom", Toast.LENGTH_LONG).show();
//            }
//        });
//        button4 = (Button) findViewById(R.id.button4);
//        button4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "button4 : Singleroom", Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//}
