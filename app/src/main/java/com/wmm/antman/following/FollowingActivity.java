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

import android.app.Activity;
import android.content.ContentProvider;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wmm.antman.R;
import com.wmm.antman.data.Followers;
import com.wmm.antman.utils.ViewStyleUtis;

import java.util.List;

public class FollowingActivity extends Activity implements FollowingView, AdapterView.OnItemClickListener {

    private RecyclerView listView;
    private ProgressBar progressBar;
    private FollowingPresenter presenter;
    private FollowingAdapter mFollowingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.following_activity);
        listView = (RecyclerView) findViewById(R.id.recyclerview_following);
//        listView.setOnItemClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        initRecyclerView();
        presenter = new FollowingPresenterImpl(this);

        mFollowingAdapter = new FollowingAdapter(FollowingActivity.this);
        listView.setAdapter(mFollowingAdapter);

    }

    public void initRecyclerView() {
        ViewStyleUtis.setRecyclerViewLine(FollowingActivity.this, listView, 4);
        listView.setItemAnimator(new DefaultItemAnimator());
        listView.setVerticalScrollBarEnabled(true);
        listView.setHasFixedSize(true);
    }

    @Override protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.INVISIBLE);
    }

    @Override public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
    }

    @Override public void setItems(List<Followers> items) {
        mFollowingAdapter.setData(items);
    }

    @Override public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemClicked(position);
    }
}
