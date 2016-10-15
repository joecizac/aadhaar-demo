package com.jozze.aadhaar_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jozze.aadhaar_demo.models.KYC;


public class HomeScreenActivity extends AppCompatActivity {

    private RelativeLayout setImage;
    private LinearLayout meLayout;
    private ImageView iv_profile;
    private TextView tv_name;
    private TextView tv_dob;
    private TextView tv_gender;
    private TextView tv_add1;
    private LinearLayout ll_home;
    private LinearLayout ll_doctor;
    private LinearLayout ll_social;
    private LinearLayout ll_me;
    private TextView tv_pincode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home_screen);
        initViews();


        onclickListners();

    }

    private void onclickListners() {
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

    private void initViews() {
        iv_profile = (ImageView) findViewById(R.id.iv_profile);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_pincode = (TextView) findViewById(R.id.tv_pincode);
        tv_dob = (TextView) findViewById(R.id.tv_dob);
        tv_gender = (TextView) findViewById(R.id.tv_gender);
        tv_add1 = (TextView) findViewById(R.id.tv_add1);
        setImage = (RelativeLayout) findViewById(R.id.setImage);
        meLayout = (LinearLayout) findViewById(R.id.meLayout);
        ll_home = (LinearLayout) findViewById(R.id.ll_home);
        ll_doctor = (LinearLayout) findViewById(R.id.ll_doctor);
        ll_social = (LinearLayout) findViewById(R.id.ll_social);
        ll_me = (LinearLayout) findViewById(R.id.ll_me);

        KYC kyc = new Gson().fromJson(getIntent().getStringExtra("Data"), KYC.class);
        tv_name.setText(kyc.getKyc().getPersonal().getName());
        tv_dob.setText(kyc.getKyc().getPersonal().getBirthDate());
        tv_gender.setText(kyc.getKyc().getPersonal().getGender());
        tv_pincode.setText(kyc.getKyc().getAddress().getPincode());
        String s="";
        if (kyc.getKyc().getAddress().getCareOf()!=null)
        s= s+kyc.getKyc().getAddress().getCareOf() + ", " + "\n";

        if (kyc.getKyc().getAddress().getArea()!=null)
            s=s+ kyc.getKyc().getAddress().getArea() + ", " + "\n";
        if (kyc.getKyc().getAddress().getStreet()!=null)
            s= s+kyc.getKyc().getAddress().getStreet() + ", " + "\n";
        if (kyc.getKyc().getAddress().getPostOffice()!=null)
            s= s+kyc.getKyc().getAddress().getPostOffice() + ", " + "\n";
        if (kyc.getKyc().getAddress().getDistrict()!=null)
            s= s+kyc.getKyc().getAddress().getDistrict() + ", " + "\n";
        if (kyc.getKyc().getAddress().getState()!=null)
            s= s+kyc.getKyc().getAddress().getState() ;

        tv_add1.setText(s);

        String imageString = kyc.getKyc().getImageByteArray().replaceAll("\r", "").replaceAll("\n", "");
        byte[] imageByteArray = Base64.decode(imageString, Base64.DEFAULT);
        Glide.with(HomeScreenActivity.this).load(imageByteArray).asBitmap().placeholder(R.drawable.logo).into(iv_profile);
    }


}
