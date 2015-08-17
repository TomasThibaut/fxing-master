package cn.gc.customerviews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import cn.gc.customerviews.slide_delete.SlideDelete;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private Button btn_slide_delete;
    private Button btn_viewpager;
    private Button btn_toggleButton;
    private Button btn_fastIndex;

    private Context mContext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        mContext = this;
        btn_slide_delete = (Button) findViewById(R.id.btn_slide_delete);
        btn_viewpager = (Button) findViewById(R.id.btn_viewpager);
        btn_toggleButton = (Button) findViewById(R.id.btn_toggleButton);
        btn_fastIndex = (Button) findViewById(R.id.btn_fastIndex);
        btn_slide_delete.setOnClickListener(this);
        btn_viewpager.setOnClickListener(this);
        btn_toggleButton.setOnClickListener(this);
        btn_fastIndex.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_slide_delete:
                intent.setClass(mContext, SlideDelete.class);
                break;
            case R.id.btn_viewpager:
                break;
            case R.id.btn_toggleButton:
                break;
            case R.id.btn_fastIndex:
                break;
        }
        startActivity(intent);
    }
}
