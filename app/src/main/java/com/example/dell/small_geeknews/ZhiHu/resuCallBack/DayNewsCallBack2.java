package com.example.dell.small_geeknews.ZhiHu.resuCallBack;

import com.example.dell.small_geeknews.ZhiHu.geekbean.MoreDayNewsBean;

/**
 * Created by Dell on 2019/4/19.
 */

public interface DayNewsCallBack2 {

    void onDayNewsSuccess(MoreDayNewsBean moreDayNewsBean);

    void onDayNewsFail(String string);
}
