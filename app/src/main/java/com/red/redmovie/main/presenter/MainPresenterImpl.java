package com.red.redmovie.main.presenter;

import com.red.redmovie.main.view.MainView;

/**
 * Created by Red on 2016/10/19.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView mMainView;

    public MainPresenterImpl(MainView mainView) {
        this.mMainView = mainView;
    }

}
