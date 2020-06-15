package com.example.dell.small_geeknews.V2EX.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.V2EX.adapter.bean.V2exBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

/**
 * Created by Dell on 2019/4/22.
 */

class RlvAdapter extends RecyclerView.Adapter{

    private ArrayList<V2exBean> mlist;

    public RlvAdapter(ArrayList<V2exBean> list) {

        mlist = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jiedian, null);
        return new ListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ListViewHolder holder1 = (ListViewHolder) holder;
        holder1.mTv1.setText(mlist.get(position).provider);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
    class ListViewHolder extends XRecyclerView.ViewHolder {
            TextView mTv1;
            public ListViewHolder(View itemView) {
                super(itemView);
                this.mTv1 = (TextView) itemView.findViewById(R.id.tv1);
            }
        }
}
