package com.red.redmovie.movie.widget;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.red.redmovie.R;


public class MoviesFragment extends Fragment {

    private TabLayout mTableLayout;
    private ViewPager mViewPager;

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        mTableLayout = (TabLayout) view.findViewById(R.id.tabs);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(3);
        mTableLayout.addTab(mTableLayout.newTab().setText(R.string.hot));
        mTableLayout.addTab(mTableLayout.newTab().setText(R.string.news));
        mTableLayout.setupWithViewPager(mViewPager);
        return view;
    }


}
