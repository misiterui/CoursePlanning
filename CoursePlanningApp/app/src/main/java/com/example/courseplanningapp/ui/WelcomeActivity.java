package com.example.courseplanningapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.courseplanningapp.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        handler.sendEmptyMessageDelayed(0,3000);

    }

    //用handler达到延时的效果
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            getHome();
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
}
