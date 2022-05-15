package ru.mirea.lukyanchuk.looper;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.util.concurrent.ThreadLocalRandom;

public class MyLooper extends Thread{
    private int number = 0;
    Handler handler;
    int timeSleep = ThreadLocalRandom.current().nextInt(1, 100);

    @SuppressLint("HandlerLeak")
    @Override
    public void run() {
        Log.d("MyLooper", "run");
        Looper.prepare();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                timeSleep = ThreadLocalRandom.current().nextInt(1, 100);
                int age = msg.getData().getInt("AGE");
                String work = msg.getData().getString("OCC");
                try {
                    Thread.sleep(age);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("MyLooper", "age" + ": " + age);
                Log.d("MyLooper", "Work" + ": "+work);
                number++;
            }
        };
        Looper.loop();
    }
}

