package com.red.redmovie.movie.widget;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.red.redmovie.R;

import java.util.ArrayList;
import java.util.List;


public class MoviesFragment extends Fragment {

    public static int MOVIES_TYPE_HOT = 0;
    public static int MOVIES_TYPE_NEWS = 0;

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

    private void setupViewPager(ViewPager viewPager) {
        MoviePagerAdapter moviePagerAdapter = new MoviePagerAdapter(getChildFragmentManager());
    }

    public static class MoviePagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        MoviePagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }


        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }


}
