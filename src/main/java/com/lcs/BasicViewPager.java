package com.lcs;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.animation.Interpolator;

import java.lang.reflect.Field;

/**
 * @author by linchunshu
 * @function
 * @since 2018/5/28/028
 */

public class BasicViewPager extends ViewPager{

    private ViewPagerScroller mScroller;


    public BasicViewPager(Context context) {
        this(context,null);
    }

    public BasicViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        initViews(context);
    }

    /**
     * 通过反射获取mScroller方法来设置滑动时间
     * @param context
     */
    private void initViews(Context context) {
        try {
            Class<?> viewpager = ViewPager.class;
            Field scroller = viewpager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            Field interpolator = viewpager.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);

            mScroller = new ViewPagerScroller(getContext() , (Interpolator) interpolator.get(null));
            scroller.set(this, mScroller);
        } catch (Exception e) {
        }
    }

    /**
     * 设置该值后，具体速度为正常速度的1/scrollFactor倍
     *
     * @param scrollFactor 滑动因子
     */
    public void setScrollDurationFactor(float scrollFactor) {
        mScroller.setScrollDurationFactor(scrollFactor);
    }
}
