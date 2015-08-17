package cn.gc.fxing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import cn.gc.fxing.adapter.SplashPagerAdapter;


public class SplashActivity extends ActionBarActivity {
    private static final int WHAT_ENTER_MAIN = 1;
    @ViewInject(R.id.viewPager_splash)
    private ViewPager mViewPager;
    private Context mContext;
    private SplashPagerAdapter mPagerAdapter;
    private ArrayList<ImageView> imageViews;
    private int[] resuorces;
    private MyPagerChangedListener mListener;
    private android.os.Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Intent intent = new Intent();
            switch (msg.what) {
                case WHAT_ENTER_MAIN:
                    intent.setClass(mContext, MainActivity.class);
                    break;
                default:
                    break;
            }
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ViewUtils.inject(this);
        mContext = this;
        resuorces = new int[]{
                R.mipmap.icon_guide_1, R.mipmap.icon_guide_2, R.mipmap.icon_guide_3
        };
        imageViews = new ArrayList<>();
        ImageView iv = null;
        for (int i = 0; i < resuorces.length; i++) {
            iv = new ImageView(this);
            iv.setImageResource(resuorces[i]);
            imageViews.add(iv);
        }
        mPagerAdapter = new SplashPagerAdapter(mContext, imageViews);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOnPageChangeListener(mListener);
    }

    private class MyPagerChangedListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            /**进入主界面*/
            if (position == 2) {
                startActivity(new Intent(mContext, MainActivity.class));
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
