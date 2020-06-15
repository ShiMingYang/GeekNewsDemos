package com.example.dell.small_geeknews.V2EX.adapter.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.V2EX.adapter.V2ExXlvAdapter;
import com.example.dell.small_geeknews.V2EX.adapter.bean.V2ExListBean;
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

import butterknife.Unbinder;

/**
 * Created by Dell on 2019/4/21.
 */

public class V2exListFragment extends BaseFragment<V2exP, V2exV> implements V2exV {

    private static final String TAG = "V2exListFragment";
    @BindView(R.id.rlv)
    RecyclerView rlv;
    private String Url = "https://www.v2ex.com";
    private String count;
    private String autortext;
    private String autors;
    private String secondTabs;
    private String info;
    private String titles;
    private String src;
    private ArrayList<V2ExListBean> list;
    private V2ExXlvAdapter adapter;

    @Override
    protected V2exP initGeekP() {
        return new V2exP();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.zhuanlan;
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void initData() {
        //  presenter.GetV2ExData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String tech = getArguments().getString("tab");

                list = new ArrayList<>();
                try {
                    Document doc = Jsoup.connect(Url + tech).get();
                    Elements elements = doc.select("div.cell.item");
                    for (Element element : elements) {
                        Elements img = element.select("table tbody tr td > a >img.avatar");
                        src = img.attr("src");
                        //Log.e(TAG, "src: "+src );
                        Elements title = element.select("table tbody tr td span.item_title >a");
                        titles = title.text();
                        // Log.e(TAG, "子条目标题 "+titles );
                        Elements allinfo = element.select("table tbody tr td span.topic_info");
                        info = allinfo.text();
                        // Log.e(TAG, "评论信息: "+info );

                        Element tabs2 = allinfo.select("a.node").first();
                        secondTabs = tabs2.text();
                        // Log.e(TAG, "程序员: "+secondTabs );

                        Elements people = allinfo.select("strong >a");
                        if (people.size() > 0) {
                            Element author = people.get(0);
                            autors = author.text();
                           // Log.d(TAG, "作者: " + author.text());
                        }

                        if (people.size() > 1) {
                            Element author1 = people.get(1);
                            autortext = author1.text();
                            // Log.e(TAG, "评论人: "+autortext );
                        }

                        Elements shu = element.select("table tbody tr td > a >count_livid");
                        count = shu.text();
                        // Log.e(TAG, "count: "+count );
                        V2ExListBean v2ExListBean = new V2ExListBean(src, titles, info, secondTabs, autors, autortext, count);
                        list.add(v2ExListBean);
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            rlv.setLayoutManager(new LinearLayoutManager(getContext()));
                            adapter = new V2ExXlvAdapter(getContext(),list);
                            rlv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
                            rlv.setAdapter(adapter);
                        }
                    });


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
