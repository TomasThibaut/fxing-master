package cn.gc.customerviews.slide_delete;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.gc.customerviews.R;

public class SlideDelete extends ActionBarActivity implements View.OnClickListener {
    private ListView listview;
    private MyAdapter mAdapter;
    private ArrayList<Integer> data;

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(SlideDelete.this, R.layout.slide_delete_item, null);
                holder.tv = (TextView) convertView.findViewById(R.id.tv_content);
                holder.delete = (TextView) convertView.findViewById(R.id.tv_delete);
                holder.delete.setOnClickListener(SlideDelete.this);
                holder.tv.setOnClickListener(SlideDelete.this);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv.setText(data.get(position).toString());

            holder.tv.setTag(data.get(position));
            /**关闭其他打开的标签*/
//            SlideDeleteView slideDeleteView = (SlideDeleteView) convertView.findViewById(R.id.slide_view);
//            slideDeleteView.close();
            return convertView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_delete);
        mAdapter = new MyAdapter();
        data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add(i);
        }
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(mAdapter);
    }

    private boolean flag;

    @Override
    public void onClick(View v) {
        Object tag = v.getTag();
        int position = 0;
        if (tag instanceof Integer) {
            position = (int) tag;
        }
        switch (v.getId()) {
            case R.id.tv_content:
                Toast.makeText(this, "点击 " + position + " 号content", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_delete:
                data.remove(position);
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    static class ViewHolder {
        private TextView tv;
        private TextView delete;
    }
}
