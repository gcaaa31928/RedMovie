package com.red.redmovie.movie.model;

import com.red.redmovie.beans.MoviesBean;

import java.util.List;

/**
 * Created by Red on 2016/10/20.
 */

public interface OnLoadMoviesListListener {
    void onSuccess(List<MoviesBean> list);
    void onFailure(String msg, Exception e);
}
