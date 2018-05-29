package com.lcs;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.ViewGroup;


/**
 * @author by linchunshu
 * @function
 * @since 2018/5/28/028
 */

public class AutoScrollViewPager extends BaseAutoScrollViewPager implements ViewPager.OnPageChangeListener {


    private BasicViewPager mViewPager;

    private BasicViewPagerAdapter basicViewPagerAdapter;

    private AutoScrollViewPagerAdapter autoScrollViewPagerAdapter;

    private Runnable autoRunnable;//控制轮播的现成

    private int delayMills = 1500;

    @Override
    public void setAutoScroll(boolean isAutoScroll) {
        this.isAutoScroll = isAutoScroll;
    }

    public AutoScrollViewPager(@NonNull Context context) {
        this(context, null);
    }

    public AutoScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    @Override
    public void setAdapter(AutoScrollViewPagerAdapter adapter) {
        autoScrollViewPagerAdapter = adapter;
        basicViewPagerAdapter = new BasicViewPagerAdapter();
        basicViewPagerAdapter.setiBindView(autoScrollViewPagerAdapter);
        autoScrollViewPagerAdapter.setCount(basicViewPagerAdapter.getCount());
        mViewPager.setAdapter(basicViewPagerAdapter);

        mViewPager.setCurrentItem(basicViewPagerAdapter.getCount() * 100);

        autoRunnable = new Runnable() {
            @Override
            public void run() {
                if(isAutoScroll){
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                }
            }
        };
        //直接自动启动滚动
        postDelayed(autoRunnable,delayMills);//启动一秒执行一次
    }

    @Override
    public void stopAutoScroll() {
        removeCallbacks(autoRunnable);
        setAutoScroll(false);
    }

    @Override
    public void startAutoScroll() {
        postDelayed(autoRunnable,delayMills);//启动一秒执行一次
        setAutoScroll(true);
    }

    private void initViews(Context context) {
        float scrollFactor = 10.0f;

        mViewPager = new BasicViewPager(context);
        mViewPager.setId(R.id.base_viewpager);
        LayoutParams flLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mViewPager.setLayoutParams(flLayoutParams);
        mViewPager.addOnPageChangeListener(this);
        addView(mViewPager);
        mViewPager.setScrollDurationFactor(scrollFactor);

        //设置viewpager切换动画，如果不想就注释掉，也可以自行更换
        mViewPager.setPageTransformer(true,new DepthPageTransformer());

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if(!isAutoScroll){
            //禁止自动滑动
            return;
        }
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                //当拖拽的时候移除滑动线程
                removeCallbacks(autoRunnable);
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                //当滚动停止的时候启动滑动线程
                postDelayed(autoRunnable,delayMills);
                break;
        }
    }
}
