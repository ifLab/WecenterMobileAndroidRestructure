package org.iflab.wecentermobileandroidrestructure.model.Search;

/**
 * Created by Lyn on 15/9/14.
 */
public class SearchUsers extends SearchBase{


    /**
     * type : articles
     * search_id : 21
     * name : 想想当年经济大萧条，这次股市暴跌也是个警示
     * detail : {"avatar_file":"","reputation":63,"agree_count":1,"thanks_count":1,"fans_count":1,"signature":""}
     */

    private String type;
    private int search_id;
    private String name;
    /**
     * avatar_file :
     * reputation : 63
     * agree_count : 1
     * thanks_count : 1
     * fans_count : 1
     * signature :
     */

    private DetailEntity detail;

    public void setType(String type) {
        this.type = type;
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

    public String getType() {
        return type;
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
        private String avatar_file;
        private int reputation;
        private int agree_count;
        private int thanks_count;
        private int fans_count;
        private String signature;

        public void setAvatar_file(String avatar_file) {
            this.avatar_file = avatar_file;
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

        public void setFans_count(int fans_count) {
            this.fans_count = fans_count;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getAvatar_file() {
            return avatar_file;
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

        public int getFans_count() {
            return fans_count;
        }

        public String getSignature() {
            return signature;
        }
    }
}
