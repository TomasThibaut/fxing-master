package cn.gc.fxing.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import cn.gc.fxing.app.MyApp;

/**
 * Created by 宫成 on 15/8/11.
 * 网络请求相关工具类
 */
public class NetUtils {
    private static Context mContext;
    private static String mResult;

    public static void getDataFromNet(final Handler mHandler, Context context, String url) {

        /**显示加载loading*/
        MyApp.getInstance().showLoading(context);

        mContext = context;

        new HttpUtils(5000).send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
//                LogUtils.Log(MainActivity.TAG, responseInfo.result);
                mResult = responseInfo.result;
                Message mMsg = mHandler.obtainMessage();
                mMsg.obj = mResult;
                mMsg.what = 1;
                mMsg.sendToTarget();
            }

            @Override
            public void onFailure(HttpException error, String msg) {
//                LogUtils.Log(MainActivity.TAG, msg);
                mResult = msg;
                Message mMsg = mHandler.obtainMessage();
                mMsg.obj = msg;
                mMsg.what = 0;
                mMsg.sendToTarget();
            }
        });
    }
}
