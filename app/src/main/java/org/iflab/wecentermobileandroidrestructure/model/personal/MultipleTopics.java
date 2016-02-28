package org.iflab.wecentermobileandroidrestructure.model.personal;

import java.util.List;

/**
 * Created by Lyn on 15/12/5.
 */
public class MultipleTopics {

    /**
     * rsm : [{"topic_id":251,"topic_title":"开发商","add_time":1444038484,"discuss_count":1,"topic_description":"","topic_pic":null,"topic_lock":0,"focus_count":1,"user_related":0,"url_token":"%E5%BC%80%E5%8F%91%E5%95%86","merged_id":0,"seo_title":null,"parent_id":0,"is_parent":0,"discuss_count_last_week":1,"discuss_count_last_month":1,"discuss_count_update":1444038484,"has_focus":0},{"topic_id":252,"topic_title":"房地产","add_time":1444038484,"discuss_count":1,"topic_description":"","topic_pic":null,"topic_lock":0,"focus_count":1,"user_related":0,"url_token":"%E6%88%BF%E5%9C%B0%E4%BA%A7","merged_id":0,"seo_title":null,"parent_id":0,"is_parent":0,"discuss_count_last_week":1,"discuss_count_last_month":1,"discuss_count_update":1444038484,"has_focus":0}]
     * errno : 1
     * err : null
     */

    private int errno;
    private Object err;
    /**
     * topic_id : 251
     * topic_title : 开发商
     * add_time : 1444038484
     * discuss_count : 1
     * topic_description :
     * topic_pic : null
     * topic_lock : 0
     * focus_count : 1
     * user_related : 0
     * url_token : %E5%BC%80%E5%8F%91%E5%95%86
     * merged_id : 0
     * seo_title : null
     * parent_id : 0
     * is_parent : 0
     * discuss_count_last_week : 1
     * discuss_count_last_month : 1
     * discuss_count_update : 1444038484
     * has_focus : 0
     */

    private List<RsmEntity> rsm;

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public void setErr(Object err) {
        this.err = err;
    }

    public void setRsm(List<RsmEntity> rsm) {
        this.rsm = rsm;
    }

    public int getErrno() {
        return errno;
    }

    public Object getErr() {
        return err;
    }

    public List<RsmEntity> getRsm() {
        return rsm;
    }

    public static class RsmEntity {
        private int topic_id;
        private String topic_title;
        private int add_time;
        private int discuss_count;
        private String topic_description;
        private Object topic_pic;
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

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public void setDiscuss_count(int discuss_count) {
            this.discuss_count = discuss_count;
        }

        public void setTopic_description(String topic_description) {
            this.topic_description = topic_description;
        }

        public void setTopic_pic(Object topic_pic) {
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

        public int getAdd_time() {
            return add_time;
        }

        public int getDiscuss_count() {
            return discuss_count;
        }

        public String getTopic_description() {
            return topic_description;
        }

        public Object getTopic_pic() {
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
