package com.example.dell.small_geeknews.Mainfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.V2EX.adapter.JieDianDaoHActivity;
import com.example.dell.small_geeknews.V2EX.adapter.VpAdapter;
import com.example.dell.small_geeknews.V2EX.adapter.bean.TabsBean;
import com.example.dell.small_geeknews.V2EX.adapter.fragment.V2exListFragment;
import com.example.dell.small_geeknews.base.BaseFragment;
import com.example.dell.small_geeknews.presenter.V2exP;
import com.example.dell.small_geeknews.view.V2exV;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Dell on 2019/4/3.
 */

public class V2ExFragment extends BaseFragment<V2exP, V2exV> implements V2exV {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.pic)
    ImageView pic;
    //石明洋  1808B

    @Override
    protected V2exP initGeekP() {
        return new V2exP();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.v2ex_fagment;
    }

    String mUrl = "https://www.v2ex.com/";

    @Override
    protected void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(mUrl).get();
                    //查找id是Tabs的div元素，因为数据显示只有一个，所以直接get调用
                    Element Tabs = doc.select("div#Tabs").first();
                    //查找带有href属性的a元素
                    Elements AllTabs = Tabs.select("a[href]");
                    final ArrayList<TabsBean> tabsBeans = new ArrayList<>();
                    for (Element elements : AllTabs) {
                        String href = elements.attr("href");
                        String tab = elements.text();
                        TabsBean bean = new TabsBean(href, tab);
                        tabsBeans.add(bean);

                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<Fragment> fragments = new ArrayList<>();
                            for (int i = 0; i < tabsBeans.size(); i++) {
                                V2exListFragment v2exListFragment = new V2exListFragment();
                                Bundle bundle = new Bundle();
                                bundle.putString("tab", tabsBeans.get(i).Link);
                                v2exListFragment.setArguments(bundle);
                                fragments.add(v2exListFragment);
                            }

                            VpAdapter vpAdapter = new VpAdapter(getChildFragmentManager(), fragments, tabsBeans);
                            vp.setAdapter(vpAdapter);
                            tab.setupWithViewPager(vp);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



    @OnClick(R.id.pic)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pic:
               startActivity(new Intent(getContext(), JieDianDaoHActivity.class));
                break;
        }
    }
}
