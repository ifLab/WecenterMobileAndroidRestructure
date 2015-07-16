package org.iflab.wecentermobileandroidrestructure.model.article;

import org.litepal.crud.DataSupport;

/**
 * Created by client on 15/7/16.
 */
public class ArticleRSM extends DataSupport {
    ArticleInfo article_info;
    ArticleTopics article_topics;

    public ArticleInfo getArticleInfo() {
        return article_info;
    }

    public void setArticleInfo(ArticleInfo articleInfo) {
        this.article_info = articleInfo;
    }

    public ArticleTopics getArticleTopics() {
        return article_topics;
    }

    public void setArticleTopics(ArticleTopics articleTopics) {
        this.article_topics = articleTopics;
    }

    @Override
    public String toString() {
        return "ArticleRSM{" +
                "articleInfo=" + article_info +
                ", articleTopics=" + article_topics +
                '}';
    }
}
