package com.jskj.course.bean;

import java.util.List;

/**
 * Created by xuecheng.cui on 2017/2/3.
 */
public class HomeResultInfo {
    public String code;
    public String msg;
    public Result result;

    public class Result {
        public List<BannerInfo> banner_info;
        public List<ChannelInfo> channel_info;
        public List<RecommendInfo> recommend_info;
        public List<HotInfo> hot_info;

        public class BannerInfo {
            public String image;
            public String option;
            public String type;
            public String value;
        }

        public class ChannelInfo {
            public String channel_name;
            public String image;
            public String option;
            public String type;
            public String value;
        }

        public class RecommendInfo {
            public String cover_price;
            public String figure;
            public String name;
            public String product_id;
        }

        public class HotInfo {
            public String cover_price;
            public String figure;
            public String name;
            public String product_id;
        }
    }


}
