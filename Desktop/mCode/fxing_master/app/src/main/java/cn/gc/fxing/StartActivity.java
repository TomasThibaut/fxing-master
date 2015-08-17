package cn.gc.fxing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;

import cn.gc.fxing.domain.LaunchADBean;
import cn.gc.fxing.util.CacheUtils;
import cn.gc.fxing.util.Utils;


public class StartActivity extends Activity {

    private final String TAG = StartActivity.class.getSimpleName();

    @ViewInject(R.id.iv_start)
    private ImageView iv_start;
    @ViewInject(R.id.iv_ad)
    private ImageView iv_ad;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ViewUtils.inject(this);
        mContext = this;
        /**得到广告数据*/
        getADData();
        /**启动动画*/
        startAnimation();
    }

    private int token;

    /**
     * 得到广告数据
     */
    private void getADData() {
        String url_ad = CacheUtils.getString(mContext, "URL_AD", "");
        if (TextUtils.isEmpty(url_ad)) {
            token = 0;
        } else {
            processADFromNet();//请求网络获取数据
        }
    }

    private void processADFromNet() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, Utils.GET_LAUNCH_AD_URL, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.e(TAG, "result = " + responseInfo.result);
                String json = responseInfo.result;
                LaunchADBean launchADBean = new Gson().fromJson(json, LaunchADBean.class);
                String jpgURL = launchADBean.ad_list.get(0).material;
                loadAD(jpgURL);

            }

            @Override
            public void onFailure(HttpException error, String msg) {
                Log.e(TAG, "onFailure msg = " + msg);
            }
        });
    }

    private void loadAD(String jpgURL) {
        BitmapUtils bitmapUtils = new BitmapUtils(mContext);
        bitmapUtils.display(iv_ad, jpgURL);
    }

    private void startAnimation() {

        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(1000);
        aa.setFillAfter(true);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Intent intent = new Intent();

                if (CacheUtils.getBoolean(mContext, "isFirstLogin", true)) {
                    intent.setClass(mContext, SplashActivity.class);
                    CacheUtils.putBoolean(mContext, "isFirstLogin", false);
                } else {
                    intent.setClass(mContext, MainActivity.class);
                }
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        /**启动动画*/
        iv_start.startAnimation(aa);
    }
}
