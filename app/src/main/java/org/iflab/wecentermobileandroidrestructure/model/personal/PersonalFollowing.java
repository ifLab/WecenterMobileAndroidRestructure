package org.iflab.wecentermobileandroidrestructure.model.personal;

import java.util.List;

/**
 * Description:
 *
 * @author huangchen
 * @version 1.0
 * @time 15/8/22 10:41
 */

public class PersonalFollowing {

    /**
     * total_rows : 2
     * rows : [{"uid":"5","avatar_file":"000/00/00/05_avatar_max.jpg","signature":"Creating a world of pure white.","user_name":"纯白色蝴蝶"},{"uid":"9","avatar_file":"","signature":"","user_name":"billhu1996"}]
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
         * uid : 5
         * avatar_file : 000/00/00/05_avatar_max.jpg
         * signature : Creating a world of pure white.
         * user_name : 纯白色蝴蝶
         */
        private String uid;
        private String avatar_file;
        private String signature;
        private String user_name;

        public void setUid(String uid) {
            this.uid = uid;
        }

        public void setAvatar_file(String avatar_file) {
            this.avatar_file = avatar_file;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUid() {
            return uid;
        }

        public String getAvatar_file() {
            return avatar_file;
        }

        public String getSignature() {
            return signature;
        }

        public String getUser_name() {
            return user_name;
        }
    }
}
