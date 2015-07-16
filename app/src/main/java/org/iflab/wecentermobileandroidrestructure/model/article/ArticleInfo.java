package org.iflab.wecentermobileandroidrestructure.model.article;

import org.litepal.crud.DataSupport;

/**
 * Created by hcjcch on 15/5/31.
 */
public class ArticleInfo extends DataSupport{
    int id;
    int uid;
    String title;
    String message;
    int votes;
    String user_name;
    String avatar_file;
    String signature;
    int vote_value;

    public int getArticleId() {
        return id;
    }

    public void setArticleId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getArticleTitle() {
        return title;
    }

    public void setArticleTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
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
        return "ArticleInfo{" +
                "id=" + id +
                ", uid=" + uid +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", votes=" + votes +
                ", user_name='" + user_name + '\'' +
                ", avatar_file='" + avatar_file + '\'' +
                ", signature='" + signature + '\'' +
                ", vote_value=" + vote_value +
                '}';
    }
}
