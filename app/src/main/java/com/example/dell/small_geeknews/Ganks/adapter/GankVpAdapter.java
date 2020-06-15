package com.example.dell.small_geeknews.Ganks.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by Dell on 2019/4/22.
 */

public class GankVpAdapter extends FragmentStatePagerAdapter{
    private ArrayList<Fragment> mfragments;
    private ArrayList<String> mtitles;

    public GankVpAdapter(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> titles) {
        super(fm);
        mfragments = fragments;
        mtitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mfragments.get(position);
    }

    @Override
    public int getCount() {
        return mfragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mtitles.get(position);
    }
}
