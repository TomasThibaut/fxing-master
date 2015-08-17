package cn.gc.fxing.app;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;

import cn.gc.fxing.R;
import cn.gc.fxing.view.LoadingDialog;

/**
 * Created by 宫成 on 15/8/13.
 * 自定义Application类,全局单例,在所有组件初始化之前初始化
 */
public class MyApp extends Application {


    private LoadingDialog mLoadingDialog;
    private static MyApp mApp;

    @Override
    public void onCreate() {
        init();
    }

    private void init() {
    }

    /**
     * 显示加载页面
     */
    public void showLoading(Context context) {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            return;
        } else {

            AlertDialog.Builder builder = new LoadingDialog.Builder(context, R.style.LoadingDialog);
            builder.setMessage("加载中...");
            builder.show();
        }
    }

    /**
     * 隐藏加载页面
     */
    public void dismissLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    /**
     * 得到实例
     */
    public static MyApp getInstance() {
        if (mApp == null) {
            mApp = new MyApp();
            return mApp;
        }
        return mApp;
    }
}
