package com.wmm.antman.followers;

import android.support.annotation.NonNull;

import com.wmm.antman.base.BasePresenter;
import com.wmm.antman.base.BaseView;
import com.wmm.antman.data.Followers;

import java.util.List;

/**
 * Created by ming on 2016/4/15.
 */
public interface FollowersContract {
    interface View extends BaseView<Presenter> {

        void showFollowers(List<Followers> followersList);

        void showProgress();

        void hideProgress();

        void showError();

        void showFollowersDetails(String id);

    }

    interface Presenter extends BasePresenter {

        void getRepo();

        void openFollowersDetails(@NonNull Followers requestedTask);

        void result(int requestCode, int resultCode);

    }
}
