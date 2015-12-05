package org.iflab.wecentermobileandroidrestructure.model.personal;

import java.util.List;

/**
 * Description:
 *
 * @author huangchen
 * @version 1.0
 * @time 15/8/15 13:37
 */

public class PersonalArticle {


    /**
     * total_rows : 1
     * rows : [{"history_id":16,"associate_action":501,"add_time":1434706630,"article_info":{"id":3,"title":"读懂ETF，你不再是新韭菜","message":"为什么会发明ETF这样的产品：交易成本低（和买卖股票一样）、有效跟踪指数（不象封闭式基金有很大的折价）、流动性好（有做市商机制和套利盘），而且非常透明（严格复制指数）。","comments":0,"views":36,"add_time":1434706630}}]
     */

    private int total_rows;
    /**
     * history_id : 16
     * associate_action : 501
     * add_time : 1434706630
     * article_info : {"id":3,"title":"读懂ETF，你不再是新韭菜","message":"为什么会发明ETF这样的产品：交易成本低（和买卖股票一样）、有效跟踪指数（不象封闭式基金有很大的折价）、流动性好（有做市商机制和套利盘），而且非常透明（严格复制指数）。","comments":0,"views":36,"add_time":1434706630}
     */

    private List<RowsEntity> rows;

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public void setRows(List<RowsEntity> rows) {
        this.rows = rows;
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public List<RowsEntity> getRows() {
        return rows;
    }

    public static class RowsEntity {
        private int history_id;
        private int associate_action;
        private long add_time;
        /**
         * id : 3
         * title : 读懂ETF，你不再是新韭菜
         * message : 为什么会发明ETF这样的产品：交易成本低（和买卖股票一样）、有效跟踪指数（不象封闭式基金有很大的折价）、流动性好（有做市商机制和套利盘），而且非常透明（严格复制指数）。
         * comments : 0
         * views : 36
         * add_time : 1434706630
         */

        private ArticleInfoEntity article_info;

        public void setHistory_id(int history_id) {
            this.history_id = history_id;
        }

        public void setAssociate_action(int associate_action) {
            this.associate_action = associate_action;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }

        public void setArticle_info(ArticleInfoEntity article_info) {
            this.article_info = article_info;
        }

        public int getHistory_id() {
            return history_id;
        }

        public int getAssociate_action() {
            return associate_action;
        }

        public long getAdd_time() {
            return add_time;
        }

        public ArticleInfoEntity getArticle_info() {
            return article_info;
        }

        public static class ArticleInfoEntity {
            private int id;
            private String title;
            private String message;
            private int comments;
            private int views;
            private long add_time;

            public void setId(int id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public void setComments(int comments) {
                this.comments = comments;
            }

            public void setViews(int views) {
                this.views = views;
            }

            public void setAdd_time(long add_time) {
                this.add_time = add_time;
            }

            public int getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public String getMessage() {
                return message;
            }

            public int getComments() {
                return comments;
            }

            public int getViews() {
                return views;
            }

            public long getAdd_time() {
                return add_time;
            }
        }
    }
}
