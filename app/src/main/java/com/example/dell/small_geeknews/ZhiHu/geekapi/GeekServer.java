package com.example.dell.small_geeknews.ZhiHu.geekapi;

import com.example.dell.small_geeknews.ZhiHu.geekbean.DayNewsBean;
import com.example.dell.small_geeknews.ZhiHu.geekbean.HotBean;
import com.example.dell.small_geeknews.ZhiHu.geekbean.IdZhuanlanBean;
import com.example.dell.small_geeknews.ZhiHu.geekbean.MoreDayNewsBean;
import com.example.dell.small_geeknews.ZhiHu.geekbean.NewsXiangQBean;
import com.example.dell.small_geeknews.ZhiHu.geekbean.ZhuanLanBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Dell on 2019/4/17.
 */

public interface GeekServer {

     public String daynewsUrl="http://news-at.zhihu.com/api/4/";
     //日报
     @GET("news/latest")
     Observable<DayNewsBean> daynewsData();

     //日报详情
     @GET("news/{id}")
     Observable<NewsXiangQBean> daynews2Data(@Query("id") int id);

     //日历
     @GET("news/before/{date} ")
     Observable<MoreDayNewsBean> moreDayNewsData(@Path("date") int date);

     //专栏数据
     @GET("sections")
     Observable<ZhuanLanBean> zhuanlanData();
     //专栏数据
     @GET("section/{id}")
     Observable<IdZhuanlanBean> zhuanlan2Data(@Path("id") int id);

     //热门数据
     @GET("news/hot")
     Observable<HotBean> hotData();



}
