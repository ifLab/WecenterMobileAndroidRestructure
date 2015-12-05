package org.iflab.wecentermobileandroidrestructure.model.personal;

import java.util.List;

/**
 * Description:
 *
 * @author huangchen
 * @version 1.0
 * @time 15/8/22 01:06
 */

public class PersonalTopic {


    /**
     * total_rows : 2
     * rows : [{"topic_id":13,"topic_title":"基金","add_time":1434772016,"discuss_count":15,"topic_description":"哈哈哈 测试。。","topic_pic":"http://wecenter.dev.hihwei.com/uploads/topic/20151129/e12d3caff53151e54cd1a3b9075cf73b_32_32.jpeg","topic_lock":0,"focus_count":4,"user_related":0,"url_token":"%E5%9F%BA%E9%87%91","merged_id":0,"seo_title":null,"parent_id":0,"is_parent":0,"discuss_count_last_week":1,"discuss_count_last_month":1,"discuss_count_update":1444719904,"has_focus":0},{"topic_id":3,"topic_title":"牛市","add_time":1434675053,"discuss_count":6,"topic_description":"","topic_pic":null,"topic_lock":0,"focus_count":2,"user_related":0,"url_token":"%E7%89%9B%E5%B8%82","merged_id":0,"seo_title":null,"parent_id":0,"is_parent":0,"discuss_count_last_week":5,"discuss_count_last_month":6,"discuss_count_update":1435589709,"has_focus":0}]
     */

    private int total_rows;
    /**
     * topic_id : 13
     * topic_title : 基金
     * add_time : 1434772016
     * discuss_count : 15
     * topic_description : 哈哈哈 测试。。
     * topic_pic : http://wecenter.dev.hihwei.com/uploads/topic/20151129/e12d3caff53151e54cd1a3b9075cf73b_32_32.jpeg
     * topic_lock : 0
     * focus_count : 4
     * user_related : 0
     * url_token : %E5%9F%BA%E9%87%91
     * merged_id : 0
     * seo_title : null
     * parent_id : 0
     * is_parent : 0
     * discuss_count_last_week : 1
     * discuss_count_last_month : 1
     * discuss_count_update : 1444719904
     * has_focus : 0
     */

    private List<RowsEntity> rows;

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public void setRows(List<RowsEntity> rows) {
        this.rows = rows;
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public List<RowsEntity> getRows() {
        return rows;
    }

    public static class RowsEntity {
        private int topic_id;
        private String topic_title;
        private long add_time;
        private int discuss_count;
        private String topic_description;
        private String topic_pic;
        private int topic_lock;
        private int focus_count;
        private int user_related;
        private String url_token;
        private int merged_id;
        private Object seo_title;
        private int parent_id;
        private int is_parent;
        private int discuss_count_last_week;
        private int discuss_count_last_month;
        private int discuss_count_update;
        private int has_focus;

        public void setTopic_id(int topic_id) {
            this.topic_id = topic_id;
        }

        public void setTopic_title(String topic_title) {
            this.topic_title = topic_title;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }

        public void setDiscuss_count(int discuss_count) {
            this.discuss_count = discuss_count;
        }

        public void setTopic_description(String topic_description) {
            this.topic_description = topic_description;
        }

        public void setTopic_pic(String topic_pic) {
            this.topic_pic = topic_pic;
        }

        public void setTopic_lock(int topic_lock) {
            this.topic_lock = topic_lock;
        }

        public void setFocus_count(int focus_count) {
            this.focus_count = focus_count;
        }

        public void setUser_related(int user_related) {
            this.user_related = user_related;
        }

        public void setUrl_token(String url_token) {
            this.url_token = url_token;
        }

        public void setMerged_id(int merged_id) {
            this.merged_id = merged_id;
        }

        public void setSeo_title(Object seo_title) {
            this.seo_title = seo_title;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        public void setIs_parent(int is_parent) {
            this.is_parent = is_parent;
        }

        public void setDiscuss_count_last_week(int discuss_count_last_week) {
            this.discuss_count_last_week = discuss_count_last_week;
        }

        public void setDiscuss_count_last_month(int discuss_count_last_month) {
            this.discuss_count_last_month = discuss_count_last_month;
        }

        public void setDiscuss_count_update(int discuss_count_update) {
            this.discuss_count_update = discuss_count_update;
        }

        public void setHas_focus(int has_focus) {
            this.has_focus = has_focus;
        }

        public int getTopic_id() {
            return topic_id;
        }

        public String getTopic_title() {
            return topic_title;
        }

        public long getAdd_time() {
            return add_time;
        }

        public int getDiscuss_count() {
            return discuss_count;
        }

        public String getTopic_description() {
            return topic_description;
        }

        public String getTopic_pic() {
            return topic_pic;
        }

        public int getTopic_lock() {
            return topic_lock;
        }

        public int getFocus_count() {
            return focus_count;
        }

        public int getUser_related() {
            return user_related;
        }

        public String getUrl_token() {
            return url_token;
        }

        public int getMerged_id() {
            return merged_id;
        }

        public Object getSeo_title() {
            return seo_title;
        }

        public int getParent_id() {
            return parent_id;
        }

        public int getIs_parent() {
            return is_parent;
        }

        public int getDiscuss_count_last_week() {
            return discuss_count_last_week;
        }

        public int getDiscuss_count_last_month() {
            return discuss_count_last_month;
        }

        public int getDiscuss_count_update() {
            return discuss_count_update;
        }

        public int getHas_focus() {
            return has_focus;
        }
    }
}
