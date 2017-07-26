package com.heima.yqz.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Mryang on 2016/11/24.
 */

public class TabAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String[] names = new String[]{
            "首页", "新闻中心", "智慧服务", "政务", "设置"
    };
    /**
     * 通过构造函数传递数据
     * */
    public TabAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    /**
     * 为了满足TabPageIndicator设置头
     * */
    @Override
    public CharSequence getPageTitle(int position) {
        return names[position];
    }
}
