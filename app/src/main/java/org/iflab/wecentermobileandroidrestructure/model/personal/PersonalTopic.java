package org.iflab.wecentermobileandroidrestructure.model.personal;

import java.util.List;

/**
 * Description:
 *
 * @author huangchen
 * @version 1.0
 * @time 15/8/22 01:06
 */

public class PersonalTopic {

    /**
     * total_rows : 21
     * rows : [{"topic_pic":"","topic_title":"哈哈","topic_description":"","topic_id":"6"}]
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
         * topic_pic :
         * topic_title : 哈哈
         * topic_description :
         * topic_id : 6
         */
        private String topic_pic;
        private String topic_title;
        private String topic_description;
        private String topic_id;

        public void setTopic_pic(String topic_pic) {
            this.topic_pic = topic_pic;
        }

        public void setTopic_title(String topic_title) {
            this.topic_title = topic_title;
        }

        public void setTopic_description(String topic_description) {
            this.topic_description = topic_description;
        }

        public void setTopic_id(String topic_id) {
            this.topic_id = topic_id;
        }

        public String getTopic_pic() {
            return topic_pic;
        }

        public String getTopic_title() {
            return topic_title;
        }

        public String getTopic_description() {
            return topic_description;
        }

        public String getTopic_id() {
            return topic_id;
        }
    }
}
