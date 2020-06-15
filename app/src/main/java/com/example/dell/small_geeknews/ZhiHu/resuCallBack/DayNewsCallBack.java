package com.example.dell.small_geeknews.ZhiHu.resuCallBack;

import com.example.dell.small_geeknews.ZhiHu.geekbean.DayNewsBean;

/**
 * Created by Dell on 2019/4/19.
 */

public interface DayNewsCallBack {

    void onDayNewsSuccess(DayNewsBean dayNewsBean);

    void onDayNewsFail(String string);
}
