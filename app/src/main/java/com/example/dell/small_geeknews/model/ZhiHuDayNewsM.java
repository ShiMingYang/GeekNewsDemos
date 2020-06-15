package com.example.dell.small_geeknews.model;

import com.example.dell.small_geeknews.base.BaseModel;
import com.example.dell.small_geeknews.ZhiHu.resuCallBack.DayNewsCallBack;
import com.example.dell.small_geeknews.ZhiHu.resuCallBack.DayNewsCallBack2;
import com.example.dell.small_geeknews.ZhiHu.geekapi.GeekServer;
import com.example.dell.small_geeknews.ZhiHu.geekbean.DayNewsBean;
import com.example.dell.small_geeknews.ZhiHu.geekbean.MoreDayNewsBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dell on 2019/4/21.
 */

public class ZhiHuDayNewsM extends BaseModel {

    public void DayNewsData(final DayNewsCallBack dayNewsCallBack) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(GeekServer.daynewsUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        GeekServer geekServer = retrofit.create(GeekServer.class);
        final Observable<DayNewsBean> data = geekServer.daynewsData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DayNewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DayNewsBean dayNewsBean) {
                    dayNewsCallBack.onDayNewsSuccess(dayNewsBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void dayData(int intDate, final DayNewsCallBack2 dayNewsCallBack2) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(GeekServer.daynewsUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        GeekServer geekServer = retrofit.create(GeekServer.class);

        Observable<MoreDayNewsBean> data = geekServer.moreDayNewsData(intDate);
        data.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoreDayNewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MoreDayNewsBean moreDayNewsBean) {
                        dayNewsCallBack2.onDayNewsSuccess(moreDayNewsBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
