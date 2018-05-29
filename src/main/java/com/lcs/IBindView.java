package com.lcs;

import android.view.View;

/**
 * @author by linchunshu
 * @function
 * @since 2018/5/29/029
 */

public abstract class IBindView {


    public abstract int onLayoutId();

    public abstract void onBindView(View viewItem, int position);

    public int count;

    public abstract int getCount();

    public void setCount(int count) {
        this.count = count;
    }
}
