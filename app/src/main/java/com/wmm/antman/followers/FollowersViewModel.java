package com.wmm.antman.followers;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;

/**
 * Created by ming on 2016/4/15.
 */
public class FollowersViewModel extends BaseObservable {
    int mFollowersListSize = 0;
    private final FollowersContract.Presenter mPresenter;
    private Context mContext;

    public FollowersViewModel(Context context, FollowersContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
        this.mContext = context;
    }

    @Bindable
    public boolean isNotEmpty() {
        return mFollowersListSize > 0;
    }

    @Bindable
    public Drawable getImg() {

        return null;
    }

    public void setmFollowersListSize(int mFollowersListSize) {
        this.mFollowersListSize = mFollowersListSize;
//        notifyPropertyChanged(BR.txt);
//        notifyPropertyChanged(BR.notEmpty);
//        notifyPropertyChanged(BR.model);
    }
}
