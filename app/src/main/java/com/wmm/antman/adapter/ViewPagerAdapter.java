package com.wmm.antman.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wmm.antman.fragment.NewsFragment;
import com.wmm.antman.fragment.NewsFragmentByDataBinding;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private String TAG = "ViewPagerAdapter";
    private int mNumOfTabs;
    private Context mContext;

    public ViewPagerAdapter(Context context, FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return NewsFragmentByDataBinding.newInstance(position);
            case 1:
                return NewsFragment.newInstance(position);
//            case 2:
//                return NewsFragment.newInstance(position);
//            case 3:
//                return NewsFragment.newInstance(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}