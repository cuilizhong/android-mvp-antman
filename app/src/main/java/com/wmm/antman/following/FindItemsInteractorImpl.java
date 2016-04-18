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

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.wmm.antman.data.Followers;
import com.wmm.antman.net.APIService;
import com.wmm.antman.net.ServiceGenerator;
import com.wmm.antman.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindItemsInteractorImpl implements FindItemsInteractor {
    protected APIService apiService;
    protected Call call;
    protected List<Followers> followersList;

    @Override
    public void findItems(final OnFinishedListener listener) {
        createArrayList();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onFinished(followersList);
            }
        }, 2000);
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    followersList = (List<Followers>)msg.obj;
                    break;
            }
        }
    };

    private void createArrayList() {
        followersList =new ArrayList<>();
        apiService = ServiceGenerator.createService(APIService.class);
        call = apiService.repoFollowersData("JakeWharton");

        call.enqueue(new Callback<List<Followers>>() {
            @Override
            public void onResponse(Call<List<Followers>> call, Response<List<Followers>> response) {
                ToastUtil.showToast("onResponse"+response.body().get(0).getAvatar_url());
                mHandler.obtainMessage(1,response.body()).sendToTarget();
            }

            @Override
            public void onFailure(Call<List<Followers>> call, Throwable t) {
                ToastUtil.showToast("onFailure");
            }
        });
    }
}
