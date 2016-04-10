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
import com.wmm.antman.bean.NewByRetrofit;
import com.wmm.antman.databinding.NewsFragmentBinding;
import com.wmm.antman.bean.News;
import com.wmm.antman.databinding.NewsItemBinding;
import com.wmm.antman.model.ApiServiceModel;
import com.wmm.antman.model.modelview.NewsItemViewModel;
import com.wmm.antman.net.APIService;
import com.wmm.antman.net.JsonUtil;
import com.wmm.antman.net.OkHttpClientManager;
import com.wmm.antman.net.RequestDataUtils;
import com.wmm.antman.net.ServiceGenerator;
import com.wmm.antman.utils.ToastUtil;
import com.wmm.antman.utils.ViewStyleUtis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ming on 2016/4/7.
 */
public class NewsFragmentByDataBinding extends Fragment {

    private static final String ARG_POSITION = "position";
    private List<NewByRetrofit> mListData = new ArrayList<>();
    private static NewsFragmentByDataBinding mNewsFragmentByDataBinding = null;
    private NewsFragmentBinding mNewsFragmentBinding;
    private NewsItemBinding mNewsItemBinding;
    private NewsItemViewModel mNewsItemViewModel;
    private NewsAdapterByDataBinding mNewsAdapterByDataBinding;
    private ApiServiceModel mApiServiceModel;
    private APIService apiService;
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
//        requestData();
        repo();
        initData();
        return mNewsFragmentBinding.getRoot();
    }

    private void repo() {
//        mNewsItemBinding.setModel(mNewsItemViewModel = new NewsItemViewModel());
        mNewsItemViewModel = new NewsItemViewModel();
        apiService = ServiceGenerator.createService(APIService.class);

        Call call= apiService.repoNewsData("square", "retrofit");

        call.enqueue(new Callback<List<NewByRetrofit>>() {
            @Override
            public void onResponse(Call<List<NewByRetrofit>> call, Response<List<NewByRetrofit>> response) {
                //                    mListData = call.execute().body();
                mHandler.obtainMessage(INITDATA, response.body()).sendToTarget();
                ToastUtil.showToast("onResponse");
                Log.d("onResponse",response.body().get(0).getAvatar_url()+"");
            }

            @Override
            public void onFailure(Call<List<NewByRetrofit>> call, Throwable t) {
                try {
                    mListData = call.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("onFailure",t.getMessage());
                ToastUtil.showToast("onFailure");
            }
        });
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

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case INITDATA:
                    List<NewByRetrofit> newsList = (List<NewByRetrofit>)msg.obj;
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

