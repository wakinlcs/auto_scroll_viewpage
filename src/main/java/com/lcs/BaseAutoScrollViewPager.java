package com.lcs;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * @author by linchunshu
 * @function
 * @since 2018/5/28/028
 */

public abstract class BaseAutoScrollViewPager extends FrameLayout{

    //控制是否自动滑动
    protected boolean isAutoScroll = true;

    public abstract void setAutoScroll(boolean isAutoScroll);

    public BaseAutoScrollViewPager(@NonNull Context context) {
        super(context);
    }

    public BaseAutoScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseAutoScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void setAdapter(AutoScrollViewPagerAdapter iBindView);

    //停止滚动
    public abstract void stopAutoScroll();
    //开启滚动
    public abstract void startAutoScroll();
}
