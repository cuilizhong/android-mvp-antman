package com.wmm.antman.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.okhttp.Request;
import com.wmm.antman.R;
import com.wmm.antman.adapter.NewsAdapterByDataBinding;
import com.wmm.antman.databinding.NewsFragmentBinding;
import com.wmm.antman.model.News;
import com.wmm.antman.net.JsonUtil;
import com.wmm.antman.net.OkHttpClientManager;
import com.wmm.antman.net.RequestDataUtils;
import com.wmm.antman.utils.ToastUtil;
import com.wmm.antman.utils.ViewStyleUtis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ming on 2016/4/7.
 */
public class NewsFragmentByDataBinding extends Fragment {

    private static final String ARG_POSITION = "position";
    private List<News> mListData = new ArrayList<>();
    private static NewsFragmentByDataBinding mNewsFragmentByDataBinding = null;
    private int pageIndex = 0;
    private NewsFragmentBinding mNewsFragmentBinding;
    private NewsAdapterByDataBinding mNewsAdapterByDataBinding;
    private static final int INITDATA = 1;

    public static NewsFragmentByDataBinding newInstance(int position) {
        if (mNewsFragmentByDataBinding == null) {
            mNewsFragmentByDataBinding = new NewsFragmentByDataBinding();
        }
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        mNewsFragmentByDataBinding.setArguments(b);
        return mNewsFragmentByDataBinding;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mNewsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.news_fragment, container, false);
        initRecyclerView(mNewsFragmentBinding.RecyclerViewFragmentNews);
        requestData();
        initData();
        return mNewsFragmentBinding.getRoot();
    }

    private void initRecyclerView(RecyclerView mRecyclerView) {
        ViewStyleUtis.setRecyclerViewLine(getActivity(), mRecyclerView, 1);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setVerticalScrollBarEnabled(true);
        mRecyclerView.setHasFixedSize(true);

        int height = (int) getActivity().getResources().getDimension(R.dimen.abc_action_bar_default_height_material);
        mRecyclerView.setPadding(mRecyclerView.getPaddingLeft(), height,
                mRecyclerView.getPaddingRight(), mRecyclerView.getPaddingBottom());
    }

    public void requestData() {
        RequestDataUtils.RequestMessageListData(call, pageIndex);
    }

    OkHttpClientManager.StringCallback call = new OkHttpClientManager.StringCallback() {
        @Override
        public void onFailure(Request request, IOException e) {
            Log.d("response", e.toString());
            ToastUtil.showToast(R.string.internet_error, 1000, R.drawable.ic_ab_drawer, Gravity.CENTER);
        }

        @Override
        public void onResponse(String response) {
            Log.d("response", response);
            mHandler.obtainMessage(INITDATA, response).sendToTarget();
//            mListData = JsonUtil.getNewsListJson(getActivity(),response);
        }
    };

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case INITDATA:
                    String data = (String) msg.obj;
                    List<News> newsList = JsonUtil.getNewsListJson(getActivity(),
                            data);
                    mNewsAdapterByDataBinding.setData(newsList);
                    break;
            }
        }
    };

    private void initData() {
        mNewsAdapterByDataBinding = new NewsAdapterByDataBinding(getActivity(), mListData);
        mNewsFragmentBinding.RecyclerViewFragmentNews.setAdapter(mNewsAdapterByDataBinding);
    }
}

