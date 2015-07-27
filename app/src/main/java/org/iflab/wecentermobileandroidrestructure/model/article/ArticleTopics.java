package org.iflab.wecentermobileandroidrestructure.model.article;

import org.litepal.crud.DataSupport;

/**
 * Created by Lyn on 15/7/16.
 */
public class ArticleTopics extends DataSupport {
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
        return "ArticleTopics{" +
                "topic_id=" + topic_id +
                ", topic_title='" + topic_title + '\'' +
                '}';
    }
}
