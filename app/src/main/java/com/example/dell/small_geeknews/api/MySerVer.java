package com.example.dell.small_geeknews.api;

import com.example.dell.small_geeknews.Ganks.bean.AppBean;
import com.example.dell.small_geeknews.Ganks.bean.FuliBean;
import com.example.dell.small_geeknews.Ganks.bean.IosBean;
import com.example.dell.small_geeknews.Ganks.bean.AndroidBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Dell on 2019/4/22.
 */

public interface MySerVer {

    public String GankUrl="http://gank.io/api/data/";

    @GET("Android/20/1")
     Observable<AndroidBean> androiddata();

    @GET("iOS/20/1")
     Observable<IosBean> iosdata();

    @GET("App/20/1")
     Observable<AppBean> appdata();


     public String FuliUrl="http://gank.io/api/data/%E7%A6%8F%E5%88%A9/";
    @GET("20/{page}")
     Observable<FuliBean> fulidata(@Path("page") int page);

}
