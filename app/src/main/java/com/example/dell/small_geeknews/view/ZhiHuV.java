package com.example.dell.small_geeknews.view;

import com.example.dell.small_geeknews.base.BaseView;
import com.example.dell.small_geeknews.ZhiHu.geekbean.DayNewsBean;
import com.example.dell.small_geeknews.ZhiHu.geekbean.MoreDayNewsBean;

/**
 * Created by Dell on 2019/4/3.
 */

public interface ZhiHuV extends BaseView {

    void DayNewsSuccess(DayNewsBean dayNewsBean);

    void DayNewsFail(String string);


    void successData(MoreDayNewsBean moreDayNewsBean);

    void failData(String string);
}
