package com.example.mindful;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;



public class MainActivity extends AppCompatActivity {

    //hard coded for now
    long millisInFuture = 6000;

    Random r = new Random();

    ImageButton btStartStop;

    TextView txFocus;
    TextView txStartStop;

    CountDownTimer timer;

    boolean timerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Vibrator vibrate = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        txFocus = findViewById(R.id.txFocus);
        txStartStop = findViewById(R.id.txStart);

        btStartStop = findViewById(R.id.button_start);
        btStartStop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                if(timerRunning){
                    stopTimer();
                }else{
                    //modifier between 1-10
                    double modifier = millisInFuture;
                    modifier *= (double)r.nextInt(20) /10;
                    startTimer((long)modifier, vibrate);
                }
            }
        });

    }

    protected void startTimer(long modifier, Vibrator v){
        startUpdate();
        timerRunning = true;
        new CountDownTimer(modifier, 1000) {
            @Override
            public void onTick(long l) {
                //no actions on tick
            }
            @Override
            public void onFinish() {
                v.vibrate(2000);
                timerRunning = false;
                stopUpdate();
            }
        }.start();
    }

    protected void stopTimer(){
        stopUpdate();
    }

    protected void startUpdate(){
        txFocus.setText("Focusing...");
        txStartStop.setText("Stop");
    }

    protected void stopUpdate(){
        txFocus.setText("Focus");
        txStartStop.setText("Start");
    }
}