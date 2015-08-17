package cn.gc.fxing.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by 宫成 on 15/8/10.
 */
public class SplashPagerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<ImageView> imageViews;

    public SplashPagerAdapter(Context mContext, ArrayList<ImageView> imageViews) {
        this.mContext = mContext;
        this.imageViews = imageViews;
    }

    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView view = imageViews.get(position);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
