package com.example.dell.small_geeknews.V2EX.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.V2EX.adapter.bean.V2ExListBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

/**
 * Created by Dell on 2019/4/21.
 */

public class V2ExXlvAdapter extends RecyclerView.Adapter {
    private Context mcontext;
    private ArrayList<V2ExListBean> mlist;

    public V2ExXlvAdapter(Context context, ArrayList<V2ExListBean> list) {

        mcontext = context;
        mlist = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mcontext).inflate(R.layout.v2ex_item, null);
        return new ListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ListViewHolder holder1 = (ListViewHolder) holder;
        holder1.title.setText(mlist.get(position).getTitles());
        holder1.chengxu.setText(mlist.get(position).getSecondTabs());
        holder1.autor.setText(mlist.get(position).getAutors());
        holder1.autortext.setText(mlist.get(position).getAutortext());
        holder1.count.setText(mlist.get(position).getCount());

        Glide.with(mcontext).load("https:"+mlist.get(position).getSrc()).into(holder1.mPic);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class ListViewHolder extends XRecyclerView.ViewHolder {
            ImageView mPic;
            TextView title;
            TextView count;
            TextView chengxu;
            TextView autor;
            TextView autortext;
            public ListViewHolder(View itemView) {
                super(itemView);
                this.mPic = (ImageView) itemView.findViewById(R.id.pic);
                this.title = (TextView) itemView.findViewById(R.id.title);
                this.count = (TextView) itemView.findViewById(R.id.count);
                this.chengxu = (TextView) itemView.findViewById(R.id.chengxu);
                this.autor = (TextView) itemView.findViewById(R.id.autor);
                this.autortext = (TextView) itemView.findViewById(R.id.autortext);
            }
        }
}
