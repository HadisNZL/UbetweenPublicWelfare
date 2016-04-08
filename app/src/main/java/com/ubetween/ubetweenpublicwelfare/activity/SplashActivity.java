package com.ubetween.ubetweenpublicwelfare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ubetween.ubetweenpublicwelfare.R;
import com.ubetween.ubetweenpublicwelfare.base.BaseActivity;
import com.ubetween.ubetweenpublicwelfare.utils.SharePrefenceUtil;

/**
 * 开屏页
 *
 * @author hadis on 16.4.5.
 */
public class SplashActivity extends BaseActivity {
    private boolean first;
    private static int TIME = 1500; // 进入主程序的延迟时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onResume() {
        into(); // 进入主程序的方法
        super.onResume();
    }

    private void into() {
        first = SharePrefenceUtil.getFirst(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (first) {
                    startActivity(new Intent(getApplicationContext(), GuideActivity.class));
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                finish();
            }
        }, TIME);
    }
}
