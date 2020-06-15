package com.example.dell.small_geeknews.Ganks.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dell.small_geeknews.Ganks.adapter.AndroidAdapter;
import com.example.dell.small_geeknews.Ganks.adapter.IOSAdapter;
import com.example.dell.small_geeknews.Ganks.bean.AndroidBean;
import com.example.dell.small_geeknews.Ganks.bean.IosBean;
import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.Utils.SystemUtil;
import com.example.dell.small_geeknews.api.MySerVer;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.glide.transformations.BlurTransformation;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dell on 2019/4/22.
 */

public class IOSFragment extends Fragment {
    private View view;
    private ImageView mIvTechBlur;
    private ImageView mIvTechOrigin;
    /**
     * XX: 小美女
     */
    private TextView mTvTechCopyright;
    private AppBarLayout mTechAppbar;
    private RecyclerView mRlv;
    private IOSAdapter adapter;
    private ArrayList<IosBean.ResultsBean> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.ios, null);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(MySerVer.GankUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MySerVer mySerVer = retrofit.create(MySerVer.class);

        Observable<IosBean> androiddata = mySerVer.iosdata();

        androiddata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IosBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(IosBean iosBean) {
                        adapter.setList(iosBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView(View inflate) {
        mIvTechBlur = (ImageView) inflate.findViewById(R.id.iv_tech_blur);
        mIvTechOrigin = (ImageView) inflate.findViewById(R.id.iv_tech_origin);
        mTvTechCopyright = (TextView) inflate.findViewById(R.id.tv_tech_copyright);
        mTechAppbar = (AppBarLayout) inflate.findViewById(R.id.tech_appbar);
        mRlv = (RecyclerView) inflate.findViewById(R.id.rlv);

        list = new ArrayList<>();
        adapter = new IOSAdapter(getActivity(),list);
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mRlv.setAdapter(adapter);


        RequestOptions options = new RequestOptions()
                .transform(new BlurTransformation());
        Glide.with(getContext()).load(R.drawable.meizi2).apply(options).into(mIvTechBlur);
        Glide.with(getContext()).load(R.drawable.meizi2).into(mIvTechOrigin);

        //appbar 滑动偏移监听
        mTechAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //两张图片,后面的是高斯模糊过的,前面的是原图,滑动过程中修改原图
                //的透明度,实现效果
                ///透明度值范围,0到1,0完全透明,1完全不透明
                //1dp = 3px(我的手机) 768px
                int imgHeight = SystemUtil.dp2px(256);
                //verticalOffset 0到-768
                //verticalOffset / imgHeight(768)范围:0到-1
                float rate = 1+verticalOffset * 2.0f / imgHeight;
                if (rate>=0){
                    mIvTechOrigin.setAlpha(rate);
                }

            }
        });
    }
}
