package com.example.dell.small_geeknews.Homeavtivity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.small_geeknews.Mainfragment.CollectFragment;
import com.example.dell.small_geeknews.Mainfragment.GankFragment;
import com.example.dell.small_geeknews.Mainfragment.GoldFragment;
import com.example.dell.small_geeknews.Mainfragment.MainAboutFragment;
import com.example.dell.small_geeknews.Mainfragment.SettingFragment;
import com.example.dell.small_geeknews.Mainfragment.V2ExFragment;
import com.example.dell.small_geeknews.Mainfragment.WeChatFragment;
import com.example.dell.small_geeknews.Mainfragment.ZhiHuFragment;
import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.Utils.Constants;
import com.example.dell.small_geeknews.Utils.SpUtil;
import com.example.dell.small_geeknews.base.BaseActivity;
import com.example.dell.small_geeknews.presenter.EmptyP;
import com.example.dell.small_geeknews.view.EmptyV;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity<EmptyP, EmptyV> implements EmptyV {


    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.tooltv)
    TextView tooltv;
    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.nv)
    NavigationView nv;
    @BindView(R.id.dl)
    DrawerLayout dl;
    @BindView(R.id.ll)
    LinearLayout ll;

    @BindView(R.id.search_view)
    MaterialSearchView mSearchView;
    @BindView(R.id.toolbar_container)
    FrameLayout toolbarContainer;
    private FragmentManager manager;
    private ArrayList<Fragment> fragments;
    private ArrayList<Integer> titles;
    private final int type_zhihu = 0;
    private final int type_wechat = 1;
    private final int type_gank = 2;
    private final int type_gold = 3;
    private final int type_v2ex = 4;
    private final int type_collect = 5;
    public static final int type_setting = 6;
    private final int type_mainabout = 7;
    private int mLastFargmentposition;
    protected MenuItem mSearchItem;
    private WeChatFragment weChatFragment;
    private int type;



    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        manager = getSupportFragmentManager();

        //tooltv.setText(R.string.geek);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, toolbar,
                R.string.app_name, R.string.app_name);
        dl.addDrawerListener(toggle);
        toggle.syncState();
        //设置颜色
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        //设置旋转开关的颜色
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.c_bule));

        initFragment();
        initTitile();
        addZhiHuFragment();

    }

    private void addZhiHuFragment() {
        //如果是因为切换日夜间模式导致Activyt重建,需要直接进入设置Fragment
        type = (int) SpUtil.getParam(Constants.DAY_NIGHT_FRAGMENT_POS, type_zhihu);
        //开启事物
        FragmentTransaction transaction = manager.beginTransaction();
        //添加首个碎片显示 并提交事物
        transaction.add(R.id.fl, fragments.get(type));
        transaction.commit();

       tooltv.setText(titles.get(type));
    }


    private void initTitile() {
        titles = new ArrayList<>();
        titles.add(R.string.geek);
        titles.add(R.string.wechat);
        titles.add(R.string.gank);
        titles.add(R.string.gold);
        titles.add(R.string.vtex);
        titles.add(R.string.follow);
        titles.add(R.string.set);
        titles.add(R.string.main);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new ZhiHuFragment());
        weChatFragment = new WeChatFragment();
        fragments.add(weChatFragment);
        fragments.add(new GankFragment());
        fragments.add(new GoldFragment());
        fragments.add(new V2ExFragment());
        fragments.add(new CollectFragment());
        fragments.add(new SettingFragment());
        fragments.add(new MainAboutFragment());
    }


    @Override
    protected void initListener() {
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId != R.id.zixun && itemId != R.id.options) {
                    item.setChecked(true);
                    switch (item.getItemId()) {
                        case R.id.info:
                            switchFragment(type_zhihu);
                            tooltv.setText(R.string.geek);
                            break;
                        case R.id.wechat:
                            switchFragment(type_wechat);
                            tooltv.setText(R.string.wechat);
                            break;
                        case R.id.gank:
                            switchFragment(type_gank);
                            tooltv.setText(R.string.gank);
                            break;
                        case R.id.gold:
                            switchFragment(type_gold);
                            tooltv.setText(R.string.gold);
                            break;
                        case R.id.v2Ex:
                            switchFragment(type_v2ex);
                            tooltv.setText(R.string.vtex);
                            break;
                        case R.id.follow:
                            switchFragment(type_collect);
                            tooltv.setText(R.string.follow);
                            break;
                        case R.id.set:
                            switchFragment(type_setting);
                            tooltv.setText(R.string.set);
                            break;
                        case R.id.main:
                            switchFragment(type_mainabout);
                            tooltv.setText(R.string.main);
                            break;
                    }
                    dl.closeDrawer(Gravity.LEFT);
                } else {
                    item.setChecked(false);
                }
                return false;
            }
        });

        dl.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                //旋转开关和侧滑之间的滑动百分比
                float v = dl.getWidth() * slideOffset;
                ll.setX(v);
            }
        });

        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //点击搜索按钮提交搜索的内容,weChatFragment就是微信精选界面的碎片
                if (weChatFragment.isVisible()) {
                    Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                    weChatFragment.setQueat(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                //搜索框中内容发生改变
                return false;
            }
        });
    }

    private void switchFragment(int type) {
        //显示一个fragmnet,隐藏一个Fragment
        //显示
        Fragment fragment = fragments.get(type);
        //需要隐藏之前的一个界面
        Fragment hide = fragments.get(mLastFargmentposition);
        //重启事物
        FragmentTransaction transaction = manager.beginTransaction();
        //判断是否被选中，如果没有被选中就添加到事物中
        if (!fragment.isAdded()) {
            transaction.add(R.id.fl, fragment);
        }
        //先隐藏，在显示，这样第一个碎片就不会重复点击的时候隐藏掉
        transaction.hide(hide);
        transaction.show(fragment);

        transaction.commit();
        //自定义上一个界面的位置等同于碎片的位置
        mLastFargmentposition = type;


        //显示或者隐藏搜索框
        if (type == type_wechat || type == type_gank) {
            mSearchItem.setVisible(true);
        } else {
            mSearchItem.setVisible(false);
        }

    }

    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);

        mSearchItem = menu.findItem(R.id.action_search);
        //设置条目隐藏信息
        mSearchItem.setVisible(false);
        mSearchView.setMenuItem(mSearchItem);

        return true;
    }


    /**
     * 回退键点击回调
     */
   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        //点击返回键
        if(keyCode==KeyEvent.KEYCODE_BACK){
            //声明弹出对象并初始化
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("提示：");
            builder.setMessage("确定要退出GeekNews吗？");
            //设置确定按钮
            builder.setNegativeButton("取消",null);
            //设置取消按钮
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();

                }
            });

            //显示弹窗
            builder.show();
            SpUtil.setParam(Constants.DAY_NIGHT_FRAGMENT_POS,type_zhihu);
            return true;
        }else {

            return super.onKeyDown(keyCode,event);
        }

    }*/
    /**
     * 回退键点击回调
     */
  /*  @Override
    public void onBackPressed() {
        if (mSearchView.isSearchOpen()) {
            mSearchView.closeSearch();
        } else {

            super.onBackPressed();
            SpUtil.setParam(Constants.DAY_NIGHT_FRAGMENT_POS,type_zhihu);
        }
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
