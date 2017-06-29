package com.jskj.course.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.jskj.course.bean.HomeBean;

/**
 * Created by xuecheng.cui on 2017/6/20.
 */

public abstract class BaseAdapter extends android.widget.BaseAdapter {
    protected HomeBean homeBean;

    protected BaseAdapter(HomeBean homeBean) {
        this.homeBean = homeBean;
    }

    @Override
    public int getCount() {
        return getItemCount();
    }


    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return getItemView(i, view, viewGroup);
    }

    protected abstract View getItemView(int i, View view, ViewGroup viewGroup);

    protected abstract int getItemCount();
}
