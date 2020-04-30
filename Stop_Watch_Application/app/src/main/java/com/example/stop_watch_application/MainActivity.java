package com.example.stop_watch_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Handler timerHandler;
    private ArrayAdapter<String> itemsAdapter;
    private TextView txtTimer;
    private Button btnStartPause, btnLapReset;

    private long milliSecondTime, startTime, pausedTime, updateTime = 0;

    private int seconds, minutes, milliSeconds;

    private boolean stopWatchStarted, stopWatchPaused = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //btnLapReset = findViewById(R.id.btn_lap_reset);

        ListView lvLaps;

        timerHandler = new Handler();

        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        txtTimer = findViewById(R.id.txt_timer);
        btnStartPause = findViewById(R.id.btn_start_pause);
        btnLapReset = findViewById(R.id.btn_lap_reset);
        lvLaps = findViewById(R.id.lvLaps);
        lvLaps.setAdapter(itemsAdapter);
        btnStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!stopWatchStarted || stopWatchPaused)
                {
                    stopWatchStarted = true;
                    stopWatchPaused = false;

                    startTime = SystemClock.uptimeMillis();

                    timerHandler.postDelayed(timerRunnable, 0);

                    btnStartPause.setText(R.string.lblPause);
                    btnLapReset.setText(R.string.btnLap);
                }
                else
                {
                    pausedTime += milliSecondTime;
                    stopWatchPaused = true;

                    timerHandler.removeCallbacks(timerRunnable);

                    btnStartPause.setText(R.string.lblStart);
                    btnLapReset.setText(R.string.lblReset);
                }
            }
        });

        btnLapReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(stopWatchStarted && !stopWatchPaused)
                {
                    String lapTime = minutes + ":" + String.format("%02d", seconds) + String.format("%03d", milliSeconds);
                    itemsAdapter.add(lapTime);
                }
                else if(stopWatchStarted)
                {
                    stopWatchPaused = false;
                    stopWatchStarted = false;

                    timerHandler.removeCallbacks(timerRunnable);

                    milliSeconds = 0;
                    seconds = 0;
                    minutes = 0;
                    startTime = 0;
                    pausedTime = 0;
                    updateTime = 0;
                    milliSecondTime = 0;

                    txtTimer.setText(R.string.lblTImer);
                    btnLapReset.setText(R.string.btnLap);

                    itemsAdapter.clear();
                }

                else
                {
                    Toast.makeText(getApplicationContext(), "Timer hasn't started yet!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            milliSecondTime = SystemClock.uptimeMillis() - startTime;

            updateTime = pausedTime + milliSecondTime;
            milliSeconds = (int) (updateTime % 1000);
            seconds = (int) (updateTime/1000);
            String updatedTime  = minutes + ":" + String.format("%02d", seconds) + ":" + String.format("%03d", milliSeconds);

            txtTimer.setText(updatedTime);
            timerHandler.postDelayed(this, 0);


        }
    };
}
