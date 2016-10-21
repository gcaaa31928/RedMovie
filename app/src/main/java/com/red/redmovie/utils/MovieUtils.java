package com.red.redmovie.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.red.redmovie.beans.MoviesBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Red on 2016/10/21.
 */

public class MovieUtils {

    private static final String TAG = "MovieUtils";

    public static List<MoviesBean> readJsonMovieBean(String res, String value) {
        List<MoviesBean> beans = new ArrayList<>();
        try {
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(res).getAsJsonObject();
            JsonElement jsonElement = jsonObject.get(value);
            if (jsonElement == null) {
                return null;
            }
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jo = jsonArray.get(i).getAsJsonObject();
                MoviesBean moviesBean = JsonUtils.deserialize(jo, MoviesBean.class);
                beans.add(moviesBean);
            }
        } catch (Exception e) {
            LogUtils.e(TAG, "readJsonMovieBean", e);
        }
        return beans;
    }
}
