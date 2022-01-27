package com.example.mindful;

import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;



public class MainActivity extends AppCompatActivity {

    //hard coded for now
    long millisInFuture = 60000;

    Random r = new Random();

    ImageButton btStartStop;


    TextView txFocus;
    TextView txStartStop;

    CountDownTimer timer;
    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

    boolean timerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
                    modifier *= r.nextInt(20)/10;
                    startTimer((long)modifier);
                }
            }
        });

    }

    protected void startTimer(long modifier){
        startUpdate();
        timer = new CountDownTimer(modifier, 1000) {
            @Override
            public void onTick(long l) {
                //no actions on tick
            }

            @Override
            public void onFinish() {



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