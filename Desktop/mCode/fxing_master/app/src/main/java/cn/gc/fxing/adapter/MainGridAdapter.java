package cn.gc.fxing.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by 宫成 on 15/8/10.
 */
public class MainGridAdapter extends BaseAdapter {
    public MainGridAdapter() {
    }

    ArrayList<ImageView> gv_imageviews;

    public MainGridAdapter(ArrayList<ImageView> gv_imageviews) {
        if (gv_imageviews != null) {
            this.gv_imageviews = gv_imageviews;
        }
    }

    @Override
    public int getCount() {
        return gv_imageviews.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return gv_imageviews.get(position);
    }
}
