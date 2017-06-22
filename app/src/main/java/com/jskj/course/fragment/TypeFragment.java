package com.jskj.course.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

/**
 * Created by cui on 2017/6/17.
 */

public class TypeFragment extends BaseFragment {
    @Override
    protected View createView() {
        TextView textView = new TextView(this.getActivity());
        textView.setText("type");
        textView.setTextColor(Color.RED);
        return textView;
    }
}
