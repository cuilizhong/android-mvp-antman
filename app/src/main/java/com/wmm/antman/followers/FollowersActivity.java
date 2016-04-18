package com.wmm.antman.followers;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wmm.antman.R;
import com.wmm.antman.data.Followers;
import com.wmm.antman.databinding.FollowersActivityBinding;
import com.wmm.antman.utils.ViewStyleUtis;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ming on 2016/4/15.
 */
public class FollowersActivity extends AppCompatActivity implements FollowersContract.View {

    private FollowersActivityBinding mFollowersActivityBinding;
    private FollowersContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private FollowersAdapter mFollowersAdapter;
    private FollowersViewModel mFollowersViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFollowersActivityBinding = DataBindingUtil.setContentView(this, R.layout.followers_activity);
        initRecyclerView();
        mPresenter = new FollowersPresenter(this);
        mFollowersViewModel = new FollowersViewModel(getApplicationContext(), mPresenter);
        mFollowersActivityBinding.setFollowersViewModel(mFollowersViewModel);
        mFollowersActivityBinding.setActionHandler(mPresenter);
        mFollowersAdapter = new FollowersAdapter(FollowersActivity.this, new ArrayList<Followers>(0), mPresenter);
        mFollowersActivityBinding.recyclerviewFollowers.setAdapter(mFollowersAdapter);

    }


    public void initRecyclerView() {
        mRecyclerView = mFollowersActivityBinding.recyclerviewFollowers;
        ViewStyleUtis.setRecyclerViewLine(FollowersActivity.this, mRecyclerView, 4);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setVerticalScrollBarEnabled(true);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getRepo();
    }

    @Override
    public void showFollowersDetails(String id) {

    }


    @Override
    public void showProgress() {
        mFollowersActivityBinding.progressFollowers.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mFollowersActivityBinding.progressFollowers.setVisibility(View.GONE);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showFollowers(List<Followers> followersList) {
        mFollowersAdapter.replaceData(followersList);
        mFollowersViewModel.setmFollowersListSize(followersList.size());
    }

    @Override
    public void setPresenter(@NonNull FollowersContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
