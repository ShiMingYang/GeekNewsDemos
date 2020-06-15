package com.example.dell.small_geeknews.V2EX.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dell.small_geeknews.V2EX.adapter.bean.TabsBean;

import java.util.ArrayList;

/**
 * Created by Dell on 2019/4/21.
 */

public class VpAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> mfragments;
    private ArrayList<TabsBean> mtabsBeans;

    public VpAdapter(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<TabsBean> tabsBeans) {
        super(fm);
        mfragments = fragments;
        mtabsBeans = tabsBeans;
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
        return mtabsBeans.get(position).tab;
    }
}
