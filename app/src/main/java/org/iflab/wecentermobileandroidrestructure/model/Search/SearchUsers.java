package org.iflab.wecentermobileandroidrestructure.model.Search;

/**
 * Created by Lyn on 15/9/14.
 */
public class SearchUsers extends SearchBase{


    /**
     * uid : 4
     * score : null
     * type : users
     * url : http://we.bistu.edu.cn/?/people/hc
     * search_id : 4
     * name : hc
     * detail : {"avatar_file":"http://we.bistu.edu.cn/uploads/avatar/000/00/00/04_avatar_mid.jpg","signature":"青山依旧在，几度夕阳红！","reputation":0,"agree_count":2,"thanks_count":0}
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
         * avatar_file : http://we.bistu.edu.cn/uploads/avatar/000/00/00/04_avatar_mid.jpg
         * signature : 青山依旧在，几度夕阳红！
         * reputation : 0
         * agree_count : 2
         * thanks_count : 0
         */

        private String avatar_file;
        private String signature;
        private int reputation;
        private int agree_count;
        private int thanks_count;

        public void setAvatar_file(String avatar_file) {
            this.avatar_file = avatar_file;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public void setReputation(int reputation) {
            this.reputation = reputation;
        }

        public void setAgree_count(int agree_count) {
            this.agree_count = agree_count;
        }

        public void setThanks_count(int thanks_count) {
            this.thanks_count = thanks_count;
        }

        public String getAvatar_file() {
            return avatar_file;
        }

        public String getSignature() {
            return signature;
        }

        public int getReputation() {
            return reputation;
        }

        public int getAgree_count() {
            return agree_count;
        }

        public int getThanks_count() {
            return thanks_count;
        }
    }
}
