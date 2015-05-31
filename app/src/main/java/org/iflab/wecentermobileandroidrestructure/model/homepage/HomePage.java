package org.iflab.wecentermobileandroidrestructure.model.homepage;

import org.litepal.crud.DataSupport;

/**
 * Created by hcjcch on 15/5/31.
 */
public class HomePage extends DataSupport{
    private UserInfo userInfo;
    private QuestionInfo questionInfo;
    private AnswerInfo answerInfo;
    private ArticleInfo articleInfo;
    private int historyId;
    private int uid;
    private int associateAction;
    private int associateId;
    private long addTime;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public QuestionInfo getQuestionInfo() {
        return questionInfo;
    }

    public void setQuestionInfo(QuestionInfo questionInfo) {
        this.questionInfo = questionInfo;
    }

    public AnswerInfo getAnswerInfo() {
        return answerInfo;
    }

    public void setAnswerInfo(AnswerInfo answerInfo) {
        this.answerInfo = answerInfo;
    }

    public ArticleInfo getArticleInfo() {
        return articleInfo;
    }

    public void setArticleInfo(ArticleInfo articleInfo) {
        this.articleInfo = articleInfo;
    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getAssociateAction() {
        return associateAction;
    }

    public void setAssociateAction(int associateAction) {
        this.associateAction = associateAction;
    }

    public int getAssociateId() {
        return associateId;
    }

    public void setAssociateId(int associateId) {
        this.associateId = associateId;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }
}