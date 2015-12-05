package org.iflab.wecentermobileandroidrestructure.model.personal;

/**
 * Created by Lyn on 15/11/27.
 */
public class Person {


    /**
     * uid : 2
     * user_name : 小韭菜
     * avatar_file : http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/02_avatar_max.jpg
     * sex : 1
     * province : 浙江省
     * city : 杭州市
     * fans_count : 50
     * friend_count : 23
     * article_count : 6
     * question_count : 4
     * answer_count : 51
     * topic_focus_count : 28
     * agree_count : 240
     * thanks_count : 8
     * views_count : 162
     * signature : 非职业投机客...
     * answer_favorite_count : 1
     * has_focus : 0
     */

    private RsmEntity rsm;
    /**
     * rsm : {"uid":2,"user_name":"小韭菜","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/02_avatar_max.jpg","sex":1,"province":"浙江省","city":"杭州市","fans_count":50,"friend_count":23,"article_count":6,"question_count":4,"answer_count":51,"topic_focus_count":28,"agree_count":240,"thanks_count":8,"views_count":162,"signature":"非职业投机客...","answer_favorite_count":1,"has_focus":0}
     * errno : 1
     * err : null
     */

    private int errno;
    private Object err;

    public void setRsm(RsmEntity rsm) {
        this.rsm = rsm;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public void setErr(Object err) {
        this.err = err;
    }

    public RsmEntity getRsm() {
        return rsm;
    }

    public int getErrno() {
        return errno;
    }

    public Object getErr() {
        return err;
    }

    public static class RsmEntity {
        private int uid;
        private String user_name;
        private String avatar_file;
        private int sex;
        private String province;
        private String city;
        private int fans_count;
        private int friend_count;
        private int article_count;
        private int question_count;
        private int answer_count;
        private int topic_focus_count;
        private int agree_count;
        private int thanks_count;
        private int views_count;
        private String signature;
        private int answer_favorite_count;
        private int has_focus;

        public void setUid(int uid) {
            this.uid = uid;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public void setAvatar_file(String avatar_file) {
            this.avatar_file = avatar_file;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setFans_count(int fans_count) {
            this.fans_count = fans_count;
        }

        public void setFriend_count(int friend_count) {
            this.friend_count = friend_count;
        }

        public void setArticle_count(int article_count) {
            this.article_count = article_count;
        }

        public void setQuestion_count(int question_count) {
            this.question_count = question_count;
        }

        public void setAnswer_count(int answer_count) {
            this.answer_count = answer_count;
        }

        public void setTopic_focus_count(int topic_focus_count) {
            this.topic_focus_count = topic_focus_count;
        }

        public void setAgree_count(int agree_count) {
            this.agree_count = agree_count;
        }

        public void setThanks_count(int thanks_count) {
            this.thanks_count = thanks_count;
        }

        public void setViews_count(int views_count) {
            this.views_count = views_count;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public void setAnswer_favorite_count(int answer_favorite_count) {
            this.answer_favorite_count = answer_favorite_count;
        }

        public void setHas_focus(int has_focus) {
            this.has_focus = has_focus;
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

        public int getSex() {
            return sex;
        }

        public String getProvince() {
            return province;
        }

        public String getCity() {
            return city;
        }

        public int getFans_count() {
            return fans_count;
        }

        public int getFriend_count() {
            return friend_count;
        }

        public int getArticle_count() {
            return article_count;
        }

        public int getQuestion_count() {
            return question_count;
        }

        public int getAnswer_count() {
            return answer_count;
        }

        public int getTopic_focus_count() {
            return topic_focus_count;
        }

        public int getAgree_count() {
            return agree_count;
        }

        public int getThanks_count() {
            return thanks_count;
        }

        public int getViews_count() {
            return views_count;
        }

        public String getSignature() {
            return signature;
        }

        public int getAnswer_favorite_count() {
            return answer_favorite_count;
        }

        public int getHas_focus() {
            return has_focus;
        }
    }
}
