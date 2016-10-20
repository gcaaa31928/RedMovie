package com.red.redmovie.profile;

/**
 * Created by Red on 2016/10/21.
 */

public class Urls {
    private final static String API_KEY = "62d7ef004fb9942c77a27bdcbb08af39";
    private final static String LANGUAGE = "zh-TW";
    private final static String HOST = "https://api.themoviedb.org/3/";

    public final static String HOT_URL = HOST + "discover/movie?" + "api_key=" +
            API_KEY + "&language=" + LANGUAGE;
}
