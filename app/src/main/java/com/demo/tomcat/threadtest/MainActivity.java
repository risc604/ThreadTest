package com.demo.tomcat.threadtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private static final String TAG =  MainActivity.class.getSimpleName();

TextView    tvMessage;
Button      btnChangeText;

boolean     btnStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w(TAG, "onCreate(), ");

        initView();
        initControl();
    }

    public void OnClickChangeText(View view)
    {
        switch (view.getId())
        {
            case R.id.change_text:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        tvMessage.setText("Nice to meet you !!");
                    }
                }).start();
                break;

            default:
                break;
        }
    }


    //-------------------------- User function -----------------------//
    private void initView()
    {
        Log.w(TAG, "initView(), ");
        tvMessage = findViewById(R.id.textMsg);
        btnChangeText = findViewById(R.id.change_text);

    }

    private void initControl()
    {
        Log.w(TAG, "initControl(), ");

    }


}

