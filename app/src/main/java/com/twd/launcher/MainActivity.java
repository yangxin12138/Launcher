package com.twd.launcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = MainActivity.class.getName();
    private ImageButton bt_youtube;
    private ImageButton bt_google;
    private ImageButton bt_facebook;
    private ImageButton bt_netflix;
    private ImageButton bt_bluetooth;
    private ImageButton bt_usb;
    private ImageButton bt_settings;
    private ImageButton bt_application;

    private TextView tv_time;
    private ImageView iv_wifi;

    private Handler timeHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
        //初始化时间
        updateTimeRunnable.run();
    }

    private void initView(){
        bt_youtube = findViewById(R.id.button_youtube);
        bt_google = findViewById(R.id.button_google);
        bt_facebook = findViewById(R.id.button_facebook);
        bt_netflix = findViewById(R.id.button_netflix);
        bt_bluetooth = findViewById(R.id.button_bt);
        bt_usb = findViewById(R.id.button_usb);
        bt_settings = findViewById(R.id.button_settings);
        bt_application = findViewById(R.id.button_application);
        tv_time = findViewById(R.id.tv_time);
        iv_wifi = findViewById(R.id.iv_wifi);

        bt_youtube.setOnClickListener(this::onClick);
        bt_google.setOnClickListener(this::onClick);
        bt_facebook.setOnClickListener(this::onClick);
        bt_netflix.setOnClickListener(this::onClick);
        bt_bluetooth.setOnClickListener(this::onClick);
        bt_usb.setOnClickListener(this::onClick);
        bt_settings.setOnClickListener(this::onClick);
        bt_application.setOnClickListener(this::onClick);
    }

    private Runnable updateTimeRunnable = new Runnable() {
        @Override
        public void run() {
            //获取当前的时间
            Calendar calendar = Calendar.getInstance();
            Date currentDate = calendar.getTime();

            //设置时间的格式
            DateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String formatter = timeFormat.format(currentDate);

            //在TextView上更新日期
            tv_time.setText(formatter);

            //每隔一秒更新一次
            timeHandler.postDelayed(this,1000);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timeHandler.removeCallbacks(updateTimeRunnable);
    }

    @Override
    public void onClick(View v) {

        //添加点击动画
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale_animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent ;
                if (v.getId() == R.id.button_youtube){
                    Log.i(TAG, "onClick: ---------youtube");
                } else if (v.getId() == R.id.button_google) {
                    Log.i(TAG, "onClick: ---------google");
                } else if (v.getId() == R.id.button_facebook) {
                    Log.i(TAG, "onClick: ---------facebook");
                } else if (v.getId() == R.id.button_netflix) {
                    Log.i(TAG, "onClick: ---------netflix");
                } else if (v.getId() == R.id.button_bt) {
                    Log.i(TAG, "onClick: ----------bluetooth");
                } else if (v.getId() == R.id.button_usb) {
                    Log.i(TAG, "onClick: ----------usb");
                } else if (v.getId() == R.id.button_settings) {
                    Log.i(TAG, "onClick: -----------settings");
                } else if (v.getId() == R.id.button_application) {
                    Log.i(TAG, "onClick: ------------application");
                    intent = new Intent(getApplicationContext(),ApplicationListActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.startAnimation(animation);

    }
}