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
     * total_rows : 10
     * rows : [{"uid":25,"user_name":"Daflsh","agree_count":0,"thanks_count":0,"reputation":0,"signature":null,"avatar_file":"http://wecenter.dev.hihwei.com/static/common/avatar-mid-img.png"}]
     */

    private int total_rows;
    /**
     * uid : 25
     * user_name : Daflsh
     * agree_count : 0
     * thanks_count : 0
     * reputation : 0
     * signature : null
     * avatar_file : http://wecenter.dev.hihwei.com/static/common/avatar-mid-img.png
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
        private int uid;
        private String user_name;
        private int agree_count;
        private int thanks_count;
        private int reputation;
        private String signature;
        private String avatar_file;

        public void setUid(int uid) {
            this.uid = uid;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public void setAgree_count(int agree_count) {
            this.agree_count = agree_count;
        }

        public void setThanks_count(int thanks_count) {
            this.thanks_count = thanks_count;
        }

        public void setReputation(int reputation) {
            this.reputation = reputation;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public void setAvatar_file(String avatar_file) {
            this.avatar_file = avatar_file;
        }

        public int getUid() {
            return uid;
        }

        public String getUser_name() {
            return user_name;
        }

        public int getAgree_count() {
            return agree_count;
        }

        public int getThanks_count() {
            return thanks_count;
        }

        public int getReputation() {
            return reputation;
        }

        public String getSignature() {
            return signature;
        }

        public String getAvatar_file() {
            return avatar_file;
        }
    }
}
