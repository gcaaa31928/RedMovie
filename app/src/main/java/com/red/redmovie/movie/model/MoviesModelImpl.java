package com.red.redmovie.movie.model;

import com.red.redmovie.beans.MoviesBean;
import com.red.redmovie.utils.LogUtils;
import com.red.redmovie.utils.MovieUtils;
import com.red.redmovie.utils.OkHttpUtils;

import java.util.List;

/**
 * Created by Red on 2016/10/20.
 */

public class MoviesModelImpl implements MoviesModel {
    @Override
    public void loadMovies(String url, int type,final OnLoadMoviesListListener listener) {
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                List<MoviesBean> beans = MovieUtils.readJsonMovieBean(response, "results");
                listener.onSuccess(beans);
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("load news list failure.", e);
            }
        };
        OkHttpUtils.get(url, loadNewsCallback);
    }
}
