package org.iflab.wecentermobileandroidrestructure.model.article;

import org.litepal.crud.DataSupport;

/**
 * Created by hcjcch on 15/5/31.
 */
public class ArticleInfo extends DataSupport{


    /**
     * id : 30
     * uid : 19
     * title : 【分享】吴
     * message : 8月8日，
     * comments : 2
     * views : 70
     * add_time : 1439349922
     * has_attach : 0
     * lock : 0
     * votes : 2
     * title_fulltext : 2099820139 2
     * category_id : 1
     * is_recommend : 1
     * chapter_id : null
     * sort : 0
     * user_info : {"uid":19,"user_name":"放肆","signature":"好困。。。","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/19_avatar_mid.jpg"}
     * vote_info : {"id":30,"uid":3,"type":"article","item_id":30,"rating":1,"time":1439350026,"reputation_factor":1,"item_uid":19}
     * vote_users : null
     * user_follow_check : 1
     */

    private int id;
    private int uid;
    private String title;
    private String message;
    private int comments;
    private int views;
    private long add_time;
    private int has_attach;
    private int lock;
    private int votes;
    private String title_fulltext;
    private int category_id;
    private int is_recommend;
    private Object chapter_id;
    private int sort;
    /**
     * uid : 19
     * user_name : 放肆
     * signature : 好困。。。
     * avatar_file : http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/19_avatar_mid.jpg
     */

    private UserInfoEntity user_info;
    /**
     * id : 30
     * uid : 3
     * type : article
     * item_id : 30
     * rating : 1
     * time : 1439350026
     * reputation_factor : 1
     * item_uid : 19
     */

    private VoteInfoEntity vote_info;
    private Object vote_users;
    private int user_follow_check;

    public void setId(int id) {
        this.id = id;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setAdd_time(long add_time) {
        this.add_time = add_time;
    }

    public void setHas_attach(int has_attach) {
        this.has_attach = has_attach;
    }

    public void setLock(int lock) {
        this.lock = lock;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public void setTitle_fulltext(String title_fulltext) {
        this.title_fulltext = title_fulltext;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setIs_recommend(int is_recommend) {
        this.is_recommend = is_recommend;
    }

    public void setChapter_id(Object chapter_id) {
        this.chapter_id = chapter_id;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public void setUser_info(UserInfoEntity user_info) {
        this.user_info = user_info;
    }

    public void setVote_info(VoteInfoEntity vote_info) {
        this.vote_info = vote_info;
    }

    public void setVote_users(Object vote_users) {
        this.vote_users = vote_users;
    }

    public void setUser_follow_check(int user_follow_check) {
        this.user_follow_check = user_follow_check;
    }

    public int getId() {
        return id;
    }

    public int getUid() {
        return uid;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public int getComments() {
        return comments;
    }

    public int getViews() {
        return views;
    }

    public long getAdd_time() {
        return add_time;
    }

    public int getHas_attach() {
        return has_attach;
    }

    public int getLock() {
        return lock;
    }

    public int getVotes() {
        return votes;
    }

    public String getTitle_fulltext() {
        return title_fulltext;
    }

    public int getCategory_id() {
        return category_id;
    }

    public int getIs_recommend() {
        return is_recommend;
    }

    public Object getChapter_id() {
        return chapter_id;
    }

    public int getSort() {
        return sort;
    }

    public UserInfoEntity getUser_info() {
        return user_info;
    }

    public VoteInfoEntity getVote_info() {
        return vote_info;
    }

    public Object getVote_users() {
        return vote_users;
    }

    public int getUser_follow_check() {
        return user_follow_check;
    }

    public static class UserInfoEntity {
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

    public static class VoteInfoEntity {
        private int id;
        private int uid;
        private String type;
        private int item_id;
        private int rating;
        private int time;
        private int reputation_factor;
        private int item_uid;

        public void setId(int id) {
            this.id = id;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setItem_id(int item_id) {
            this.item_id = item_id;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public void setReputation_factor(int reputation_factor) {
            this.reputation_factor = reputation_factor;
        }

        public void setItem_uid(int item_uid) {
            this.item_uid = item_uid;
        }

        public int getId() {
            return id;
        }

        public int getUid() {
            return uid;
        }

        public String getType() {
            return type;
        }

        public int getItem_id() {
            return item_id;
        }

        public int getRating() {
            return rating;
        }

        public int getTime() {
            return time;
        }

        public int getReputation_factor() {
            return reputation_factor;
        }

        public int getItem_uid() {
            return item_uid;
        }
    }
}
