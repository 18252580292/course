package com.jskj.course.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jskj.course.MCApplication;
import com.jskj.course.R;
import com.jskj.course.bean.HomeResultInfo;
import com.jskj.course.constant.HttpConstants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnLoadImageListener;

import java.util.ArrayList;
import java.util.List;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int BANNER_INFO = 0;
    private final int TYPE_INFO = 1;
    private final int RECOMMEND_INFO = 2;
    private final int HOT_INFO = 3;
    private int currentType = BANNER_INFO;
    private HomeResultInfo resultInfo;

    public HomeRecyclerAdapter(HomeResultInfo resultInfo) {
        this.resultInfo = resultInfo;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                currentType = BANNER_INFO;
                break;
            case 1:
                currentType = TYPE_INFO;
                break;
            case 2:
                currentType = RECOMMEND_INFO;
                break;
            case 3:
                currentType = HOT_INFO;
                break;
        }
        return currentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case BANNER_INFO:
                view = View.inflate(parent.getContext(), R.layout.banner_item, null);
                return new BannerViewHolder(view);
            case TYPE_INFO:
                view = View.inflate(parent.getContext(), R.layout.type_item, null);
                return new TypeViewHolder(view);
            case RECOMMEND_INFO:
                view = View.inflate(parent.getContext(), R.layout.recommend_item, null);
                return new RecommendViewHolder(view);
            case HOT_INFO:
                view = View.inflate(parent.getContext(), R.layout.hot_item, null);
                return new HotViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (position) {
            case 0:
                processBannerItem(holder, position);
                break;
            case 1:
                processTypeItem(holder, position);
                break;
            case 2:
                processRecommendItem(holder, position);
                break;
            case 3:
                processHotItem(holder,position);
                break;
            default:
                throw new IllegalArgumentException("the position may be error!!!");
        }
    }

    /**
     * process the banner info
     */
    private void processBannerItem(RecyclerView.ViewHolder holder, int position) {
        BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
        List<String> images = new ArrayList<>();
        List<HomeResultInfo.Result.BannerInfo> bannerInfos = this.resultInfo.result.banner_info;
        for (int i = 0; i < bannerInfos.size(); i++) {
            images.add(HttpConstants.BASE_URL + HttpConstants.BASE_IMG + bannerInfos.get(i).image);
        }
        bannerViewHolder.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        bannerViewHolder.banner.setImages(images, new OnLoadImageListener() {
            @Override
            public void OnLoadImage(ImageView view, Object url) {
                Glide.with(MCApplication.mContext).load(url).into(view);
            }
        });
    }

    /**
     * process the type info
     */
    private void processTypeItem(RecyclerView.ViewHolder holder, int position) {
        TypeViewHolder typeViewHolder = (TypeViewHolder) holder;
        typeViewHolder.gridView.setAdapter(new HomeRecyclerTypeAapter(resultInfo.result.channel_info));
    }

    private void processRecommendItem(RecyclerView.ViewHolder holder, int position) {
        RecommendViewHolder viewHolder = (RecommendViewHolder) holder;
        viewHolder.gridView.setAdapter(new HomeRecyclerRecommendAdapter(resultInfo.result.recommend_info));
    }

    private void processHotItem(RecyclerView.ViewHolder holder, int position) {
        HotViewHolder hotViewHolder = (HotViewHolder) holder;
        hotViewHolder.gridView.setAdapter(new HomeRecyclerHotAdapter(resultInfo.result.hot_info));
    }
    @Override
    public int getItemCount() {
        return 4;
    }

    private class BannerViewHolder extends RecyclerView.ViewHolder {
        private Banner banner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner);
        }
    }

    private class TypeViewHolder extends RecyclerView.ViewHolder {
        public GridView gridView;

        public TypeViewHolder(View itemView) {
            super(itemView);
            gridView = (GridView) itemView.findViewById(R.id.grid_view_type);
        }
    }

    private class RecommendViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_more;
        private GridView gridView;

        public RecommendViewHolder(View itemView) {
            super(itemView);
            this.tv_more = (TextView) itemView.findViewById(R.id.tv_more_recommend);
            this.gridView = (GridView) itemView.findViewById(R.id.grid_view_recommend);
        }
    }

    private class HotViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_more;
        private GridView gridView;

        public HotViewHolder(View itemView) {
            super(itemView);
            this.tv_more = (TextView) itemView.findViewById(R.id.tv_more_hot);
            this.gridView = (GridView) itemView.findViewById(R.id.grid_view_hot);
        }
    }
}
