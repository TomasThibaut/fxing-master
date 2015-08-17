package cn.gc.fxing.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.gc.fxing.base.BaseFragment;

/**
 * Created by 宫成 on 15/8/14 下午6:18.
 */
public class FeatureFgt extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(getActivity());
        tv.setText("我是精选页面");
        rootView = tv;
    }

    @Override
    public View initView() {
        if (rootView != null) return rootView;
        return null;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}
