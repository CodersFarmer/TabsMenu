package com.heima.yqz.fragment;

import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Mryang on 2016/11/24.
 */

public class homeTabFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView tv = new TextView(container.getContext());
        tv.setTextSize(20);
        tv.setText("首页");
        tv.setTextColor(Color.RED);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }
}
