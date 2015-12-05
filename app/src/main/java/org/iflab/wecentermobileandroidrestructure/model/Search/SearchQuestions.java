package org.iflab.wecentermobileandroidrestructure.model.Search;

/**
 * Created by Lyn on 15/9/14.
 */
public class SearchQuestions extends SearchBase{


    /**
     * type : questions
     * search_id : 146
     * name : 降息对股市有什么影响？
     * detail : {"best_answer":0,"answer_count":1,"comment_count":0,"focus_count":2,"agree_count":7}
     */

    private String type;
    private int search_id;
    private String name;
    /**
     * best_answer : 0
     * answer_count : 1
     * comment_count : 0
     * focus_count : 2
     * agree_count : 7
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
