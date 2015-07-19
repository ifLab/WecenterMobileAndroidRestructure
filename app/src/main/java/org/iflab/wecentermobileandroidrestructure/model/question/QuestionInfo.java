package org.iflab.wecentermobileandroidrestructure.model.question;

/**
 * Created by client on 15/7/18.
 */
public class QuestionInfo {

    int question_id;
    String question_content;
    String question_detail;
    int focus_count;
    int has_focus;

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    public String getQuestion_detail() {
        return question_detail;
    }

    public void setQuestion_detail(String question_detail) {
        this.question_detail = question_detail;
    }

    public int getFocus_count() {
        return focus_count;
    }

    public void setFocus_count(int focus_count) {
        this.focus_count = focus_count;
    }

    public int getHas_focus() {
        return has_focus;
    }

    public void setHas_focus(int has_focus) {
        this.has_focus = has_focus;
    }


    @Override
    public String toString() {
        return "QuestionInfo{" +
                "question_id=" + question_id +
                ", question_content='" + question_content + '\'' +
                ", question_detail='" + question_detail + '\'' +
                ", focus_count=" + focus_count +
                ", has_focus=" + has_focus +
                '}';
    }
}
