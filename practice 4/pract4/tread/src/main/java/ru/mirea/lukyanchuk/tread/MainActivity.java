package ru.mirea.lukyanchuk.tread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int counter = 0;

    int classesCount;
    int daysCount;
    int resultCount;

    TextView result;
    EditText classes;
    EditText day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView infoTextView = findViewById(R.id.textView);
        Thread mainThread = Thread.currentThread();
        infoTextView.setText("Current thread: " + mainThread.getName());

        mainThread.setName("MireaThread");
        infoTextView.append("\n New thread name: " + mainThread.getName());

        result = findViewById(R.id.textView2);

        classes = findViewById(R.id.classes);
        day = findViewById(R.id.day);

    }

    public void onClick(View view) {

        Runnable runnable = new Runnable() {
            public void run() {
                int numberThread = counter++;
                Log.i("ThreadProject", "Index of executed thread: " + numberThread);
                long endTime = System.currentTimeMillis() + 20 * 1000;
                while (System.currentTimeMillis() < endTime) {
                    synchronized (this) {
                        try {
                            wait(endTime - System.currentTimeMillis());
                        } catch (Exception e) { }
                    }
                }
                Log.i("ThreadProject", "Index of finished thread: " + numberThread);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void onClickLessonsPerDay(View view){
        Runnable runnable = new Runnable() {
            public void run() {
                synchronized (this) {
                    try {
                        try{
                            classesCount = Integer.parseInt(classes.getText().toString());
                            daysCount = Integer.parseInt(day.getText().toString());
                        }
                        catch (Exception exception){}
                        resultCount = classesCount / daysCount;
                        result.setText(String.valueOf(resultCount));
                    } catch (Exception e) { }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}