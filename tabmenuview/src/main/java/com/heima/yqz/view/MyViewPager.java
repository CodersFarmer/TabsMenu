package com.heima.yqz.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Mryang on 2016/11/24.
 * 实现viewPager的滑动的拦截，实现只让tab的RadioButton来控制viewPager的
 * 界面切换。
 */

public class MyViewPager extends ViewPager {
    public MyViewPager(Context context) {
        super(context);
    }
    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

    }
    /**
     * onInterceptTouchEvent方法
     * return false,表示不拦截，可以传递给viewPager的子view进行事件的传递
     * */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
    /**
     * onTouchEvent方法
     * return false，表示自己不处理这个事件，而把它往上传
     * 如果返回了true，则会接受并消费该事件。
     * */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
