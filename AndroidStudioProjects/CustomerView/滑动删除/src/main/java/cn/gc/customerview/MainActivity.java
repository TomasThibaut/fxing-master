package cn.gc.customerview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * 一个ListView用来加载单独的自定义Item
 */
public class MainActivity extends AppCompatActivity {
    private ListView lv_main;
    private MyAdapter mAdapter;
    private Context mContext;
    private ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            dataList.add("" + i);
        }
        mContext = this;
        mAdapter = new MyAdapter();
        lv_main = (ListView) findViewById(R.id.lv_main);
        lv_main.setAdapter(mAdapter);
    }

    private class MyAdapter extends BaseAdapter implements View.OnClickListener {

        @Override
        public int getCount() {
            return dataList.size();
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
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(mContext, R.layout.item_slidedelete, null);

                convertView.findViewById(R.id.tv_content).setOnClickListener(this);
                convertView.findViewById(R.id.tv_delete).setOnClickListener(this);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            holder.tv_content.setText(dataList.get(position));
            return convertView;
        }
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_content:
                    Toast.makeText(mContext, "我是Content", Toast.LENGTH_SHORT).show();

                    break;
                case R.id.tv_delete:
                    Toast.makeText(mContext, "我是Delete", Toast.LENGTH_SHORT).show();

                    //TODO remove掉dataList的这一行数据
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    static class ViewHolder {
        private TextView tv_content;
    }

}
