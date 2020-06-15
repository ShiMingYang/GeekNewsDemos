package com.example.dell.small_geeknews.Ganks.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.dell.small_geeknews.Ganks.bean.FuliBean;
import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.Utils.MyApp;
import com.example.dell.small_geeknews.Utils.SystemUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Dell on 2019/4/22.
 */

public class FuLiAdapter extends RecyclerView.Adapter{
    private static final String TAG = "FuLiAdapter";
    private final Context mactivity;
    public final List<FuliBean.ResultsBean> mlist;

    public FuLiAdapter(Context context, List<FuliBean.ResultsBean> list1) {
        mactivity = context;
        this.mlist = list1;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mactivity).inflate(R.layout.fuli_item, null);
        return new ListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ListViewHolder holder1 = (ListViewHolder) holder;

        FuliBean.ResultsBean bean = mlist.get(position);
        //在设置图片之前把ImageView的宽高重新设置一下
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder1.mPic.getLayoutParams();
        int imageWidth = MyApp.mWidthPixels / 2 - SystemUtil.dp2px(8);
        layoutParams.width = imageWidth;
        if (bean.getScale() != 0){
            layoutParams.height = (int) (imageWidth/bean.getScale());
        }
        layoutParams.leftMargin = layoutParams.topMargin = SystemUtil.dp2px(4);
        holder1.mPic.setLayoutParams(layoutParams);

        RequestOptions options = new RequestOptions()
                .transform(new RoundedCornersTransformation(SystemUtil.dp2px(6),0));
        Glide.with(mactivity).load(mlist.get(position).getUrl()).apply(options).into(holder1.mPic);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void setList(FuliBean fuliBean) {
        mlist.addAll(fuliBean.getResults());
        setImageScale();
    }

    //计算图片的宽高比
    private void setImageScale() {
        for (final FuliBean.ResultsBean bean :mlist) {
            if (bean.getScale() == 0) {
                //未计算过宽高比
                Glide.with(mactivity).load(bean.getUrl()).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource,
                                                @Nullable Transition<? super Drawable> transition) {
                        //宽高比
                        float scale = resource.getIntrinsicWidth() * 1.0f / resource.getIntrinsicHeight();
                        bean.setScale(scale);
                        notifyDataSetChanged();
                    }
                });
            }else {
                notifyDataSetChanged();
            }
        }
    }

    class ListViewHolder extends XRecyclerView.ViewHolder {
            ImageView mPic;
            public ListViewHolder(View itemView) {
                super(itemView);
                this.mPic = (ImageView) itemView.findViewById(R.id.pic);
            }
        }
}
