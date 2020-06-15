package com.example.dell.small_geeknews.presenter;

import com.example.dell.small_geeknews.V2EX.adapter.bean.V2ExListBean;
import com.example.dell.small_geeknews.base.BasePresenter;
import com.example.dell.small_geeknews.callback.V2ExCallBack;
import com.example.dell.small_geeknews.model.V2EXM;
import com.example.dell.small_geeknews.view.V2exV;
import com.example.dell.small_geeknews.view.WeChatV;

/**
 * Created by Dell on 2019/4/3.
 */

public class V2exP extends BasePresenter<V2exV> {

    private V2EXM v2EXM;

    @Override
    protected void initModel() {
        v2EXM = new V2EXM();
    }

    public void GetV2ExData() {
        v2EXM.V2exData(new V2ExCallBack() {
            @Override
            public void onSuccess(V2ExListBean v2ExListBean) {

            }

            @Override
            public void onFail(String string) {

            }
        });
    }
}
