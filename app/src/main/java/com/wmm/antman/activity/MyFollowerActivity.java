package com.wmm.antman.activity;

import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.wmm.antman.R;
import com.wmm.antman.adapter.MyFollowersAdapterByDataBinding;
import com.wmm.antman.adapter.NewsAdapterByDataBinding;
import com.wmm.antman.base.BaseActivity;
import com.wmm.antman.bean.MyFollowersBean;
import com.wmm.antman.bean.NewByRetrofit;
import com.wmm.antman.databinding.ActivityMyFollwersBinding;
import com.wmm.antman.net.APIService;
import com.wmm.antman.net.ServiceGenerator;
import com.wmm.antman.utils.ToastUtil;
import com.wmm.antman.utils.ViewStyleUtis;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ming on 2016/4/13.
 */
public class MyFollowerActivity extends AppCompatActivity {

    private static final String TAG = "MyFollowerActivity";
    private ActivityMyFollwersBinding mActivityMyFollwersBinding;
    private APIService apiService;
    private static final int INITDATA = 1;
    private MyFollowersAdapterByDataBinding mMyFollowersAdapterByDataBinding;
    private List<MyFollowersBean> mList;
    private Call call;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMyFollwersBinding = DataBindingUtil.setContentView(this, R.layout.activity_my_follwers);
//        mActivityMyFollwersBinding.setModel(viewModel = new MainViewModel());
        initRecyclerView(mActivityMyFollwersBinding.RecyclerViewMyFollowers);
        initToolBar(mActivityMyFollwersBinding.includeToolbar.baseToolbar);
        repo();
        initData();
    }

    private void initToolBar(Toolbar toolbar) {
        supportPostponeEnterTransition();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setHapticFeedbackEnabled(true);
        toolbar.setTitle(R.string.my_followers);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initRecyclerView(RecyclerView recyclerViewMyFollowers) {
        ViewStyleUtis.setRecyclerViewLine(MyFollowerActivity.this, recyclerViewMyFollowers, 4);
        recyclerViewMyFollowers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMyFollowers.setVerticalScrollBarEnabled(true);
        recyclerViewMyFollowers.setHasFixedSize(true);
    }

    private void initData() {
        mList = new ArrayList<>();
        mMyFollowersAdapterByDataBinding = new MyFollowersAdapterByDataBinding(MyFollowerActivity.this, mList);
        mActivityMyFollwersBinding.RecyclerViewMyFollowers.setAdapter(mMyFollowersAdapterByDataBinding);
    }

    private void repo() {
        apiService = ServiceGenerator.createService(APIService.class);

        call = apiService.repoFollowersData("JakeWharton");

        call.enqueue(new Callback<List<MyFollowersBean>>() {
            @Override
            public void onResponse(Call<List<MyFollowersBean>> call, Response<List<MyFollowersBean>> response) {
                ToastUtil.showToast("onResponse");
                mHandler.obtainMessage(INITDATA, response.body()).sendToTarget();
                Log.d(TAG, response.body().get(0).getAvatar_url() + "");
            }

            @Override
            public void onFailure(Call<List<MyFollowersBean>> call, Throwable t) {
                Log.d(TAG, t.getMessage());
                ToastUtil.showToast("onFailure");
            }
        });
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case INITDATA:
                    List<MyFollowersBean> mList = (List<MyFollowersBean>) msg.obj;
                    mMyFollowersAdapterByDataBinding.setData(mList);
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        call.cancel();
        super.onDestroy();
    }
}
