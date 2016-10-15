package com.jozze.aadhaar_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private Timer tiemr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);

        tiemr = new Timer();
        MyTimerTask timerTask = new MyTimerTask();
        tiemr.schedule(timerTask, 500);
    }
    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            if (tiemr != null)
                tiemr.cancel();
            Intent intent=new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            finish();

        }
    }
}
