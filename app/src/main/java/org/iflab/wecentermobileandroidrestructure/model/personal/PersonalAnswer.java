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
     * total_rows : 5
     * rows : [{"agree_count":"0","answer_content":"不陪","avatar_file":"000/00/00/04_avatar_max.jpg","question_title":"你吓到我了，赔钱！","answer_id":"27","question_id":"14"},{"agree_count":"1","answer_content":"huida\n[attach]273[/attach]","avatar_file":"000/00/00/04_avatar_max.jpg","question_title":"huaweic8801","answer_id":"17","question_id":"29"},{"agree_count":"1","answer_content":"当然是**java**好了\n- {{{\nSystem.out.println(&quot;I'm good&quot;)\n}}}","avatar_file":"000/00/00/04_avatar_max.jpg","question_title":"大四专业课.java与.net选哪个好呢","answer_id":"11","question_id":"8"},{"agree_count":"0","answer_content":"ddsdsdsd\n[attach]53[/attach]\n[attach]52[/attach]\n[attach]54[/attach]\n[attach]58[/attach]\n[attach]55[/attach]\n[attach]56[/attach]\n[attach]59[/attach]\n[attach]60[/attach]\n[attach]57[/attach]\n[attach]62[/attach]\n[attach]63[/attach]\n[attach]61[/attach]\n[attach]66[/attach]\n[attach]67[/attach]\n[attach]68[/attach]\n[attach]64[/attach]\n[attach]65[/attach]","avatar_file":"000/00/00/04_avatar_max.jpg","question_title":"北京信息科技大学最美的时刻","answer_id":"8","question_id":"5"},{"agree_count":"1","answer_content":" 哈哈哈哈哈哈哈哈","avatar_file":"000/00/00/04_avatar_max.jpg","question_title":"我是热门用户","answer_id":"4","question_id":"2"}]
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
         * agree_count : 0
         * answer_content : 不陪
         * avatar_file : 000/00/00/04_avatar_max.jpg
         * question_title : 你吓到我了，赔钱！
         * answer_id : 27
         * question_id : 14
         */
        private String agree_count;
        private String answer_content;
        private String avatar_file;
        private String question_title;
        private String answer_id;
        private String question_id;

        public void setAgree_count(String agree_count) {
            this.agree_count = agree_count;
        }

        public void setAnswer_content(String answer_content) {
            this.answer_content = answer_content;
        }

        public void setAvatar_file(String avatar_file) {
            this.avatar_file = avatar_file;
        }

        public void setQuestion_title(String question_title) {
            this.question_title = question_title;
        }

        public void setAnswer_id(String answer_id) {
            this.answer_id = answer_id;
        }

        public void setQuestion_id(String question_id) {
            this.question_id = question_id;
        }

        public String getAgree_count() {
            return agree_count;
        }

        public String getAnswer_content() {
            return answer_content;
        }

        public String getAvatar_file() {
            return avatar_file;
        }

        public String getQuestion_title() {
            return question_title;
        }

        public String getAnswer_id() {
            return answer_id;
        }

        public String getQuestion_id() {
            return question_id;
        }
    }
}
