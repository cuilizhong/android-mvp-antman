package com.wmm.antman.net;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangmingming on 2015/11/20 14:32.
 */
public class RequestDataUtils {
    public static Map<String, String> params = new HashMap<>();
    public static int pageNum = 10;
    private final static String TOKEN = "token";
    private final static String IP = "http://www.whjd.sh.cn";

    public static void RequestMessageListData(OkHttpClientManager.StringCallback call, int pageIndex) {
        params.put(TOKEN, "anonymous");
        params.put("activityTagId", "921ed135b20f41bdab8c22688eaf850e");
        params.put("pageIndex", String.valueOf(pageIndex));
        params.put("pageNum", String.valueOf(pageNum));
        try {
            OkHttpClientManager.getAsString(IP + "/appActivity/appActivityListIndex.do", call, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * MessageActivityDetail 详情请求
     *
     * @param call
     * @param activityId
     * @param applyType
     */
    public static void RequestMessageDetailData(OkHttpClientManager.StringCallback call, String activityId, String applyType) {
        params.put("activityId", activityId);
        params.put("applyType", applyType);
        params.put(TOKEN, TOKEN);
        OkHttpClientManager.postAsyn(IP + "/appActivity/activityAppDetail.do", call, params);
    }
}
