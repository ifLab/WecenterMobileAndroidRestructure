package org.iflab.wecentermobileandroidrestructure.model.Search;

/**
 * Created by Lyn on 15/9/14.
 */
public class SearchArticles extends SearchBase{

    /**
     * uid : 9
     * score : 1
     * type : articles
     * url : http://we.bistu.edu.cn/?/article/6
     * search_id : 6
     * name : 我决定粘贴一篇特别长的图文混排的文章
     * detail : {"comments":0,"views":9}
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
         * comments : 0
         * views : 9
         */

        private int comments;
        private int views;

        public void setComments(int comments) {
            this.comments = comments;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public int getComments() {
            return comments;
        }

        public int getViews() {
            return views;
        }
    }
}
