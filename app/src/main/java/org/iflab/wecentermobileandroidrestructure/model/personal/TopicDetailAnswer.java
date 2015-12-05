package org.iflab.wecentermobileandroidrestructure.model.personal;

import java.util.List;

/**
 * Created by Lyn on 15/12/3.
 */
public class TopicDetailAnswer {


    /**
     * total_rows : 5
     * rows : [{"question_info":{"question_id":38,"question_content":"为什么股市中同一10.05%？"},"user_info":{"uid":8,"user_name":"新海基金","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/08_avatar_mid.jpg"},"answer_info":{"answer_id":63,"answer_content":"目前答案根本没有解释","add_time":1436177469,"against_count":0,"agree_count":3,"comment_count":0,"thanks_count":0,"agree_status":0}}]
     */

    private int total_rows;
    /**
     * question_info : {"question_id":38,"question_content":"为什么股市中同一10.05%？"}
     * user_info : {"uid":8,"user_name":"新海基金","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/08_avatar_mid.jpg"}
     * answer_info : {"answer_id":63,"answer_content":"目前答案根本没有解释","add_time":1436177469,"against_count":0,"agree_count":3,"comment_count":0,"thanks_count":0,"agree_status":0}
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
        /**
         * question_id : 38
         * question_content : 为什么股市中同一10.05%？
         */

        private QuestionInfoEntity question_info;
        /**
         * uid : 8
         * user_name : 新海基金
         * avatar_file : http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/08_avatar_mid.jpg
         */

        private UserInfoEntity user_info;
        /**
         * answer_id : 63
         * answer_content : 目前答案根本没有解释
         * add_time : 1436177469
         * against_count : 0
         * agree_count : 3
         * comment_count : 0
         * thanks_count : 0
         * agree_status : 0
         */

        private AnswerInfoEntity answer_info;

        public void setQuestion_info(QuestionInfoEntity question_info) {
            this.question_info = question_info;
        }

        public void setUser_info(UserInfoEntity user_info) {
            this.user_info = user_info;
        }

        public void setAnswer_info(AnswerInfoEntity answer_info) {
            this.answer_info = answer_info;
        }

        public QuestionInfoEntity getQuestion_info() {
            return question_info;
        }

        public UserInfoEntity getUser_info() {
            return user_info;
        }

        public AnswerInfoEntity getAnswer_info() {
            return answer_info;
        }

        public static class QuestionInfoEntity {
            private int question_id;
            private String question_content;

            public void setQuestion_id(int question_id) {
                this.question_id = question_id;
            }

            public void setQuestion_content(String question_content) {
                this.question_content = question_content;
            }

            public int getQuestion_id() {
                return question_id;
            }

            public String getQuestion_content() {
                return question_content;
            }
        }

        public static class UserInfoEntity {
            private int uid;
            private String user_name;
            private String avatar_file;

            public void setUid(int uid) {
                this.uid = uid;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public void setAvatar_file(String avatar_file) {
                this.avatar_file = avatar_file;
            }

            public int getUid() {
                return uid;
            }

            public String getUser_name() {
                return user_name;
            }

            public String getAvatar_file() {
                return avatar_file;
            }
        }

        public static class AnswerInfoEntity {
            private int answer_id;
            private String answer_content;
            private int add_time;
            private int against_count;
            private int agree_count;
            private int comment_count;
            private int thanks_count;
            private int agree_status;

            public void setAnswer_id(int answer_id) {
                this.answer_id = answer_id;
            }

            public void setAnswer_content(String answer_content) {
                this.answer_content = answer_content;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public void setAgainst_count(int against_count) {
                this.against_count = against_count;
            }

            public void setAgree_count(int agree_count) {
                this.agree_count = agree_count;
            }

            public void setComment_count(int comment_count) {
                this.comment_count = comment_count;
            }

            public void setThanks_count(int thanks_count) {
                this.thanks_count = thanks_count;
            }

            public void setAgree_status(int agree_status) {
                this.agree_status = agree_status;
            }

            public int getAnswer_id() {
                return answer_id;
            }

            public String getAnswer_content() {
                return answer_content;
            }

            public int getAdd_time() {
                return add_time;
            }

            public int getAgainst_count() {
                return against_count;
            }

            public int getAgree_count() {
                return agree_count;
            }

            public int getComment_count() {
                return comment_count;
            }

            public int getThanks_count() {
                return thanks_count;
            }

            public int getAgree_status() {
                return agree_status;
            }
        }
    }
}
