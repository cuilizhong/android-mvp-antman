package com.wmm.antman.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wmm.antman.R;
import com.wmm.antman.databinding.NewsItemBinding;
import com.wmm.antman.model.News;

import java.util.List;

/**
 * Created by ming on 2016/4/7.
 */
public class NewsAdapterByDataBinding extends RecyclerView.Adapter<NewsAdapterByDataBinding.BindingHolder> {
    //private final int mBackground;
    private List<News> newsList;

    public class BindingHolder extends RecyclerView.ViewHolder {
        private NewsItemBinding binding;

        public BindingHolder(View v) {
            super(v);
        }

        public NewsItemBinding getBinding() {
            return binding;
        }

        public void setBinding(NewsItemBinding binding) {
            this.binding = binding;
        }
    }

    public void setData(List<News> data) {
        newsList = data;
        this.notifyDataSetChanged();
    }

    public NewsAdapterByDataBinding(Context context, List<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public NewsAdapterByDataBinding.BindingHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        NewsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.news_item,parent,false);
        BindingHolder holder = new BindingHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        News news = newsList.get(position);
        holder.binding.imgItemNews.setImageURI(Uri.parse(news.getActivityIconUrl()));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
