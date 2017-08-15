package com.yechaoa.timelinedemo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listView);
    }

    private void initData() {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        map.put("title", "已签收 期待再次为您服务");
        map.put("time", "2017-08-14 11:21:58");
        list.add(map);

        map = new HashMap<>();
        map.put("title", "派件中 快递员** 123456789");
        map.put("time", "2017-08-14 11:21:58");
        list.add(map);

        map = new HashMap<>();
        map.put("title", "快件已到达 上海浦东集散中心");
        map.put("time", "2017-08-14 11:21:58");
        list.add(map);

        map = new HashMap<>();
        map.put("title", "快件已到达 上海虹桥集散中心");
        map.put("time", "2017-08-14 11:21:58");
        list.add(map);

        map = new HashMap<>();
        map.put("title", "苏州吴江大客户营业部 已发出");
        map.put("time", "2017-08-14 11:21:58");
        list.add(map);

        map = new HashMap<>();
        map.put("title", "包裹正在等待揽收");
        map.put("time", "2017-08-14 11:21:58");
        list.add(map);

        mListView.setAdapter(new TimeLineAdapter(this, list));
    }

    class TimeLineAdapter extends BaseAdapter {

        private Context context;
        private List<Map<String, Object>> list;
        private LayoutInflater inflater;

        public TimeLineAdapter(Context context, List<Map<String, Object>> list) {
            super();
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TimeLineHolder viewHolder;
            if (convertView == null) {
                inflater = LayoutInflater.from(parent.getContext());
                convertView = inflater.inflate(R.layout.item_time_line, null);
                viewHolder = new TimeLineHolder();
                viewHolder.title = (TextView) convertView.findViewById(R.id.title);
                viewHolder.time = (TextView) convertView.findViewById(R.id.time);
                viewHolder.view0 = convertView.findViewById(R.id.view0);
                viewHolder.view4 = convertView.findViewById(R.id.view4);
                viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (TimeLineHolder) convertView.getTag();
            }
            //根据key获取value
            String titleStr = list.get(position).get("title").toString();
            viewHolder.title.setText(titleStr);
            String timeStr = list.get(position).get("time").toString();
            viewHolder.time.setText(timeStr);
            //第一个去掉上面的竖线，并设置颜色
            if (position == 0) {
                viewHolder.title.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                viewHolder.time.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                viewHolder.image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.shape_oval_primary));
                viewHolder.view0.setVisibility(View.INVISIBLE);
            } else {
                viewHolder.title.setTextColor(Color.GRAY);
                viewHolder.time.setTextColor(Color.GRAY);
            }
            //最后一个去掉底部的下划线
            if (position == list.size() - 1) {
                viewHolder.view4.setVisibility(View.INVISIBLE);
            } else {
                viewHolder.view4.setVisibility(View.VISIBLE);
            }
            return convertView;
        }

        class TimeLineHolder {
            private TextView title, time;
            private View view0, view4;
            private ImageView image;
        }
    }
}
