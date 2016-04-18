package com.wmm.antman.followers;

import android.support.annotation.NonNull;

import com.wmm.antman.data.Followers;
import com.wmm.antman.net.APIService;
import com.wmm.antman.net.ServiceGenerator;
import com.wmm.antman.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ming on 2016/4/15.
 */
public class FollowersPresenter implements FollowersContract.Presenter {
    protected APIService apiService;
    protected Call call;
    protected FollowersContract.View mFollowersDetailView;


    public FollowersPresenter(@NonNull FollowersContract.View view) {
        mFollowersDetailView = view;
        mFollowersDetailView.showProgress();
        mFollowersDetailView.setPresenter(this);
    }

    @Override
    public void getRepo() {

        apiService = ServiceGenerator.createService(APIService.class);
        call = apiService.repoFollowersData("JakeWharton");

        call.enqueue(new Callback<List<Followers>>() {
            @Override
            public void onResponse(Call<List<Followers>> call, Response<List<Followers>> response) {
                List<Followers> followersList = new ArrayList<>();
                ToastUtil.showToast("onResponse");
                followersList = response.body();
                mFollowersDetailView.hideProgress();
                mFollowersDetailView.showFollowers(followersList);
            }

            @Override
            public void onFailure(Call<List<Followers>> call, Throwable t) {
                ToastUtil.showToast("onFailure");
                mFollowersDetailView.showError();
            }
        });

    }

    @Override
    public void openFollowersDetails(@NonNull Followers followers) {

        mFollowersDetailView.showFollowersDetails(followers.getLogin());

    }

    @Override
    public void result(int requestCode, int resultCode) {

    }
}
