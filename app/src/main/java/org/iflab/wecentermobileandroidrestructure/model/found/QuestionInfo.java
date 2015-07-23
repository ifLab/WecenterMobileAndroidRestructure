package org.iflab.wecentermobileandroidrestructure.model.found;

import java.util.Calendar;

/**
 * Description:
 *
 * @author huangchen
 * @version 1.0
 * @time 15/7/21 23:17
 */

public class QuestionInfo extends BaseFoundInfo{
    private int QuestionId;
    private String questionContent;
    private String questionDetail;
    private String  updateTime;
    private int publishUid;
    private String publishUserName;
    private String publishAvatarFile;
    private int answerUid;
    private String answerUserName;
    private String answerAvatarFile;
    private String answerContent;

    public int getQuestionId() {
        return QuestionId;
    }

    public void setQuestionId(int questionId) {
        QuestionId = questionId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getQuestionDetail() {
        return questionDetail;
    }

    public void setQuestionDetail(String questionDetail) {
        this.questionDetail = questionDetail;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getPublishUid() {
        return publishUid;
    }

    public void setPublishUid(int publishUid) {
        this.publishUid = publishUid;
    }

    public String getPublishUserName() {
        return publishUserName;
    }

    public void setPublishUserName(String publishUserName) {
        this.publishUserName = publishUserName;
    }

    public String getPublishAvatarFile() {
        return publishAvatarFile;
    }

    public void setPublishAvatarFile(String publishAvatarFile) {
        this.publishAvatarFile = publishAvatarFile;
    }

    public int getAnswerUid() {
        return answerUid;
    }

    public void setAnswerUid(int answerUid) {
        this.answerUid = answerUid;
    }

    public String getAnswerUserName() {
        return answerUserName;
    }

    public void setAnswerUserName(String answerUserName) {
        this.answerUserName = answerUserName;
    }

    public String getAnswerAvatarFile() {
        return answerAvatarFile;
    }

    public void setAnswerAvatarFile(String answerAvatarFile) {
        this.answerAvatarFile = answerAvatarFile;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }
}