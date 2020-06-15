package com.example.dell.small_geeknews.Mainfragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.dell.small_geeknews.Ganks.Fragment.AndroidFragment;
import com.example.dell.small_geeknews.Ganks.Fragment.FuLiFragment;
import com.example.dell.small_geeknews.Ganks.Fragment.IOSFragment;
import com.example.dell.small_geeknews.Ganks.Fragment.AppFragment;
import com.example.dell.small_geeknews.Ganks.adapter.GankVpAdapter;
import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.base.BaseFragment;
import com.example.dell.small_geeknews.presenter.GankP;
import com.example.dell.small_geeknews.view.GankV;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Dell on 2019/4/3.
 */

public class GankFragment extends BaseFragment<GankP, GankV> implements GankV {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;


    @Override
    protected GankP initGeekP() {
        return new GankP();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.gank_fagment;
    }

    @Override
    protected void initView() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new AndroidFragment());
        fragments.add(new IOSFragment());
        fragments.add(new AppFragment());
        fragments.add(new FuLiFragment());

        ArrayList<String> titles = new ArrayList<>();
        titles.add("Android ");
        titles.add("iOS ");
        titles.add("App ");
        titles.add("福利");

        GankVpAdapter adapter = new GankVpAdapter(getChildFragmentManager(),fragments,titles);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);

    }
}
