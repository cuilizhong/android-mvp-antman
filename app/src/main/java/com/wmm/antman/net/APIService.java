package com.wmm.antman.net;

import com.wmm.antman.bean.MyFollowersBean;
import com.wmm.antman.bean.NewByRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by ming on 2016/4/5.
 */
public interface APIService {
//    @GET("/repos/{owner}/{repo}/contributors")
//    Call<List<NewByRetrofit>> repoNewsData(
//            @FieldMap Map<String, String> params);

    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<NewByRetrofit>> repoNewsData(
            @Path("owner") String owner,
            @Path("repo") String repo);

    //请求我的粉丝
    @GET("/users/{user}/followers")
    Call<List<MyFollowersBean>> repoFollowersData(
            @Path("user") String user);


}
