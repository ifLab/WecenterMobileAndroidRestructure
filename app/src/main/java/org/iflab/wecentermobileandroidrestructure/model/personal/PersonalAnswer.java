package org.iflab.wecentermobileandroidrestructure.model.personal;

import java.util.List;

/**
 * Description:
 *
 * @author huangchen
 * @version 1.0
 * @time 15/8/17 23:51
 */

public class PersonalAnswer {


    /**
     * total_rows : 1
     * rows : [{"history_id":4358,"associate_action":201,"add_time":1448781963,"answer_info":{"answer_id":311,"answer_content":"哈哈哈 测试测试\r\n \r\n \r\n\r\n[attach]47[/attach]\r\n ","add_time":1448781963,"against_count":0,"agree_count":0},"question_info":{"question_id":167,"question_content":"全国房地产开发商前五十名有哪些？","add_time":1444038484,"update_time":1448781963,"answer_count":7,"agree_count":2}}]
     */

    private int total_rows;
    /**
     * history_id : 4358
     * associate_action : 201
     * add_time : 1448781963
     * answer_info : {"answer_id":311,"answer_content":"哈哈哈 测试测试\r\n \r\n \r\n\r\n[attach]47[/attach]\r\n ","add_time":1448781963,"against_count":0,"agree_count":0}
     * question_info : {"question_id":167,"question_content":"全国房地产开发商前五十名有哪些？","add_time":1444038484,"update_time":1448781963,"answer_count":7,"agree_count":2}
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
         * answer_id : 311
         * answer_content : 哈哈哈 测试测试
          
          

         [attach]47[/attach]
          
         * add_time : 1448781963
         * against_count : 0
         * agree_count : 0
         */

        private AnswerInfoEntity answer_info;
        /**
         * question_id : 167
         * question_content : 全国房地产开发商前五十名有哪些？
         * add_time : 1444038484
         * update_time : 1448781963
         * answer_count : 7
         * agree_count : 2
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

        public void setAnswer_info(AnswerInfoEntity answer_info) {
            this.answer_info = answer_info;
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

        public AnswerInfoEntity getAnswer_info() {
            return answer_info;
        }

        public QuestionInfoEntity getQuestion_info() {
            return question_info;
        }

        public static class AnswerInfoEntity {
            private int answer_id;
            private String answer_content;
            private long add_time;
            private int against_count;
            private int agree_count;

            public void setAnswer_id(int answer_id) {
                this.answer_id = answer_id;
            }

            public void setAnswer_content(String answer_content) {
                this.answer_content = answer_content;
            }

            public void setAdd_time(long add_time) {
                this.add_time = add_time;
            }

            public void setAgainst_count(int against_count) {
                this.against_count = against_count;
            }

            public void setAgree_count(int agree_count) {
                this.agree_count = agree_count;
            }

            public int getAnswer_id() {
                return answer_id;
            }

            public String getAnswer_content() {
                return answer_content;
            }

            public long getAdd_time() {
                return add_time;
            }

            public int getAgainst_count() {
                return against_count;
            }

            public int getAgree_count() {
                return agree_count;
            }
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
