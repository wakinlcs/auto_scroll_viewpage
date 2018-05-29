package com.lcs;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by linchunshu
 * @function
 * @since 2018/5/28/028
 */

public class BasicViewPagerAdapter extends PagerAdapter {

    //防止两张图的时候会出现白屏现象
    private List<View> mAssistViews;
    private List<View> mViews;

    private IBindView iBindView;

    public void setiBindView(IBindView iBindView) {
        this.iBindView = iBindView;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        int count = iBindView.getCount();
        //装数据
        if (null == mViews) {
            mViews = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                mViews.add(LayoutInflater.from(container.getContext()).inflate(iBindView.onLayoutId(), container, false));
            }
        }
        if (count == 2 && null == mAssistViews) {
            //解决两张滑动白屏现象
            mAssistViews = new ArrayList<>(mViews);
            mAssistViews.add(LayoutInflater.from(container.getContext()).inflate(iBindView.onLayoutId(), container, false));
        }

        View itemView = null;

        if (null != mAssistViews) {
            itemView = mAssistViews.get(position % mAssistViews.size());
        }else{
            itemView = mViews.get(position % mViews.size());
        }

        ViewParent parent = itemView.getParent();
        if (parent != null) {
            ViewGroup group = (ViewGroup) parent;
            //这里要强制移除父子关系，一个view只能有一个parent
            group.removeView(itemView);
        }

        iBindView.onBindView(itemView, position % mViews.size());

        container.addView(itemView);

        return itemView;
    }

    @Override
    public int getCount() {
        return iBindView.getCount() == 1 ? iBindView.getCount() : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
