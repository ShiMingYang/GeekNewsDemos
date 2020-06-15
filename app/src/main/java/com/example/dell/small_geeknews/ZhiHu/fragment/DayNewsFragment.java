package com.example.dell.small_geeknews.ZhiHu.fragment;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.ZhiHu.XiangQingActivity.ZhiHuWebActivity;
import com.example.dell.small_geeknews.base.BaseFragment;
import com.example.dell.small_geeknews.ZhiHu.adapter.XlvAdapter;
import com.example.dell.small_geeknews.ZhiHu.geekbean.DayNewsBean;
import com.example.dell.small_geeknews.ZhiHu.geekbean.MoreDayNewsBean;
import com.example.dell.small_geeknews.ZhiHu.geekbean.StoriesBean;
import com.example.dell.small_geeknews.ZhiHu.riLi.RiLiActivity;
import com.example.dell.small_geeknews.presenter.ZhiHuP;
import com.example.dell.small_geeknews.view.ZhiHuV;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;

/**
 * Created by Dell on 2019/4/17.
 */

public class DayNewsFragment extends BaseFragment<ZhiHuP, ZhiHuV> implements ZhiHuV, View.OnClickListener {
    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.floatbutton)
    FloatingActionButton floatbutton;

    private View view;
    private XlvAdapter adapter;


    private int intDate;
    // private long durationMills = CircularAnimUtil.PERFECT_MILLS;


    private void initData2() {
        presenter.Data(intDate);
    }

    protected void initData() {
        presenter.getzhihuDaynews();
    }

    @Override
    protected ZhiHuP initGeekP() {
        return new ZhiHuP();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.daynews;
    }

    @Override
    protected void initView() {

        floatbutton.setOnClickListener(this);

        rlv.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new XlvAdapter(getContext());
        rlv.setAdapter(adapter);

       adapter.SetOnItemClickLisener(new XlvAdapter.OnItemClickLisener() {
            @Override
            public void OnItemClickLisener(int position) {
                Intent intent = new Intent(getContext(), ZhiHuWebActivity.class);
                ArrayList<StoriesBean> mlist= new ArrayList<>();
                /*int id = mlist.get(position).getId();
                intent.putExtra("cid",id);*/
                startActivity(intent);
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            String date = data.getStringExtra("date");

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
            String todayDate = df.format(new Date());

            if (date.equals(todayDate)) {
                initData();
            } else {
                //因为获取出来的数据是当前日期的前一天,所以要给他加1
                intDate = Integer.valueOf(date);
                intDate += 1;
                initData2();
            }


        }
    }


    @Override
    public void DayNewsSuccess(DayNewsBean dayNewsBean) {
        adapter.setData(dayNewsBean);
    }

    @Override
    public void DayNewsFail(String string) {

    }

    @Override
    public void successData(MoreDayNewsBean moreDayNewsBean) {
        adapter.setBeforedata(moreDayNewsBean);
    }

    @Override
    public void failData(String string) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.floatbutton:
                Intent intent = new Intent(getContext(), RiLiActivity.class);
                startActivityForResult(intent, 100);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //action();
                }
                break;

        }
    }



   /* @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void action() {

            int[] location = new int[2];
            mFloatbutton.getLocationInWindow(location);
            final int cx = location[0] + mFloatbutton.getWidth() / 2;
            final int cy = location[1] + mFloatbutton.getHeight() / 2;
            final ImageView view = new ImageView(getContext());
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setImageResource(R.color.colorAccent);
            final ViewGroup decorView = (ViewGroup) getActivity().getWindow().getDecorView();
            int w = decorView.getWidth();
            int h = decorView.getHeight();
            decorView.addView(view, w, h);

            // 计算中心点至view边界的最大距离
            int maxW = Math.max(cx, w - cx);
            int maxH = Math.max(cy, h - cy);
            final int finalRadius = (int) Math.sqrt(maxW * maxW + maxH * maxH) + 1;
            Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
            int maxRadius = (int) Math.sqrt(w * w + h * h) + 1;
            // 若使用默认时长，则需要根据水波扩散的距离来计算实际时间
            if (durationMills == PERFECT_MILLS) {
                // 算出实际边距与最大边距的比率
                double rate = 1d * finalRadius / maxRadius;
                // 水波扩散的距离与扩散时间成正比
                durationMills = (long) (PERFECT_MILLS * rate);
            }
            final long finalDuration = durationMills;
            anim.setDuration(finalDuration);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    Intent intent = new Intent(getContext(),RiLiActivity.class);
                    startActivityForResult(intent,100);
                    // 默认渐隐过渡动画.
                    getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                    // 默认显示返回至当前Activity的动画.
                    mFloatbutton.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Animator anim =
                                    ViewAnimationUtils.createCircularReveal(view, cx, cy, finalRadius, 0);
                            anim.setDuration(finalDuration);
                            anim.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    try {
                                        decorView.removeView(view);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            anim.start();
                        }
                    }, 1000);
                }
            });
            anim.start();
        }
*/
}
