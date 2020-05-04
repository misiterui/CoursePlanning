package com.example.courseplanningapp.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.courseplanningapp.R;
import com.example.courseplanningapp.constants.Constants;

import java.nio.file.WatchEvent;

public class WelcomeActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        this.getWindow().setStatusBarColor(Color.RED);

        handler.sendEmptyMessageDelayed(0,3000);


    }

    //用handler达到延时的效果
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            jumpNextPage();
            super.handleMessage(msg);
        }

        private void getHome() {
            Intent intent = new Intent(WelcomeActivity.this, CourseList.class);
            startActivity(intent);
            finish();
        }
    };

    protected void onDestroy() {
        super.onDestroy();
        //移除handler的引用即可,不移出会造成内存泄漏
        handler.removeCallbacksAndMessages(null);
    }


    private void jumpNextPage() {
        // 判断之前有没有显示过新手引导页
        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        boolean userGuide = sp.getBoolean(Constants.IS_USER_GUIDE_SHOWED_KEY, false);
        System.out.println("这次的userGuide是：" + userGuide);

        if (!userGuide) {
            // 结束后开始一个activity,要new一个新的意图
            startActivity(new Intent(WelcomeActivity.this, ChooseMajorActivity.class));// 传一个自身的context和跳转的对象
        } else {
            startActivity(new Intent(WelcomeActivity.this, CourseList.class));// 传一个自身的context和跳转的对象
        }

        finish();// 跳出去以后就结束当前的页面
    }

}
