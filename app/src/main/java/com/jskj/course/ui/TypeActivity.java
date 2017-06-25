package com.jskj.course.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.jskj.course.R;
import com.jskj.course.bean.CourseBean;
import com.jskj.course.constant.HttpConstants;
import com.jskj.course.util.OkhttpUtils;
import com.jskj.course.util.TextUtils;

import java.io.IOException;

public class TypeActivity extends AppCompatActivity {
    private TextView mTvTypeTitle;
    private ListView mTypeListview;
    private CourseBean courseBean;
    private SharedPreferences mPref;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            courseBean = JSON.parseObject((String) msg.obj, CourseBean.class);
            mTypeListview.setAdapter(new TypeAdapter(courseBean));
            mTypeListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(view.getContext(), PlayActivity.class);
                    String url = HttpConstants.BASE_URL + courseBean.getCourse().get(i).getUrl();
                    String name = courseBean.getCourse().get(i).getName();
                    Bundle bundle = new Bundle();
                    bundle.putString("url", url);
                    bundle.putString("name", name);
                    intent.putExtra("bundle", bundle);
                    view.getContext().startActivity(intent);
                    SharedPreferences.Editor editor = mPref.edit();
                    editor.putString("url", url);
                    editor.putString("name", name);
                    editor.putString("img", HttpConstants.BASE_IMG_URL + courseBean.getCourse().get(i).getFigure());
                    editor.apply();
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        mTvTypeTitle = (TextView) findViewById(R.id.tv_type);
        mTypeListview = (ListView) findViewById(R.id.type_list_view);
        mPref = getSharedPreferences("config", MODE_PRIVATE);
        Intent intent = getIntent();
        final String key = intent.getStringExtra("key");
        if (TextUtils.isEmpty(key)) {
            return;
        }
        new Thread() {
            @Override
            public void run() {
                initData(key);
            }
        }.start();
    }

    private void initData(String key) {
        CourseBean courseBean = null;
        String json = null;
        switch (key) {
            case "math":
                mTvTypeTitle.setText("数学类");
                try {
                    json = OkhttpUtils.get(HttpConstants.BASE_URL + "/json/math.json");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "lang":
                mTvTypeTitle.setText("语言类");
                try {
                    json = OkhttpUtils.get(HttpConstants.BASE_URL + "/json/lang.json");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "major":
                mTvTypeTitle.setText("专业课");
                try {
                    json = OkhttpUtils.get(HttpConstants.BASE_URL + "/json/major.json");

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "img":
                mTvTypeTitle.setText("图像类");
                try {
                    json = OkhttpUtils.get(HttpConstants.BASE_URL + "/json/img.json");

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
        if (json == null) {
            return;
        }
        Message msg = Message.obtain();
        msg.obj = json;
        mHandler.sendMessage(msg);
    }

    class TypeAdapter extends BaseAdapter {
        private CourseBean courseBean;

        public TypeAdapter(CourseBean courseBean) {
            this.courseBean = courseBean;
        }

        @Override
        public int getCount() {
            return this.courseBean.getCourse().size();
        }

        @Override
        public CourseBean.Course getItem(int i) {
            return this.courseBean.getCourse().get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            View view = View.inflate(viewGroup.getContext(), R.layout.course_item_view, null);
            TextView tv_name = view.findViewById(R.id.course_tv_name);
            ImageView iv_img = view.findViewById(R.id.course_img);
            tv_name.setText(getItem(i).getName());
            Glide.with(viewGroup.getContext()).load(HttpConstants.BASE_IMG_URL + getItem(i).getFigure()).into(iv_img);
            return view;
        }
    }
}
