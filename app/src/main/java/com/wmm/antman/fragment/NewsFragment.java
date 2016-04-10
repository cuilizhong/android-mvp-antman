package com.wmm.antman.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.squareup.okhttp.Request;
import com.wmm.antman.R;
import com.wmm.antman.adapter.NewsAdapter;
import com.wmm.antman.base.BaseFragment;
import com.wmm.antman.bean.News;
import com.wmm.antman.net.JsonUtil;
import com.wmm.antman.net.OkHttpClientManager;
import com.wmm.antman.net.RequestDataUtils;
import com.wmm.antman.utils.ToastUtil;
import com.wmm.antman.utils.ViewStyleUtis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by wangmingming on 2015/11/5 10:31.
 */


public class NewsFragment extends BaseFragment {

    private static final String ARG_POSITION = "position";
    public static RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private List<News> mListData = new ArrayList<>();
    private NewsAdapter mNewsAdapter;
    private static final int INITDATA = 1;
//    private SwipeRefreshLayout mSwipeRefreshLayout;
    private static NewsFragment mNewsFragment = null;
    private int pageIndex = 0;
    private Context mContext;

    public static NewsFragment newInstance(int position) {
        if (mNewsFragment == null) {
            mNewsFragment = new NewsFragment();
        }
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        mNewsFragment.setArguments(b);
        return mNewsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        initView(rootView);
        mContext = getActivity();

        initData();
        requestData();

        return rootView;
    }

    /**
     * 初始化控件
     *
     * @param rootView
     */
    private void initView(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.RecyclerView_fragment_news);
//        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.SwipeRefreshLayout);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
//        mSwipeRefreshLayout.setColorSchemeResources(R.color.blue_500, R.color.yellow_500,
//                R.color.red_500, R.color.black);
//        mSwipeRefreshLayout.setRefreshing(true);
//        mSwipeRefreshLayout.setProgressViewOffset(true, 200, 300);
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//            }
//        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        ViewStyleUtis.setRecyclerViewLine(getActivity(), mRecyclerView, 1);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setVerticalScrollBarEnabled(true);
        mRecyclerView.setHasFixedSize(true);

        int height = (int) getActivity().getResources().getDimension(R.dimen.abc_action_bar_default_height_material);
        mRecyclerView.setPadding(mRecyclerView.getPaddingLeft(), height,
                mRecyclerView.getPaddingRight(), mRecyclerView.getPaddingBottom());
//        MainActivity.hideToolBar(mRecyclerView, height);

    }

    public void requestData() {
        RequestDataUtils.RequestMessageListData(call, pageIndex);
    }

    OkHttpClientManager.StringCallback call = new OkHttpClientManager.StringCallback() {
        @Override
        public void onFailure(Request request, IOException e) {
            Log.d("response", e.toString());
//            mSwipeRefreshLayout.setRefreshing(false);
            ToastUtil.showToast(R.string.internet_error, 1000, R.drawable.ic_ab_drawer, Gravity.CENTER);
        }

        @Override
        public void onResponse(String response) {
            Log.d("response", response);
            mHandler.obtainMessage(INITDATA, response).sendToTarget();
//            mListData = JsonUtil.getdata(getActivity(),response);

        }
    };

    private void initData() {
        mNewsAdapter = new NewsAdapter(getActivity());
        mNewsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }
            @Override
            public void onItemLongClick(View view, int position) {
                ToastUtil.showToast(position + "");
            }
        });
        mRecyclerView.setAdapter(mNewsAdapter);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case INITDATA:
                    String data = (String) msg.obj;
//                    List<News> str = JsonUtil.getNewsListJson(getActivity(),
//                            data);
                    if (pageIndex == 0) {
                        mListData.clear();
                    }
//                    mListData = JsonUtil.getdata(getActivity(), data);
//                    for (News an : str) {
//                        mListData.add(an);
//                    }
                    mNewsAdapter.setData(mListData);
                    break;
            }
        }
    };
}
