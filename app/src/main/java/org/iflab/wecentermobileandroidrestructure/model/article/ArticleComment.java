package org.iflab.wecentermobileandroidrestructure.model.article;

import java.util.List;

/**
 * Created by Lyn on 15/7/31.
 */
public class ArticleComment {


    /**
     * total_rows : 2
     * rows : [{"id":1,"uid":3,"article_id":30,"message":"GG ","add_time":1447911331,"at_uid":0,"votes":0,"user_info":{"uid":3,"user_name":"BugFree","signature":"绝望中新生，迟疑中上涨，欢乐中死亡，企盼中下跌。","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/03_avatar_mid.jpg"},"at_user_info":null,"vote_info":null},{"id":2,"uid":3,"article_id":30,"message":"GG个","add_time":1447911338,"at_uid":3,"votes":0,"user_info":{"uid":3,"user_name":"BugFree","signature":"绝望中新生，迟疑中上涨，欢乐中死亡，企盼中下跌。","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/03_avatar_mid.jpg"},"at_user_info":{"uid":3,"user_name":"BugFree","signature":"绝望中新生，迟疑中上涨，欢乐中死亡，企盼中下跌。","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/03_avatar_mid.jpg"},"vote_info":null}]
     */

    private int total_rows;
    /**
     * id : 1
     * uid : 3
     * article_id : 30
     * message : GG
     * add_time : 1447911331
     * at_uid : 0
     * votes : 0
     * user_info : {"uid":3,"user_name":"BugFree","signature":"绝望中新生，迟疑中上涨，欢乐中死亡，企盼中下跌。","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/03_avatar_mid.jpg"}
     * at_user_info : null
     * vote_info : null
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
        private int id;
        private int uid;
        private int article_id;
        private String message;
        private long add_time;
        private int at_uid;
        private int votes;
        /**
         * uid : 3
         * user_name : BugFree
         * signature : 绝望中新生，迟疑中上涨，欢乐中死亡，企盼中下跌。
         * avatar_file : http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/03_avatar_mid.jpg
         */

        private UserInfoEntity user_info;
        private Object at_user_info;
        private Object vote_info;

        public void setId(int id) {
            this.id = id;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public void setArticle_id(int article_id) {
            this.article_id = article_id;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }

        public void setAt_uid(int at_uid) {
            this.at_uid = at_uid;
        }

        public void setVotes(int votes) {
            this.votes = votes;
        }

        public void setUser_info(UserInfoEntity user_info) {
            this.user_info = user_info;
        }

        public void setAt_user_info(Object at_user_info) {
            this.at_user_info = at_user_info;
        }

        public void setVote_info(Object vote_info) {
            this.vote_info = vote_info;
        }

        public int getId() {
            return id;
        }

        public int getUid() {
            return uid;
        }

        public int getArticle_id() {
            return article_id;
        }

        public String getMessage() {
            return message;
        }

        public long getAdd_time() {
            return add_time;
        }

        public int getAt_uid() {
            return at_uid;
        }

        public int getVotes() {
            return votes;
        }

        public UserInfoEntity getUser_info() {
            return user_info;
        }

        public Object getAt_user_info() {
            return at_user_info;
        }

        public Object getVote_info() {
            return vote_info;
        }

        public static class UserInfoEntity {
            private int uid;
            private String user_name;
            private String signature;
            private String avatar_file;

            public void setUid(int uid) {
                this.uid = uid;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
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

            public String getSignature() {
                return signature;
            }

            public String getAvatar_file() {
                return avatar_file;
            }
        }
    }
}
