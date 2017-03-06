package com.jskj.course.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by xuecheng.cui on 2017/2/4.
 */
public abstract class HomeRecyclerBaseAdapter<T> extends BaseAdapter {
    protected List<T> list;

    @Override
    public int getCount() {
        if(list != null && !list.isEmpty()) {
            return list.size();
        }
        return 0;
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = getConvertView();
        }
        showUI(convertView,position);
        return convertView;
    }

    /**
     * 加载相应布局
     * @return
     */
    protected abstract View getConvertView();

    /**
     * 展示相应的界面
     */
    protected abstract void showUI(View convertView,int position);

}
