package org.iflab.wecentermobileandroidrestructure.model.Search;

/**
 * Created by Lyn on 15/9/14.
 */
public class SearchQuestions extends SearchBase{

    /**
     * uid : null
     * score : 1
     * type : questions
     * url : http://we.bistu.edu.cn/?/question/17
     * search_id : 17
     * name : 我想发一个问题
     * detail : {"best_answer":0,"answer_count":1,"comment_count":0,"focus_count":1,"agree_count":0}
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
         * best_answer : 0
         * answer_count : 1
         * comment_count : 0
         * focus_count : 1
         * agree_count : 0
         */

        private int best_answer;
        private int answer_count;
        private int comment_count;
        private int focus_count;
        private int agree_count;

        public void setBest_answer(int best_answer) {
            this.best_answer = best_answer;
        }

        public void setAnswer_count(int answer_count) {
            this.answer_count = answer_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public void setFocus_count(int focus_count) {
            this.focus_count = focus_count;
        }

        public void setAgree_count(int agree_count) {
            this.agree_count = agree_count;
        }

        public int getBest_answer() {
            return best_answer;
        }

        public int getAnswer_count() {
            return answer_count;
        }

        public int getComment_count() {
            return comment_count;
        }

        public int getFocus_count() {
            return focus_count;
        }

        public int getAgree_count() {
            return agree_count;
        }
    }
}
