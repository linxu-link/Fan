package com.link.librarymodule.base.adapter;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by wujia on 2017/7/26.
 * 说明：
 */

public class FixPagerAdapter extends FragmentPagerAdapter {
    private String[] titles;

    private List<Fragment> fragments = null;

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public FixPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        if (fragments.isEmpty()) {
            return 0;
        }
        return fragments.size();
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) super.instantiateItem(container, position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
