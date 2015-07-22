package org.iflab.wecentermobileandroidrestructure.model.question;

/**
 * Created by client on 15/7/18.
 */
public class AnswerInfo {

    int answer_id;
    String answer_content;
    int agree_count;
    int uid;
    String user_name;
    String avatar_file;
    String signature;
    Long add_time;
    int vote_value;

    public Long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Long add_time) {
        this.add_time = add_time;
    }

    public int getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }

    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }

    public int getAgree_count() {
        return agree_count;
    }

    public void setAgree_count(int agree_count) {
        this.agree_count = agree_count;
    }

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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getVote_value() {
        return vote_value;
    }

    public void setVote_value(int vote_value) {
        this.vote_value = vote_value;
    }

    @Override
    public String toString() {
        return "AnswerInfo{" +
                "answer_id=" + answer_id +
                ", answer_content='" + answer_content + '\'' +
                ", agree_count=" + agree_count +
                ", uid=" + uid +
                ", user_name='" + user_name + '\'' +
                ", avatar_file='" + avatar_file + '\'' +
                ", signature='" + signature + '\'' +
                ", vote_value=" + vote_value +
                '}';
    }
}
