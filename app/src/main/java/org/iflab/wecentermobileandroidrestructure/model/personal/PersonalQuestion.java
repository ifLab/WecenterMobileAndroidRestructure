package org.iflab.wecentermobileandroidrestructure.model.personal;

import java.util.List;

/**
 * Description:
 *
 * @author huangchen
 * @version 1.0
 * @time 15/8/8 20:43
 */

public class PersonalQuestion {


    /**
     * total_rows : 1
     * rows : [{"history_id":3032,"associate_action":101,"add_time":1441436907,"question_info":{"question_id":126,"question_content":"谁有中概股公司最新的私有化进程信息和当前溢价统计表？","add_time":1441436907,"update_time":1441437126,"answer_count":1,"agree_count":7}}]
     */

    private int total_rows;
    /**
     * history_id : 3032
     * associate_action : 101
     * add_time : 1441436907
     * question_info : {"question_id":126,"question_content":"谁有中概股公司最新的私有化进程信息和当前溢价统计表？","add_time":1441436907,"update_time":1441437126,"answer_count":1,"agree_count":7}
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
         * question_id : 126
         * question_content : 谁有中概股公司最新的私有化进程信息和当前溢价统计表？
         * add_time : 1441436907
         * update_time : 1441437126
         * answer_count : 1
         * agree_count : 7
         */

        private QuestionInfoEntity question_info;

        public void setHistory_id(int history_id) {
            this.history_id = history_id;
        }

        public void setAssociate_action(int associate_action) {
            this.associate_action = associate_action;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }

        public void setQuestion_info(QuestionInfoEntity question_info) {
            this.question_info = question_info;
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

        public QuestionInfoEntity getQuestion_info() {
            return question_info;
        }

        public static class QuestionInfoEntity {
            private int question_id;
            private String question_content;
            private long add_time;
            private int update_time;
            private int answer_count;
            private int agree_count;

            public void setQuestion_id(int question_id) {
                this.question_id = question_id;
            }

            public void setQuestion_content(String question_content) {
                this.question_content = question_content;
            }

            public void setAdd_time(long add_time) {
                this.add_time = add_time;
            }

            public void setUpdate_time(int update_time) {
                this.update_time = update_time;
            }

            public void setAnswer_count(int answer_count) {
                this.answer_count = answer_count;
            }

            public void setAgree_count(int agree_count) {
                this.agree_count = agree_count;
            }

            public int getQuestion_id() {
                return question_id;
            }

            public String getQuestion_content() {
                return question_content;
            }

            public long getAdd_time() {
                return add_time;
            }

            public int getUpdate_time() {
                return update_time;
            }

            public int getAnswer_count() {
                return answer_count;
            }

            public int getAgree_count() {
                return agree_count;
            }
        }
    }
}
