package com.wmm.antman.model;

import android.util.Log;

import com.wmm.antman.bean.NewByRetrofit;
import com.wmm.antman.model.modelview.NewsItemViewModel;
import com.wmm.antman.net.APIService;
import com.wmm.antman.net.ServiceGenerator;
import com.wmm.antman.utils.ToastUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ming on 2016/4/8.
 */
public class ApiServiceModel {

    private APIService apiService;
    private NewsItemViewModel newsItemViewModel;

    public ApiServiceModel(NewsItemViewModel viewModel){
        this.newsItemViewModel = viewModel;
        this.apiService = ServiceGenerator.createService(APIService.class);
    }
}
