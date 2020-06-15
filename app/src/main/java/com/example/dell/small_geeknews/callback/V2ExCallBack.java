package com.example.dell.small_geeknews.callback;

import com.example.dell.small_geeknews.V2EX.adapter.bean.V2ExListBean;
import com.example.dell.small_geeknews.wechat.bean.WecahtBean;

/**
 * Created by Dell on 2019/4/19.
 */

public interface V2ExCallBack {

    void onSuccess(V2ExListBean v2ExListBean);

    void onFail(String string);
}
