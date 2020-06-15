package com.example.dell.small_geeknews.Mainfragment;

import android.content.res.Configuration;
import android.support.v7.widget.SwitchCompat;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.small_geeknews.Homeavtivity.MainActivity;
import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.Utils.Constants;
import com.example.dell.small_geeknews.Utils.SpUtil;
import com.example.dell.small_geeknews.Utils.UIModeUtil;
import com.example.dell.small_geeknews.base.BaseFragment;
import com.example.dell.small_geeknews.presenter.SettingP;
import com.example.dell.small_geeknews.view.SettingV;

import butterknife.BindView;

/**
 * Created by Dell on 2019/4/3.
 */

public class SettingFragment extends BaseFragment<SettingP, SettingV> implements SettingV {
    @BindView(R.id.pic)
    ImageView pic;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.swc)
    SwitchCompat swc;


    @Override
    protected SettingP initGeekP() {
        return new SettingP();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.setting_fagment;
    }

    @Override
    protected void initView() {
        int currentNightMode = getActivity().getResources()
                .getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        if (currentNightMode == Configuration.UI_MODE_NIGHT_NO) {
            //判断当前是日间模式
            swc.setChecked(false);
        } else {
            swc.setChecked(true);
        }
    }

    @Override
    protected void initListener() {
        swc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //切换模式
                //切换日夜间模式的时候Activity会重新创建
                //对应的这个碎片也会重建,重建的时候SwitchCompat会设置默认值
                //设置默认值的时候这个回调会被调用
                //if (用户点击的情况下){
                if (buttonView.isPressed()){
                    //切换并保存模式
                    UIModeUtil.changeModeUI((MainActivity) getActivity());
                    //保存当前碎片的type
                    SpUtil.setParam(Constants.DAY_NIGHT_FRAGMENT_POS,MainActivity.type_setting);
                }
            }
        });
    }
}
