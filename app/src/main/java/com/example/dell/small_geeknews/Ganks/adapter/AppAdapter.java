package com.example.dell.small_geeknews.Ganks.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.small_geeknews.Ganks.bean.AppBean;
import com.example.dell.small_geeknews.Ganks.bean.IosBean;
import com.example.dell.small_geeknews.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

/**
 * Created by Dell on 2019/4/22.
 */

public class AppAdapter extends RecyclerView.Adapter{


    private FragmentActivity mactivity;
    private ArrayList<AppBean.ResultsBean> mlist;

    public AppAdapter(FragmentActivity activity, ArrayList<AppBean.ResultsBean> list) {

        mactivity = activity;
        mlist = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mactivity).inflate(R.layout.android_item, null);
        return new ListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ListViewHolder holder1 = (ListViewHolder) holder;
        holder1.mName.setText(mlist.get(position).getWho());
        holder1.mTitle.setText(mlist.get(position).getDesc());
        holder1.mTime.setText(mlist.get(position).getPublishedAt());

        holder1.mPic.setImageResource(R.mipmap.icon_wan);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void setList(AppBean appBean) {
        mlist.addAll(appBean.getResults());
        notifyDataSetChanged();
    }


    class ListViewHolder extends XRecyclerView.ViewHolder {
        ImageView mPic;
        TextView mName;
        TextView mTitle;
        TextView mTime;

        public ListViewHolder(View itemView) {
            super(itemView);
            this.mPic = (ImageView) itemView.findViewById(R.id.pic);
            this.mName = (TextView) itemView.findViewById(R.id.name);
            this.mTitle = (TextView) itemView.findViewById(R.id.title);
            this.mTime = (TextView) itemView.findViewById(R.id.time);
        }
    }
}
