package com.example.dell.small_geeknews.ZhiHu.XiangQingActivity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.dell.small_geeknews.R;

public class ZhiHuWebActivity extends AppCompatActivity {

    private static final String TAG = "ZhiHuWebActivity";
    private int cid;
    private Toolbar mToolBar;
    private CollapsingToolbarLayout mCtl;
    private AppBarLayout mAppBar;
    private RecyclerView mRlv;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);
        cid = getIntent().getIntExtra("cid", 0);
        initData();
        initView();
    }

    private void initData() {
        //todo  详情界面等待趴取数据
    }


    private void initView() {

        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        mCtl = (CollapsingToolbarLayout) findViewById(R.id.ctl);
        mAppBar = (AppBarLayout) findViewById(R.id.appBar);
        mRlv = (RecyclerView) findViewById(R.id.rlv);
        mFab = (FloatingActionButton) findViewById(R.id.fab);

        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                Log.d(TAG, "onOffsetChanged: " + verticalOffset);
            }
        });


        //标题必须在CollapsingToolbarLayout设置
        mCtl.setTitle("悬浮ToolBar");
        //扩张时候的title颜色
        mCtl.setExpandedTitleColor(getResources().getColor(R.color.colorPrimary));
        //收缩后显示title的颜色
        mCtl.setCollapsedTitleTextColor(getResources().getColor(R.color.white));

    }
}
