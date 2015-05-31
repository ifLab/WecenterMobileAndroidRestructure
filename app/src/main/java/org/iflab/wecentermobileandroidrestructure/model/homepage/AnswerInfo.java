package org.iflab.wecentermobileandroidrestructure.model.homepage;

import org.litepal.crud.DataSupport;

/**
 * Created by hcjcch on 15/5/31.
 */
public class AnswerInfo extends DataSupport{
    private int answerId;
    private int questionId;
    private String answerContent;
    private int agreeCount;
    private String agreestatus;

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public int getAgreeCount() {
        return agreeCount;
    }

    public void setAgreeCount(int agreeCount) {
        this.agreeCount = agreeCount;
    }

    public String getAgreestatus() {
        return agreestatus;
    }

    public void setAgreestatus(String agreestatus) {
        this.agreestatus = agreestatus;
    }
}