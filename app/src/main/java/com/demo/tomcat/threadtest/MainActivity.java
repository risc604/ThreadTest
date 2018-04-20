package com.demo.tomcat.threadtest;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG =  MainActivity.class.getSimpleName();

    private static final int UPDATE_TEXT = 1;

    TextView    tvMessage;
    Button      btnChangeText;

    boolean     btnStatus;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case UPDATE_TEXT:
                    tvMessage.setText("Nice to meet you !!");
                    break;

                default:
                    break;
            }
        }
    };

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
        Log.w(TAG, "OnClickChangeText(), ");
        switch (view.getId())
        {
            case R.id.change_text:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.w(TAG, " Thread, Runnable, run(), ");
                        Message msg = new Message();
                        msg.what = UPDATE_TEXT;
                        handler.sendMessage(msg);
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
        Log.w(TAG, " initView(), ");
        tvMessage = findViewById(R.id.textMsg);
        btnChangeText = findViewById(R.id.change_text);

    }

    private void initControl()
    {
        Log.w(TAG, " initControl(), ");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }


}

