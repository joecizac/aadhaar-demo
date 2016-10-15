package com.jozze.aadhaar_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AadhaarActivity extends AppCompatActivity {

    private EditText aadharEditText;
    private EditText otpEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadhaar);
        Button submit = (Button) findViewById(R.id.submit);
        otpEditText = (EditText) findViewById(R.id.otpEditText);
        aadharEditText = (EditText) findViewById(R.id.aadharEditText);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aadharEditText.getText().toString().length() > 0) {
                    otpEditText.setVisibility(View.VISIBLE);

//                    Intent intent=new Intent(AadhaarActivity.this,HomeScreenActivity.class);
//                    startActivity(intent);
//                    finish();
                }
            }
        });
    }
}
