package com.example.dell.small_geeknews.presenter;

import com.example.dell.small_geeknews.base.BasePresenter;
import com.example.dell.small_geeknews.ZhiHu.resuCallBack.DayNewsCallBack;
import com.example.dell.small_geeknews.ZhiHu.resuCallBack.DayNewsCallBack2;
import com.example.dell.small_geeknews.ZhiHu.geekbean.DayNewsBean;
import com.example.dell.small_geeknews.ZhiHu.geekbean.MoreDayNewsBean;
import com.example.dell.small_geeknews.model.ZhiHuDayNewsM;
import com.example.dell.small_geeknews.view.ZhiHuV;

/**
 * Created by Dell on 2019/4/3.
 */

public class ZhiHuP extends BasePresenter<ZhiHuV> {


    private ZhiHuDayNewsM zhiHuDayNewsM;



    @Override
    protected void initModel() {
        zhiHuDayNewsM = new ZhiHuDayNewsM();
    }

    public void getzhihuDaynews() {
        zhiHuDayNewsM.DayNewsData(new DayNewsCallBack() {
            @Override
            public void onDayNewsSuccess(DayNewsBean dayNewsBean) {
                mView.DayNewsSuccess(dayNewsBean);
            }

            @Override
            public void onDayNewsFail(String string) {
                mView.DayNewsFail(string);
            }
        });
    }

    public void Data(int intDate) {
        zhiHuDayNewsM.dayData(intDate, new DayNewsCallBack2() {
            @Override
            public void onDayNewsSuccess(MoreDayNewsBean moreDayNewsBean) {
                mView.successData(moreDayNewsBean);
            }

            @Override
            public void onDayNewsFail(String string) {
                mView.failData(string);
            }
        });
    }
}
