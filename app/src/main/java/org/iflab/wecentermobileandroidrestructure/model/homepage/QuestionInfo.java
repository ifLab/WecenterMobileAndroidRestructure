package org.iflab.wecentermobileandroidrestructure.model.homepage;

import org.litepal.crud.DataSupport;

/**
 * Created by hcjcch on 15/5/31.
 */
public class QuestionInfo extends DataSupport{
    private int questionId;
    private String questionContent;
    private boolean hasFocus;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public boolean isHasFocus() {
        return hasFocus;
    }

    public void setHasFocus(boolean hasFocus) {
        this.hasFocus = hasFocus;
    }

    @Override
    public String toString() {
        return "QuestionInfo{" +
                "questionId=" + questionId +
                ", questionContent='" + questionContent + '\'' +
                ", hasFocus=" + hasFocus +
                '}';
    }
}