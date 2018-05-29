package com.lcs.com.mybanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lcs.AutoScrollViewPager;
import com.lcs.AutoScrollViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    AutoScrollViewPager autoScrollViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoScrollViewPager = (AutoScrollViewPager) findViewById(R.id.scrollView);

        final int[] images = {R.drawable.cat1,R.drawable.cat2};
        autoScrollViewPager.setAutoScroll(true);
        autoScrollViewPager.setAdapter(new AutoScrollViewPagerAdapter() {
            @Override
            public int onLayoutId() {
                return R.layout.image_view;
            }

            @Override
            public void onBindView(View viewItem, int position) {
                ((ImageView)viewItem).setImageResource(images[position]);
            }

            @Override
            public int getCount() {
                return images.length;
            }
        });

    }
}
