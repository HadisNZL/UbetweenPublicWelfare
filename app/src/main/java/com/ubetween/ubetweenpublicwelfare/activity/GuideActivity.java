package com.ubetween.ubetweenpublicwelfare.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ubetween.ubetweenpublicwelfare.R;
import com.ubetween.ubetweenpublicwelfare.adapter.GuideFragmentAdapter;
import com.ubetween.ubetweenpublicwelfare.base.BaseActivity;
import com.ubetween.ubetweenpublicwelfare.base.GuideBaseFragment;
import com.ubetween.ubetweenpublicwelfare.fragment.GuideOneFragment;
import com.ubetween.ubetweenpublicwelfare.fragment.GuideThreeFragment;
import com.ubetween.ubetweenpublicwelfare.fragment.GuideTwoFragment;
import com.ubetween.ubetweenpublicwelfare.utils.CreateShut;
import com.ubetween.ubetweenpublicwelfare.utils.SharePrefenceUtil;
import com.ubetween.ubetweenpublicwelfare.view.GuideViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * guide activity
 *
 * @author hadis on 16.3.29.
 */
public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private GuideViewPager vPager;
    private List<GuideBaseFragment> list = new ArrayList<>();
    private GuideFragmentAdapter adapter;
    private ImageView[] tips;
    private TextView start_tv;
    // private int currentSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new CreateShut(this);//创建桌面快捷方式
        ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup); //初始化指示点控件
        start_tv = (TextView) findViewById(R.id.start_tv);
        tips = new ImageView[3];
        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(10, 10));
            if (i == 0) {
                imageView.setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                imageView.setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
            tips[i] = imageView;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 20;//设置指示点view的左边距
            layoutParams.rightMargin = 20;//设置指示点view的右边距
            group.addView(imageView, layoutParams);
        }

        //获取自定义viewpager 然后设置背景图片
        vPager = (GuideViewPager) findViewById(R.id.viewpager_launcher);
        vPager.setBackGroud(BitmapFactory.decodeResource(getResources(), R.drawable.guide_bac01));

        /**
         * 初始化三个fragment  并且添加到list中
         */
        GuideOneFragment oneFragment = new GuideOneFragment();
        GuideTwoFragment twoFragment = new GuideTwoFragment();
        GuideThreeFragment threeFragment = new GuideThreeFragment();
        list.add(oneFragment);
        list.add(twoFragment);
        list.add(threeFragment);

        adapter = new GuideFragmentAdapter(getSupportFragmentManager(), list);
        vPager.setAdapter(adapter);
        vPager.setOffscreenPageLimit(2);
        vPager.setCurrentItem(0);
        vPager.setOnPageChangeListener(this);
        start_tv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // SharePrefenceUtil.setFirst(false);//把第一次进入boolean值改为false
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        // overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        this.finish();
    }

    @Override
    public void onPageSelected(int position) {
        if (position == tips.length - 1) {
            start_tv.setVisibility(View.VISIBLE);
        } else {
            start_tv.setVisibility(View.GONE);
        }
        setImageBackground(position);//改变点点点的切换效果
//            GuideBaseFragment fragment = list.get(index);
//            list.get(currentSelect).stopAnimation();//停止前一个页面的动画
//            fragment.startAnimation();//开启当前页面的动画
//            currentSelect = position;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 改变点点点的切换效果
     */
    private void setImageBackground(int selectItems) {
        for (int i = 0; i < tips.length; i++) {
            if (i == selectItems) {
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
        }
    }
}
