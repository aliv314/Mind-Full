package com.example.mindful;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.os.Vibrator;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import java.util.Timer;


public class MainActivity extends AppCompatActivity {
    int beforeColor;

    boolean timerRunning = false;
    long millisInFuture = 4500;

    LinearLayout lyBackground;
    TextView txFocus;
    TextView txStartStop;
    ImageButton btStartStop;


    CountDownTimer timer;
    Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Vibrator shakey = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        //initialize main activity elements
        txFocus = findViewById(R.id.txFocus);
        txStartStop = findViewById(R.id.txStart);
        btStartStop = findViewById(R.id.button_start);
        lyBackground = findViewById(R.id.parent);

        btStartStop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                if(timerRunning){
                    stopTimer(shakey);
                    timer.cancel();
                }else{
                    //time between 1-10
                    double time = millisInFuture;
                    time *= (double)r.nextInt(20) /10;
                    startTimer((long)time, shakey);
                }
            }
        });

    }

    protected void startTimer(long time, Vibrator v){
        txFocus.setText("Focusing...");
        txStartStop.setText("Stop");
        btStartStop.setColorFilter(Color.parseColor("#EF476f"));

        timerRunning = true;
        timer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long l) {
                //no actions on tick
            }
            @Override
            public void onFinish() {
                v.vibrate(1500);
                timerRunning = false;
                stopTimer(v);
            }
        }.start();
    }

    protected void stopTimer(Vibrator v){
        timerRunning = false;
        txFocus.setText("Focus");
        txStartStop.setText("Start");
        btStartStop.setColorFilter(Color.parseColor("#06D6A0"));
    }
/*
    protected void fadeTo(int color, long time){
        //get layout color as a drawable
        //drawable converted to a colordrawable
        //colordrawable converted to int through get color

        beforeColor= ((ColorDrawable)lyBackground.getBackground()).getColor();
        ObjectAnimator backgroundChange = ObjectAnimator.ofObject(lyBackground, "backgroundColor", new ArgbEvaluator(), beforeColor, color);
        backgroundChange.setDuration(time);
        backgroundChange.start();
    }
*/
}