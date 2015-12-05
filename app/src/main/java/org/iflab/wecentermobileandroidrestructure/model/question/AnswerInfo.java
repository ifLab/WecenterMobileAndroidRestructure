package org.iflab.wecentermobileandroidrestructure.model.question;

/**
 * Created by Lyn on 15/7/18.
 */
public class AnswerInfo {


    /**
     * answer : {"user_info":{"avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/05_avatar_mid.jpg","uid":5,"user_name":"滚雪球","signature":"滚雪球，不是一个人能完成的事儿..."},"comment_count":0,"uid":5,"question_content":"全国房地产开发商前五十名有哪些？","uninterested_count":0,"answer_content":"[b]全国房地产","category_id":1,"user_vote_status":0,"agree_count":2,"against_count":0,"ip":1034167026,"anonymous":0,"has_attach":0,"publish_source":null,"answer_id":289,"user_thanks_status":0,"add_time":1444038839,"question_id":167,"thanks_count":0,"force_fold":0}
     */

    private AnswerEntity answer;

    public void setAnswer(AnswerEntity answer) {
        this.answer = answer;
    }

    public AnswerEntity getAnswer() {
        return answer;
    }

    public static class AnswerEntity {
        /**
         * user_info : {"avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/05_avatar_mid.jpg","uid":5,"user_name":"滚雪球","signature":"滚雪球，不是一个人能完成的事儿..."}
         * comment_count : 0
         * uid : 5
         * question_content : 全国房地产开发商前五十名有哪些？
         * uninterested_count : 0
         * answer_content : [b]全国房地产
         * category_id : 1
         * user_vote_status : 0
         * agree_count : 2
         * against_count : 0
         * ip : 1034167026
         * anonymous : 0
         * has_attach : 0
         * publish_source : null
         * answer_id : 289
         * user_thanks_status : 0
         * add_time : 1444038839
         * question_id : 167
         * thanks_count : 0
         * force_fold : 0
         */

        private UserInfoEntity user_info;
        private int comment_count;
        private int uid;
        private String question_content;
        private int uninterested_count;
        private String answer_content;
        private int category_id;
        private int user_vote_status;
        private int agree_count;
        private int against_count;
        private long ip;
        private int anonymous;
        private int has_attach;
        private Object publish_source;
        private int answer_id;
        private int user_thanks_status;
        private long add_time;
        private int question_id;
        private int thanks_count;
        private int force_fold;

        public void setUser_info(UserInfoEntity user_info) {
            this.user_info = user_info;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public void setQuestion_content(String question_content) {
            this.question_content = question_content;
        }

        public void setUninterested_count(int uninterested_count) {
            this.uninterested_count = uninterested_count;
        }

        public void setAnswer_content(String answer_content) {
            this.answer_content = answer_content;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public void setUser_vote_status(int user_vote_status) {
            this.user_vote_status = user_vote_status;
        }

        public void setAgree_count(int agree_count) {
            this.agree_count = agree_count;
        }

        public void setAgainst_count(int against_count) {
            this.against_count = against_count;
        }

        public void setIp(long ip) {
            this.ip = ip;
        }

        public void setAnonymous(int anonymous) {
            this.anonymous = anonymous;
        }

        public void setHas_attach(int has_attach) {
            this.has_attach = has_attach;
        }

        public void setPublish_source(Object publish_source) {
            this.publish_source = publish_source;
        }

        public void setAnswer_id(int answer_id) {
            this.answer_id = answer_id;
        }

        public void setUser_thanks_status(int user_thanks_status) {
            this.user_thanks_status = user_thanks_status;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }

        public void setQuestion_id(int question_id) {
            this.question_id = question_id;
        }

        public void setThanks_count(int thanks_count) {
            this.thanks_count = thanks_count;
        }

        public void setForce_fold(int force_fold) {
            this.force_fold = force_fold;
        }

        public UserInfoEntity getUser_info() {
            return user_info;
        }

        public int getComment_count() {
            return comment_count;
        }

        public int getUid() {
            return uid;
        }

        public String getQuestion_content() {
            return question_content;
        }

        public int getUninterested_count() {
            return uninterested_count;
        }

        public String getAnswer_content() {
            return answer_content;
        }

        public int getCategory_id() {
            return category_id;
        }

        public int getUser_vote_status() {
            return user_vote_status;
        }

        public int getAgree_count() {
            return agree_count;
        }

        public int getAgainst_count() {
            return against_count;
        }

        public long getIp() {
            return ip;
        }

        public int getAnonymous() {
            return anonymous;
        }

        public int getHas_attach() {
            return has_attach;
        }

        public Object getPublish_source() {
            return publish_source;
        }

        public int getAnswer_id() {
            return answer_id;
        }

        public int getUser_thanks_status() {
            return user_thanks_status;
        }

        public long getAdd_time() {
            return add_time;
        }

        public int getQuestion_id() {
            return question_id;
        }

        public int getThanks_count() {
            return thanks_count;
        }

        public int getForce_fold() {
            return force_fold;
        }

        public static class UserInfoEntity {
            /**
             * avatar_file : http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/05_avatar_mid.jpg
             * uid : 5
             * user_name : 滚雪球
             * signature : 滚雪球，不是一个人能完成的事儿...
             */

            private String avatar_file;
            private int uid;
            private String user_name;
            private String signature;

            public void setAvatar_file(String avatar_file) {
                this.avatar_file = avatar_file;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getAvatar_file() {
                return avatar_file;
            }

            public int getUid() {
                return uid;
            }

            public String getUser_name() {
                return user_name;
            }

            public String getSignature() {
                return signature;
            }
        }
    }
}
