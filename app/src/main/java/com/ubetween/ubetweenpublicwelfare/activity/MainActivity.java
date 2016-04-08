package com.ubetween.ubetweenpublicwelfare.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.ubetween.ubetweenpublicwelfare.R;
import com.ubetween.ubetweenpublicwelfare.base.BaseActivity;
import com.ubetween.ubetweenpublicwelfare.base.BaseApplication;
import com.ubetween.ubetweenpublicwelfare.utils.AppManager;

public class MainActivity extends BaseActivity {

    private long oneTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 点击两次退出程序
     */
    @Override
    public void onBackPressed() {
        //  super.onBackPressed();
        long twoTime = System.currentTimeMillis();
        if (twoTime - oneTime > 2000) {
            Toast.makeText(getApplication(), R.string.exit_tips, Toast.LENGTH_SHORT)
                    .show();
            oneTime = twoTime;
        } else {
            AppManager.getAppManager().AppExit(BaseApplication.getInstance());
        }
    }

}
