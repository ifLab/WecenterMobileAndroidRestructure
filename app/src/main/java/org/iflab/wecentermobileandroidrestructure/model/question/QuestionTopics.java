package org.iflab.wecentermobileandroidrestructure.model.question;

/**
 * Created by Lyn on 15/7/18.
 */
public class QuestionTopics {
    int topic_id;
    String topic_title;

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public String getTopic_title() {
        return topic_title;
    }

    public void setTopic_title(String topic_title) {
        this.topic_title = topic_title;
    }

    @Override
    public String toString() {
        return "QuestionTopics{" +
                "topic_id=" + topic_id +
                ", topic_title='" + topic_title + '\'' +
                '}';
    }
}
