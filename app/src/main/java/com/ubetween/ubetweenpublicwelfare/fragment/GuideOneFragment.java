package com.ubetween.ubetweenpublicwelfare.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ubetween.ubetweenpublicwelfare.R;
import com.ubetween.ubetweenpublicwelfare.base.GuideBaseFragment;

/**
 * guide第一个fragment
 *
 * @author hadis on 16.3.29.
 */
public class GuideOneFragment extends GuideBaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guideone, null);
        return view;
    }
}
