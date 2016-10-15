package com.jozze.aadhaar_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class HomeScreenActivity extends AppCompatActivity {

    private RelativeLayout setImage;
    private LinearLayout meLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home_screen);
        setImage = (RelativeLayout) findViewById(R.id.setImage);
        meLayout = (LinearLayout) findViewById(R.id.meLayout);
        LinearLayout ll_home = (LinearLayout) findViewById(R.id.ll_home);
        LinearLayout ll_doctor = (LinearLayout) findViewById(R.id.ll_doctor);
        LinearLayout ll_social = (LinearLayout) findViewById(R.id.ll_social);
        LinearLayout ll_me = (LinearLayout) findViewById(R.id.ll_me);


        ll_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meLayout.setVisibility(View.GONE);
                setImage.setVisibility(View.VISIBLE);
                setImage.setBackgroundResource(R.drawable.home_home);
            }
        });
        ll_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meLayout.setVisibility(View.GONE);
                setImage.setVisibility(View.VISIBLE);
                setImage.setBackgroundResource(R.drawable.health_home);
            }
        });
        ll_social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meLayout.setVisibility(View.GONE);
                setImage.setVisibility(View.VISIBLE);
                setImage.setBackgroundResource(R.drawable.social_home_s);
            }
        });
        ll_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meLayout.setVisibility(View.VISIBLE);
                setImage.setVisibility(View.GONE);
            }
        });

    }


}
