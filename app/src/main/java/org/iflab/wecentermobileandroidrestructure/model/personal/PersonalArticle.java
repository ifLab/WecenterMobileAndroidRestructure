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
     * rows : [{"id":"5","title":"文章文章文章文章文章文章文章文章文章文章文章文章文章文章文章","message":"文章文章文章文章文章文章文章文章文章文章文章文章文章文章文章","add_time":"1434897495"}]
     */
    private String total_rows;
    private List<RowsEntity> rows;

    public void setTotal_rows(String total_rows) {
        this.total_rows = total_rows;
    }

    public void setRows(List<RowsEntity> rows) {
        this.rows = rows;
    }

    public String getTotal_rows() {
        return total_rows;
    }

    public List<RowsEntity> getRows() {
        return rows;
    }

    public static class RowsEntity {
        /**
         * id : 5
         * title : 文章文章文章文章文章文章文章文章文章文章文章文章文章文章文章
         * message : 文章文章文章文章文章文章文章文章文章文章文章文章文章文章文章
         * add_time : 1434897495
         */
        private String id;
        private String title;
        private String message;
        private String add_time;

        public void setId(String id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getMessage() {
            return message;
        }

        public String getAdd_time() {
            return add_time;
        }
    }
}
