package com.example.dell.small_geeknews.Mainfragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.base.BaseFragment;
import com.example.dell.small_geeknews.presenter.WeChatP;
import com.example.dell.small_geeknews.view.WeChatV;
import com.example.dell.small_geeknews.wechat.adapter.WeCahrAdapter;
import com.example.dell.small_geeknews.wechat.bean.WecahtBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Dell on 2019/4/3.
 */

public class WeChatFragment extends BaseFragment<WeChatP, WeChatV>  implements WeChatV{
    private static final String TAG = "WeChatFragment";
    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.smr)
    SmartRefreshLayout smr;

    private WeCahrAdapter adapter;
    private int pages = 1;
    private ArrayList<WecahtBean.NewslistBean> list;



    @Override
    protected WeChatP initGeekP() {
        return new WeChatP();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.wechat_fagment;
    }
  /*key=52b7ec3471ac3bec6846577e79f20e4c&num=10&page=1*/
     String key="52b7ec3471ac3bec6846577e79f20e4c";   //todo 改接口的参数已经过期 需要去申请
     int num=10;
     int page=1;
    @Override
    protected void initData() {
        presenter.getdata(key,num,page);

    }

    @Override
    protected void initView() {
        rlv.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        adapter = new WeCahrAdapter(getContext(), list);
        rlv.setAdapter(adapter);

        smr.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                adapter.mlist.clear();
                initData();
                smr.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                initData();
                smr.finishRefresh();
            }
        });


    }

    @Override
    public void setData(WecahtBean wecahtBean) {
        Log.e(TAG, "setData: "+wecahtBean.toString() );
       list.addAll(wecahtBean.getNewslist());
       adapter.notifyDataSetChanged();

    }

    @Override
    public void setError(String string) {
        Toast.makeText(getContext(), string, Toast.LENGTH_SHORT).show();
    }


    public void setQueat(String query) {
        adapter.mlist.clear();
        if (query != null){
           presenter.setWechatSoData(pages,query);


        }else {
            Toast.makeText(getContext(), "没有搜索内容", Toast.LENGTH_SHORT).show();
        }
    }


}
