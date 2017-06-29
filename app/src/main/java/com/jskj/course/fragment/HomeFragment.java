package com.jskj.course.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jskj.course.R;
import com.jskj.course.adapter.HomeAdapter;
import com.jskj.course.bean.HomeBean;
import com.jskj.course.mvp.presenter.HomePresenter;
import com.jskj.course.mvp.presenter.impl.HomePresenterImpl;
import com.jskj.course.mvp.view.HomeFragmentView;

public class HomeFragment extends Fragment implements HomeFragmentView {
    private TextView mTvTitle;
    private RecyclerView mRecycler;
    private HomePresenter presenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTvTitle = view.findViewById(R.id.tv_common_title);
        mRecycler = view.findViewById(R.id.main_recycle_view);
        presenter = new HomePresenterImpl();
        presenter.loadHomeData(this);
    }


    @Override
    public void setAdapter(HomeBean homeBean) {
        mRecycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRecycler.setAdapter(new HomeAdapter(this.getActivity(), homeBean));
    }

    @Override
    public void showErrorMessage(String msg) {
        Toast.makeText(this.getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
