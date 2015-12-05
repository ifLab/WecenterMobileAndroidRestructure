package org.iflab.wecentermobileandroidrestructure.model.question;

/**
 * Created by Lyn on 15/7/18.
 */
public class QuestionInfo {


    /**
     * question_id : 162
     * question_content : 阿里九月份宣布杭州北京双总部，意图何在？
     * question_detail :
     * add_time : 1443680912
     * update_time : 1447898441
     * answer_count : 3
     * view_count : 75
     * focus_count : 4
     * comment_count : 0
     * agree_count : 5
     * against_count : 0
     * thanks_count : 0
     * user_info : {"uid":4,"user_name":"Carol","signature":"审计民工","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/04_avatar_mid.jpg"}
     * user_answered : 0
     * user_follow_check : 1
     * user_question_focus : 0
     * user_thanks : 0
     */

    private int question_id;
    private String question_content;
    private String question_detail;
    private long add_time;
    private int update_time;
    private int answer_count;
    private int view_count;
    private int focus_count;
    private int comment_count;
    private int agree_count;
    private int against_count;
    private int thanks_count;
    private UserInfoEntity user_info;
    private int user_answered;
    private int user_follow_check;
    private int user_question_focus;
    private int user_thanks;

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    public void setQuestion_detail(String question_detail) {
        this.question_detail = question_detail;
    }

    public void setAdd_time(long add_time) {
        this.add_time = add_time;
    }

    public void setUpdate_time(int update_time) {
        this.update_time = update_time;
    }

    public void setAnswer_count(int answer_count) {
        this.answer_count = answer_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public void setFocus_count(int focus_count) {
        this.focus_count = focus_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public void setAgree_count(int agree_count) {
        this.agree_count = agree_count;
    }

    public void setAgainst_count(int against_count) {
        this.against_count = against_count;
    }

    public void setThanks_count(int thanks_count) {
        this.thanks_count = thanks_count;
    }

    public void setUser_info(UserInfoEntity user_info) {
        this.user_info = user_info;
    }

    public void setUser_answered(int user_answered) {
        this.user_answered = user_answered;
    }

    public void setUser_follow_check(int user_follow_check) {
        this.user_follow_check = user_follow_check;
    }

    public void setUser_question_focus(int user_question_focus) {
        this.user_question_focus = user_question_focus;
    }

    public void setUser_thanks(int user_thanks) {
        this.user_thanks = user_thanks;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public String getQuestion_detail() {
        return question_detail;
    }

    public long getAdd_time() {
        return add_time;
    }

    public int getUpdate_time() {
        return update_time;
    }

    public int getAnswer_count() {
        return answer_count;
    }

    public int getView_count() {
        return view_count;
    }

    public int getFocus_count() {
        return focus_count;
    }

    public int getComment_count() {
        return comment_count;
    }

    public int getAgree_count() {
        return agree_count;
    }

    public int getAgainst_count() {
        return against_count;
    }

    public int getThanks_count() {
        return thanks_count;
    }

    public UserInfoEntity getUser_info() {
        return user_info;
    }

    public int getUser_answered() {
        return user_answered;
    }

    public int getUser_follow_check() {
        return user_follow_check;
    }

    public int getUser_question_focus() {
        return user_question_focus;
    }

    public int getUser_thanks() {
        return user_thanks;
    }

    public static class UserInfoEntity {
        /**
         * uid : 4
         * user_name : Carol
         * signature : 审计民工
         * avatar_file : http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/04_avatar_mid.jpg
         */

        private int uid;
        private String user_name;
        private String signature;
        private String avatar_file;

        public void setUid(int uid) {
            this.uid = uid;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public void setSignature(String signature) {
            this.signature = signature;
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

        public String getSignature() {
            return signature;
        }

        public String getAvatar_file() {
            return avatar_file;
        }
    }
}
