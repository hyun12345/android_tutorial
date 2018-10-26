package org.techtown.socket;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=(TextView) findViewById(R.id.textView);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientThread thread = new ClientThread();
                thread.start();
            }
        });
    }

    class ClientThread extends Thread {
        public void run() {
            String host = "localhost";
            int port = 5020;
            try {
                Socket socket = new Socket(host, port);

                ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
                outstream.writeObject("Hello!");
                outstream.flush();
                Log.d("ClientThread", "서버로 보냄.");

                ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());
                final Object input = instream.readObject();
                Log.d("ClientThread", "받은 데이터 : " + input);


                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("받은 데이터 : " + input);
                    }
                });
                //핸들러 필요
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
