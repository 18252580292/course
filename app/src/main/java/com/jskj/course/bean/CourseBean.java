package com.jskj.course.bean;

import java.util.List;

/**
 * Created by xuecheng.cui on 2017/6/20.
 */

public class CourseBean {

    /**
     * code : 200
     * msg : success
     * course : [{"cover_price":"159.00","figure":"/1477984921265.jpg","name":"神经网络","url":"/video/major/neural_net_01.mp4","product_id":"9356"},{"cover_price":"159.00","figure":"/1477984931882.jpg","name":"计算机视觉","url":"/video/major/computer_vision_01.mp4","product_id":"10391"},{"cover_price":"29.00","figure":"/1452161899947.jpg","name":"现代通信原理与系统","url":"/video/major/modern_communication_01.mp4","product_id":"3831"},{"cover_price":"199.00","figure":"/1447232577216.jpg","name":"通信原理","url":"/video/major/communication_01.mp4","product_id":"2691"},{"cover_price":"70.00","figure":"/1474370572805.jpg","name":"信号与系统","url":"/video/major/sinal_and_system_01.mp4","product_id":"9414"},{"cover_price":"4.80","figure":"/1465268743242.jpg","name":"模式识别","url":"/video/major/pattern_recognize_01.mp4","product_id":"6869"},{"cover_price":"143.10","figure":"/1477360350123.png","name":"图像处理","url":"/video/major/image_process_01.mp4","product_id":"10136"},{"cover_price":"329.00","figure":"/supplier/1467702094592.jpg","name":"现代电子技术","url":"/video/major/modern_elec_01.mp4","product_id":"7752"},{"cover_price":"138.00","figure":"/supplier/1478873740576.jpg","name":"数学","url":"/video/math/higher_math_01.mp4","product_id":"10659"},{"cover_price":"138.00","figure":"/supplier/1478873369497.jpg","name":"阅读","url":"/video/english/read_01.mp4","product_id":"10658"},{"cover_price":"32.00","figure":"/supplier/1478867468462.jpg","name":"概率论","url":"/video/math/linear_algebra_01.mp4","product_id":"10657"},{"cover_price":"18.00","figure":"/1478860081305.jpg","name":"神经网络","url":"/video/major/neural_net_01.mp4","product_id":"10656"},{"cover_price":"178.00","figure":"/1478850234799.jpg","name":"通信原理","url":"/video/major/communication_01.mp4","product_id":"10655"},{"cover_price":"138.00","figure":"/1478849792177.jpg","name":"信号与系统","url":"/video/major/signal_01.mp4","product_id":"10654"}]
     */

    private int code;
    private String msg;
    private List<Course> course;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Course> getCourse() {
        return course;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }

    public static class Course {
        /**
         * cover_price : 159.00
         * figure : /1477984921265.jpg
         * name : 神经网络
         * url : /video/major/neural_net_01.mp4
         * product_id : 9356
         */

        private String cover_price;
        private String figure;
        private String name;
        private String url;
        private String product_id;

        public String getCover_price() {
            return cover_price;
        }

        public void setCover_price(String cover_price) {
            this.cover_price = cover_price;
        }

        public String getFigure() {
            return figure;
        }

        public void setFigure(String figure) {
            this.figure = figure;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }
    }
}
