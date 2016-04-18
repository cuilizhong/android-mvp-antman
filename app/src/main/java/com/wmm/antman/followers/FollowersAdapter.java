package com.wmm.antman.followers;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wmm.antman.R;
import com.wmm.antman.data.Followers;
import com.wmm.antman.databinding.FollowersItemBinding;

import java.util.List;


/**
 * Created by ming on 2016/4/7.
 */
public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.BindingHolder> {
    private List<Followers> mList;
//    private FollowersItemBinding mModel;
    private FollowersContract.Presenter mUserActionsListener;
    private Context mContext;

    public class BindingHolder extends RecyclerView.ViewHolder {
        private FollowersItemBinding binding;

        public BindingHolder(View v) {
            super(v);
        }

        public FollowersItemBinding getBinding() {
            return binding;
        }

        public void setBinding(FollowersItemBinding binding) {
            this.binding = binding;
        }
    }

    public void setData(List<Followers> data) {
        mList = data;
        this.notifyDataSetChanged();
    }

    public void replaceData(List<Followers> followerses) {
        setData(followerses);
    }

    public FollowersAdapter(Context context, List<Followers> mList,FollowersContract.Presenter itemListener) {
        this.mContext = context;
        setData(mList);
        this.mUserActionsListener = itemListener;
    }

    @Override
    public FollowersAdapter.BindingHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        FollowersItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.followers_item,parent,false);
        BindingHolder holder = new BindingHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        Followers mFollowers = mList.get(position);
        FollowersItemActionHandler itemActionHandler =
                new FollowersItemActionHandler(mUserActionsListener);
        holder.binding.setActionHandler(itemActionHandler);
        holder.binding.imgItemMyFollowers.setImageURI(Uri.parse(mFollowers.getAvatar_url()));
        holder.binding.setFollowers(mFollowers);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
