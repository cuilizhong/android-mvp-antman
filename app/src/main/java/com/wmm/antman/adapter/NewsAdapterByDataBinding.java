package com.wmm.antman.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wmm.antman.R;
import com.wmm.antman.bean.NewByRetrofit;
import com.wmm.antman.databinding.NewsItemBinding;
import com.wmm.antman.bean.News;
import com.wmm.antman.model.modelview.NewsItemViewModel;

import java.util.List;

/**
 * Created by ming on 2016/4/7.
 */
public class NewsAdapterByDataBinding extends RecyclerView.Adapter<NewsAdapterByDataBinding.BindingHolder> {
    //private final int mBackground;
    private List<NewByRetrofit> newsList;
    private NewsItemViewModel newsItemViewModel;

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

    public void setData(List<NewByRetrofit> data) {
        newsList = data;
        this.notifyDataSetChanged();
    }

    public NewsAdapterByDataBinding(Context context, List<NewByRetrofit> newsList) {
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
        NewByRetrofit newByRetrofit = newsList.get(position);
        holder.binding.imgItemNews.setImageURI(Uri.parse(newByRetrofit.getAvatar_url()));
        holder.binding.setModel(newsItemViewModel = new NewsItemViewModel());
        newsItemViewModel.setTxt(newByRetrofit.getLogin());
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
