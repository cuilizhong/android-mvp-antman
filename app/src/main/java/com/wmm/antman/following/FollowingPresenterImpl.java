/*
 *
 *  * Copyright (C) 2014 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.wmm.antman.following;

import com.wmm.antman.data.Followers;

import java.util.List;

public class FollowingPresenterImpl implements FollowingPresenter, FindItemsInteractor.OnFinishedListener {

    private FollowingView followingView;
    private FindItemsInteractor findItemsInteractor;

    public FollowingPresenterImpl(FollowingView followingView) {
        this.followingView = followingView;
        findItemsInteractor = new FindItemsInteractorImpl();
    }

    @Override public void onResume() {
        if (followingView != null) {
            followingView.showProgress();
        }

        findItemsInteractor.findItems(this);
    }

    @Override public void onItemClicked(int position) {
        if (followingView != null) {
            followingView.showMessage(String.format("Position %d clicked", position + 1));
        }
    }

    @Override public void onDestroy() {
        followingView = null;
    }

    @Override public void onFinished(List<Followers> items) {
        if (followingView != null) {
            followingView.setItems(items);
            followingView.hideProgress();
        }
    }
}
