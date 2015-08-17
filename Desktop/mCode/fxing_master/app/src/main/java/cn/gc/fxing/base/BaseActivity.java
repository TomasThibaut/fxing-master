package cn.gc.fxing.base;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;

import cn.gc.fxing.R;

/**
 * Created by 宫成 on 15/8/10.
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        init();
        setFragment();
    }

    private void setFragment() {
        FragmentTransaction transaction = this.getFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_base_aty, passFragment());
        transaction.commit();
    }

    public abstract Fragment passFragment();

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        findViewById(R.id.tv_lastIndex).setOnClickListener(this);
        findViewById(R.id.tv_mostPopular).setOnClickListener(this);
        findViewById(R.id.ib_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.ib_title_pull).setOnClickListener(this);
        findViewById(R.id.ib_change).setOnClickListener(this);

    }


    @ViewInject(R.id.tv_title)
    protected TextView tv_title;
   /* @ViewInject(R.id.tv_lastIndex)
    protected TextView tv_lastIndex;
    @ViewInject(R.id.tv_mostPopular)
    protected TextView tv_mostPopular;
    @ViewInject(R.id.ib_back)
    protected ImageButton ib_back;
    @ViewInject(R.id.ib_title_pull)
    protected ImageButton ib_title_pull;
    @ViewInject(R.id.ib_change)
    protected ImageButton ib_change;*/

    /**
     * 子类按需要重写本方法
     */
    @Override
    public void onClick(View v) {

    }
}
