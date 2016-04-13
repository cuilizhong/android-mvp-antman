package com.wmm.antman.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wmm.antman.R;
import com.wmm.antman.bean.MyFollowersBean;
import com.wmm.antman.bean.NewByRetrofit;
import com.wmm.antman.databinding.MyFollowersItemBinding;
import com.wmm.antman.databinding.NewsItemBinding;
import com.wmm.antman.model.modelview.MyFollowersItemViewModel;
import com.wmm.antman.model.modelview.NewsItemViewModel;

import java.util.List;

/**
 * Created by ming on 2016/4/7.
 */
public class MyFollowersAdapterByDataBinding extends RecyclerView.Adapter<MyFollowersAdapterByDataBinding.BindingHolder> {
    //private final int mBackground;
    private List<MyFollowersBean> mList;
    private MyFollowersItemViewModel mModel;

    public class BindingHolder extends RecyclerView.ViewHolder {
        private MyFollowersItemBinding binding;

        public BindingHolder(View v) {
            super(v);
        }

        public MyFollowersItemBinding getBinding() {
            return binding;
        }

        public void setBinding(MyFollowersItemBinding binding) {
            this.binding = binding;
        }
    }

    public void setData(List<MyFollowersBean> data) {
        mList = data;
        this.notifyDataSetChanged();
    }

    public MyFollowersAdapterByDataBinding(Context context, List<MyFollowersBean> mList) {
        this.mList = mList;
    }

    @Override
    public MyFollowersAdapterByDataBinding.BindingHolder onCreateViewHolder(ViewGroup parent,
                                                                            int viewType) {
        MyFollowersItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.my_followers_item,parent,false);
        BindingHolder holder = new BindingHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        MyFollowersBean mMyFollowersBean = mList.get(position);
        holder.binding.imgItemMyFollowers.setImageURI(Uri.parse(mMyFollowersBean.getAvatar_url()));
        holder.binding.setModel(mModel = new MyFollowersItemViewModel());
        mModel.setTxt(mMyFollowersBean.getLogin());
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
