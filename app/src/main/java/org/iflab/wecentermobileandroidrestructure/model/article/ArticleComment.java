package org.iflab.wecentermobileandroidrestructure.model.article;

/**
 * Created by Lyn on 15/7/31.
 */
public class ArticleComment {

    int id;
    int uid;
    String message;
    long add_time;
    int at_uid;
    int votes;
    UserInfo user_info;
    AtUserInfo at_user_info;
    int vote_value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(long add_time) {
        this.add_time = add_time;
    }

    public int getAt_uid() {
        return at_uid;
    }

    public void setAt_uid(int at_uid) {
        this.at_uid = at_uid;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public UserInfo getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfo user_info) {
        this.user_info = user_info;
    }

    public AtUserInfo getAt_user_info() {
        return at_user_info;
    }

    public void setAt_user_info(AtUserInfo at_user_info) {
        this.at_user_info = at_user_info;
    }

    public int getVote_value() {
        return vote_value;
    }

    public void setVote_value(int vote_value) {
        this.vote_value = vote_value;
    }


    @Override
    public String toString() {
        return "ArticleComment{" +
                "id=" + id +
                ", uid=" + uid +
                ", message='" + message + '\'' +
                ", add_time=" + add_time +
                ", at_uid=" + at_uid +
                ", votes=" + votes +
                ", user_info=" + user_info.toString() +
                ", at_user_info='" + "at_user_info is null" + '\'' +
                ", vote_value=" + vote_value +
                '}';
    }

    public static class UserInfo {
        int uid;
        String user_name;
        String avatar_file;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getAvatar_file() {
            return avatar_file;
        }

        public void setAvatar_file(String avatar_file) {
            this.avatar_file = avatar_file;
        }

        @Override
        public String toString() {
            return "{" +
                    "uid=" + uid +
                    ", user_name='" + user_name + '\'' +
                    ", avatar_file='" + avatar_file + '\'' +
                    '}';
        }
    }

    public static class AtUserInfo{

    }
}
