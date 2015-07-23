package org.iflab.wecentermobileandroidrestructure.model.found;

import java.util.Calendar;

/**
 * Description:
 *
 * @author huangchen
 * @version 1.0
 * @time 15/7/21 23:17
 */

public class ArticleInfo extends BaseFoundInfo{
    private int articleId;
    private String articleTitle;
    private String articleMessage;
    private long addTime;
    private int votes;
    private int uid;//发布者的id
    private String avatarFile;
    private String userName;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleMessage() {
        return articleMessage;
    }

    public void setArticleMessage(String articleMessage) {
        this.articleMessage = articleMessage;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getAvatarFile() {
        return avatarFile;
    }

    public void setAvatarFile(String avatarFile) {
        this.avatarFile = avatarFile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}