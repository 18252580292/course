package com.jskj.course.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jskj.course.R;
import com.jskj.course.bean.HomeBean;
import com.jskj.course.constant.HttpConstants;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnLoadImageListener;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter {
    private List<String> images = new ArrayList<>();
    private static final int VIEW_BANNER = 0x00;
    private static final int VIEW_CHANNEL = 0x01;
    private static final int VIEW_HOT = 0x02;
    private static final int VIEW_RECOMMEND = 0x03;
    private Context mContext;
    private HomeBean homeBean;
    private SharedPreferences mPref;

    public HomeAdapter(Context context, HomeBean homeBean) {
        this.mContext = context;
        this.homeBean = homeBean;
        mPref = this.mContext.getSharedPreferences("config", Context.MODE_PRIVATE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_BANNER:
                View bannerView = View.inflate(mContext, R.layout.home_banner_item_view, null);
                return new BannerHolder(bannerView);
            case VIEW_CHANNEL:
                View channelView = View.inflate(mContext, R.layout.home_channel_item_view, null);
                return new ChannelHolder(channelView);
            case VIEW_HOT:
                View hotView = View.inflate(mContext, R.layout.home_hot_item_view, null);
                return new HotHolder(hotView);
            case VIEW_RECOMMEND:
                View recommendView = View.inflate(mContext, R.layout.home_recommend_item_view, null);
                return new RecommendHolder(recommendView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch (position) {
            case 0:
                BannerHolder bannerHolder = (BannerHolder) holder;
                for (int i = 0; i < homeBean.getResult().getBanner_info().size(); i++) {
                    images.add(i, HttpConstants.BASE_IMG_URL + homeBean.getResult().getBanner_info().get(i).getImage());
                }
                bannerHolder.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                bannerHolder.banner.setImages(images, new OnLoadImageListener() {
                    @Override
                    public void OnLoadImage(ImageView view, Object url) {
                        Glide.with(mContext).load(url).into(view);
                    }
                });
                break;
            case 1:
                ChannelHolder viewHolder = (ChannelHolder) holder;
                viewHolder.gridView.setAdapter(new ChannelAdapter(homeBean));
                viewHolder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if (i == 4) {
                            Toast.makeText(mContext, "没有更多了！！！", Toast.LENGTH_SHORT).show();
                            return;
                        }

                    }
                });
                break;
            case 2:
                HotHolder hotHolder = (HotHolder) holder;
                hotHolder.mGridView.setAdapter(new HotAdapter(homeBean));
                hotHolder.mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    }
                });
                break;
            case 3:
                RecommendHolder recommendHolder = (RecommendHolder) holder;
                recommendHolder.mGridView.setAdapter(new RecommendAdapter(homeBean));
                recommendHolder.mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                });
                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
        int currentType = -1;
        switch (position) {
            case 0:
                currentType = VIEW_BANNER;
                break;
            case 1:
                currentType = VIEW_CHANNEL;
                break;
            case 2:
                currentType = VIEW_HOT;
                break;
            case 3:
                currentType = VIEW_RECOMMEND;
                break;
        }
        return currentType;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
