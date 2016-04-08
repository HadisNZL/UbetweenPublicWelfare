package com.ubetween.ubetweenpublicwelfare.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ubetween.ubetweenpublicwelfare.base.GuideBaseFragment;

import java.util.List;

/**
 * Viewpager适配器
 *
 * @author hadis
 */
public class GuideFragmentAdapter extends FragmentStatePagerAdapter {
    private List<GuideBaseFragment> list;

    public GuideFragmentAdapter(FragmentManager fm, List<GuideBaseFragment> list) {
        super(fm);
        this.list = list;
    }

    public GuideFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
