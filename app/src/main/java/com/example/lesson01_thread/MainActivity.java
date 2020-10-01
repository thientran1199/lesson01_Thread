package com.example.lesson01_thread;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Handler mHandler;
    int a[];
    final int SIZE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        textView = findViewById(R.id.textView);
//        textView1 = findViewById(R.id.textView1);

        mHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.getData().getString("COMMAND")){
                    case "mangsonguyen":
                        Log.d("mang so nguyen a  ", msg.getData().getInt("A") + "" );
                        break;
                    case "sochan":
                        Log.d("So chan", msg.getData().getInt("A") + "");
                        break;
                    case "sole":
                        Log.d("So le", msg.getData().getInt("A") + "");
                        break;
                    case "chiahetcho5":
                        Log.d("So Chia het cho 5", msg.getData().getInt("B") + "");
                        break;
                }
            }
        };

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayGeneration(SIZE);
                sochan();
                sole();
                tong();
            }
        });
    }

    void arrayGeneration(int size){
        a = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++){
            a[i] = random.nextInt(100);
        }
    }
    void  sochan(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i<SIZE ; i++){
                    if (a[i] % 2 == 0){
                        Message msg = mHandler.obtainMessage();
                        Bundle b = new Bundle();
                        b.putString("COMMAND", "sochan");
                        b.putInt("A", a[i] );
                        msg.setData(b);

                        mHandler.sendMessage(msg);
                    }
                }
            }
        });
        t.start();
//        Log.d("so chan", "chan");
    }
    void  sole(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =0; i<SIZE ; i++){
                    if (a[i] % 2 != 0){
                        Message msg = mHandler.obtainMessage();
                        Bundle b = new Bundle();
                        b.putString("COMMAND", "sole");
                        b.putInt("A", a[i] );
                        msg.setData(b);

                        mHandler.sendMessage(msg);
                    }
                }
            }
        });
        t.start();
//        Log.d("so le", "le");
    }

    void tong(){
        Thread t =new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i < SIZE; i++) {
                    if (a[i] % 5 == 0){
                        Message msg = mHandler.obtainMessage();
                        Bundle b = new Bundle();
                        b.putString("COMMAND", "chiahetcho5");
                        b.putInt("B", a[i] );
                        msg.setData(b);
                        mHandler.sendMessage(msg);
                    }
                }
            }
        });
        t.start();

    }
//    void tang(){
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i=0; i < 10000; i++){
//                    a += 1;
//                    if(a % 5 == 0){
//                        Message msg = mHandler.obtainMessage();
//                        Bundle b = new Bundle();
//                        b.putString("COMMAND", "MODULE_5");
//                        b.putInt("A", a);
//                        msg.setData(b);
//
//                        mHandler.sendMessage(msg);
//                    }
//
//                    if(a % 3 == 0){
//                        Message msg = mHandler.obtainMessage();
//                        Bundle b = new Bundle();
//                        b.putString("COMMAND", "MODULE_3");
//                        b.putInt("A", a);
//                        msg.setData(b);
//
//                        mHandler.sendMessage(msg);
//                    }
//                }
//
//
//                giam(); //callback
//            }
//        });
//        t.start();
//        Log.d("","f");
//    }
//
//    void giam(){
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i=0; i < 10000; i++){
//                    a -= 1;
//                }
//                Log.d("Gia tri a = ", String.valueOf(a));
//            }
//        });
//        t.start();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}