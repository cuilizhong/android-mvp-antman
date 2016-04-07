package com.wmm.antman.net;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wmm.antman.model.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangmingming on 2015/11/5 13:40.
 */
public class JsonUtil {

    /**
     * @param context
     * @param JSONString
     * @return Activity
     */

    public static Integer status = 111111;


    public static List<News> getNewsListJson(Context context,String JSONString) {
        List<News> newsList = null;
        if (null != JSONString) {
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            newsList = new ArrayList<News>();
            JsonObject jsonObject = parser.parse(JSONString.toString()).getAsJsonObject();
            JsonArray jsonArray = jsonObject.getAsJsonArray("data");
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonElement el = jsonArray.get(i);
                News news = gson.fromJson(el,
                        News.class);
                newsList.add(news);
            }
        }
        return newsList;
    }
}
