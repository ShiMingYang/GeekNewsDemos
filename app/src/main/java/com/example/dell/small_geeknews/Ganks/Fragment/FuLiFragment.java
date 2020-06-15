package com.example.dell.small_geeknews.Ganks.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.small_geeknews.Ganks.adapter.FuLiAdapter;
import com.example.dell.small_geeknews.Ganks.bean.FuliBean;
import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.api.MySerVer;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dell on 2019/4/22.
 */

public class FuLiFragment extends Fragment {
    private static final String TAG = "FuLiFragment";
    private View view;
    private RecyclerView mRlv;
    private FuLiAdapter adapter;
    private StaggeredGridLayoutManager manager;
    private ArrayList<FuliBean.ResultsBean> list;
    private View mTv;
    private SmartRefreshLayout mSmr;
    int page = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.fuli, null);
        initView(inflate);
        initData();
        return inflate;
    }


    private void initData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(MySerVer.FuliUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MySerVer mySerVer = retrofit.create(MySerVer.class);

        Observable<FuliBean> androiddata = mySerVer.fulidata(page);

        androiddata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FuliBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FuliBean fuliBean) {
                        adapter.setList(fuliBean);
                        Log.i(TAG, "onError: " + fuliBean.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView(View inflate) {
        mRlv = (RecyclerView) inflate.findViewById(R.id.rlv);
        mSmr = (SmartRefreshLayout) inflate.findViewById(R.id.smr);

        manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        //fix issue #52 https://github.com/codeestX/GeekNews/issues/52
        manager.setItemPrefetchEnabled(false);
        mRlv.setLayoutManager(manager);
        // mRlv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        list = new ArrayList<>();
        adapter = new FuLiAdapter(getContext(), list);
        mRlv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        mSmr.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
                mSmr.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                adapter.mlist.clear();
                initData();
                mSmr.finishRefresh();
            }
        });
    }
}
