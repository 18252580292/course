package com.jskj.course.mvp.view;


import com.jskj.course.bean.HomeBean;

public interface HomeFragmentView {

    /**
     * 设置RecyclerView的适配器
     *
     * @param homeBean
     */
    public void setAdapter(HomeBean homeBean);

    /**
     * 显示错误信息
     * @param msg
     */
    public void showErrorMessage(String msg);

}
