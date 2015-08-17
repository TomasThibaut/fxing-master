package cn.gc.fxing.base;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 宫成 on 15/8/10.
 */
public abstract class BaseFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView();
    }

    protected View rootView;

    /**
     * 子类必须实现来返回自己的视图
     */
    public abstract View initView();
}
