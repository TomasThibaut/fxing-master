package cn.gc.fxing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import cn.gc.fxing.adapter.MainGridAdapter;
import cn.gc.fxing.adapter.MainPagerAdapter;
import cn.gc.fxing.app.MyApp;
import cn.gc.fxing.aty.FeatureAty;
import cn.gc.fxing.domain.Bean_ViewPager_Main;
import cn.gc.fxing.util.Constants;
import cn.gc.fxing.util.NetUtils;
import cn.gc.fxing.util.ToastUtils;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    @ViewInject(R.id.viewpager_main)
    private ViewPager mViewPager;
    @ViewInject(R.id.ed_main)
    private EditText mEditText;
    @ViewInject(R.id.gridview_main)
    private GridView mGridView;

    private Context mContext;
    private ArrayList<ImageView> imageViews;
    private MainPagerAdapter mPgAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        mContext = this;
        init();
        getData();
        //TODO 有大bug
//        playTopNews();
    }

    private Thread mThread;

    private void playTopNews() {

        mThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    mHandler.sendEmptyMessageDelayed(2, 1000);
                }
            }
        };
        mThread.start();
    }

    @Override
    protected void onPause() {
        if (mThread != null) {
            mThread.interrupt();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private MainHandler mHandler;
    private boolean flag;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        switch (position) {
            case 0:
                //精选页面
                intent.setClass(mContext, FeatureAty.class);
                break;
            case 1:
                //页面
                break;
            case 2:
                //页面
                break;
            case 3:
                //页面
                break;
            case 4:
                //页面
                break;
            case 5:
                //页面
                break;
            case 6:
                //页面
                break;
            case 7:
                //页面
                break;
            case 8:
                //页面
                break;

        }
        startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    /**
     * 主页面的handler,用来实现两次点击back键退出和轮播图效果
     */
    private class MainHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    flag = false;
                    break;
                case 2:
                    int position = mViewPager.getCurrentItem();
                    mViewPager.setCurrentItem(++position);
                    break;
            }
        }
    }

    /**
     * 点击两次 返回键 退出
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ToastUtils.showToast(this, "点击两次退出应用");
            flag = !flag;
            if (mHandler != null) {
                mHandler.sendEmptyMessageDelayed(1, 1500);
            }
        }
        if (!flag) {
            return super.onKeyUp(keyCode, event);
        }
        return false;
    }


    private static NetAccessHandler mNetAccessHandler;

    /**
     * 网络请求handler
     */
    private class NetAccessHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {

            mPgAdapter = new MainPagerAdapter(mContext, imageViews);
            switch (msg.what) {
                case 0://请求失败,使用已经配置好的默认图片
//                    LogUtils.Log(TAG, msg.obj.toString());

                    mViewPager.setAdapter(mPgAdapter);
                    break;
                case 1://请求成功,使用网络图片替换默认图片

                    /**隐藏加载loading*/
                    MyApp.getInstance().dismissLoading();

//                    LogUtils.Log(TAG, msg.obj.toString());
                    String json = msg.obj.toString();
                    if (json != null) {
                        Bean_ViewPager_Main viewPagerBean = new Gson().fromJson(json, Bean_ViewPager_Main.class);
                        imageViews.clear();
                        for (int i = 0; i < viewPagerBean.data.size(); i++) {
                            ImageView iv = new ImageView(mContext);
                            new BitmapUtils(mContext).display(iv, viewPagerBean.data.get(i).picurl);
//                            LogUtils.Log(TAG, viewPagerBean.data.get(i));
                            imageViews.add(iv);
                        }
                    }
                    mPgAdapter = new MainPagerAdapter(mContext, imageViews);
                    mViewPager.setAdapter(mPgAdapter);
                    break;
            }
        }
    }

    /**
     * 从三级缓存来获取数据
     */
    private void getData() {
        /**TODO 缓存取数据*/

        /**通过网络获取*/
        NetUtils.getDataFromNet(mNetAccessHandler, mContext, Constants.GET_BUSSINISS_GALLERY_FLOW_URL);

    }

    private ArrayList<ImageView> gv_Imageviews;

    private void init() {

        mHandler = new MainHandler();
        mNetAccessHandler = new NetAccessHandler();

        /**加载默认的轮播图图片*/
        imageViews = new ArrayList<>();
        initImageviews();

        /**加载GridView*/
        gv_Imageviews = new ArrayList<>();
        initGridViewImages();
        mGridView.setAdapter(new MainGridAdapter(gv_Imageviews));

        /**添加点击监听*/
        mGridView.setOnItemClickListener(this);

    }

    /**
     * GridView的视图加载
     */
    private void initGridViewImages() {
        int[] pics = new int[]{
                R.mipmap.main_feature_new, R.mipmap.main_movie_new, R.mipmap.main_tv_new, R.mipmap.main_variety_new
                , R.mipmap.main_catroon_new, R.mipmap.main_entertainment_new, R.mipmap.main_sport_new,
                R.mipmap.main_rank_new, R.mipmap.main_hotapp_new
        };
        for (int i = 0; i < pics.length; i++) {
            ImageView iv = new ImageView(mContext);
            iv.setImageResource(pics[i]);
            gv_Imageviews.add(iv);
        }
    }

    /**
     * 如果网络请求不到的话就加载默认图片
     */
    private void initImageviews() {
        for (int i = 0; i < 8; i++) {
            ImageView iv = new ImageView(mContext);
            iv.setImageResource(R.mipmap.icon_loading_default);
            imageViews.add(iv);
        }
    }
}
