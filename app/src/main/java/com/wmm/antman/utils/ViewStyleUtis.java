package com.wmm.antman.utils;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by ming on 2016/3/24.
 */
public class ViewStyleUtis {

    public static void setRecyclerViewLine(Context context, RecyclerView mRecyclerView, int line) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, line, LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
    }

    public static void setRecyclerViewHorizontal(Context context, RecyclerView mRecyclerView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }
    public static void setRecyclerViewVertical(Context context, RecyclerView mRecyclerView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }


}
