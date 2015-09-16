package org.iflab.wecentermobileandroidrestructure.model.Search;

/**
 * Created by Lyn on 15/9/14.
 */
public class SearchTopics extends SearchBase{


    /**
     * uid : 9
     * score : 1
     * type : articles
     * url : http://we.bistu.edu.cn/?/article/6
     * search_id : 6
     * name : 我决定粘贴一篇特别长的图文混排的文章
     * detail : {"topic_pic":"http://we.bistu.edu.cn/static/common/topic-mid-img.png","topic_id":14,"focus_count":1,"discuss_count":3,"topic_description":""}
     */

    private int uid;
    private int score;
    private String type;
    private String url;
    private int search_id;
    private String name;
    private DetailEntity detail;

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSearch_id(int search_id) {
        this.search_id = search_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetail(DetailEntity detail) {
        this.detail = detail;
    }

    public int getUid() {
        return uid;
    }

    public int getScore() {
        return score;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public int getSearch_id() {
        return search_id;
    }

    public String getName() {
        return name;
    }

    public DetailEntity getDetail() {
        return detail;
    }

    public static class DetailEntity {
        /**
         * topic_pic : http://we.bistu.edu.cn/static/common/topic-mid-img.png
         * topic_id : 14
         * focus_count : 1
         * discuss_count : 3
         * topic_description :
         */

        private String topic_pic;
        private int topic_id;
        private int focus_count;
        private int discuss_count;
        private String topic_description;

        public void setTopic_pic(String topic_pic) {
            this.topic_pic = topic_pic;
        }

        public void setTopic_id(int topic_id) {
            this.topic_id = topic_id;
        }

        public void setFocus_count(int focus_count) {
            this.focus_count = focus_count;
        }

        public void setDiscuss_count(int discuss_count) {
            this.discuss_count = discuss_count;
        }

        public void setTopic_description(String topic_description) {
            this.topic_description = topic_description;
        }

        public String getTopic_pic() {
            return topic_pic;
        }

        public int getTopic_id() {
            return topic_id;
        }

        public int getFocus_count() {
            return focus_count;
        }

        public int getDiscuss_count() {
            return discuss_count;
        }

        public String getTopic_description() {
            return topic_description;
        }
    }
}
