package ru.mirea.lukyanchuk.data_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = findViewById(R.id.textView666);
        final Runnable runn1 = new Runnable() {
            public void run() {
                tvInfo.setText("runn1");
            }
        };
        final Runnable runn2 = new Runnable() {
            public void run() {
                tvInfo.setText("runn2");
            }
        };
        final Runnable runn3 = new Runnable() {
            public void run() {
                tvInfo.setText("runn3");
            }
        };
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    /*Запускает определённое действие потока пользовательского интерфейса.
                     * Оно выполняются сразу же, если он также является текущим потоком, иначе
                     * добавляется в очередь событий
                     */
                    TimeUnit.SECONDS.sleep(1);
                    tvInfo.postDelayed(runn3, 2000);
                    /*Добавляет интерфейс Runnable в очередь сообщений, чтобы запустить
                     * его после спустя какое-то время на том потоке, к которому он прикреплён.
                     * Время кбездействия добавляет дополнительную задержку*/
                    tvInfo.post(runn2);
                    /*Добавляется интерфейс Runnable к очереди сообщений, будет запущен в
                     * том потоке, к которому он прикреплён*/
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}