package org.iflab.wecentermobileandroidrestructure.model.question;

import java.util.List;

/**
 * Created by Lyn on 15/7/23.
 */
public class CommentInfo {


    /**
     * rsm : [{"id":2,"answer_id":291,"uid":3,"message":"恩恩","time":1447899049,"user_info":{"uid":3,"user_name":"BugFree","signature":"绝望中新生，迟疑中上涨，欢乐中死亡，企盼中下跌。","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/03_avatar_mid.jpg"}},{"id":3,"answer_id":291,"uid":3,"message":"@3:中么么啦额","time":1447899057,"at_user":{"3":{"uid":3,"user_name":"BugFree","signature":"绝望中新生，迟疑中上涨，欢乐中死亡，企盼中下跌。","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/03_avatar_mid.jpg"}},"user_info":{"uid":3,"user_name":"BugFree","signature":"绝望中新生，迟疑中上涨，欢乐中死亡，企盼中下跌。","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/03_avatar_mid.jpg"}},{"id":4,"answer_id":291,"uid":3,"message":"@3: @1 @62  哈哈哈","time":1447899982,"at_user":{"1":{"uid":1,"user_name":"Hwei","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/01_avatar_mid.jpg"},"3":{"uid":3,"user_name":"BugFree","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/03_avatar_mid.jpg"},"62":{"uid":62,"user_name":"Hwei5047","avatar_file":"http://wecenter.dev.hihwei.com/static/common/avatar-mid-img.png"}},"user_info":{"uid":3,"user_name":"BugFree","signature":"绝望中新生，迟疑中上涨，欢乐中死亡，企盼中下跌。","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/03_avatar_mid.jpg"}}]
     * errno : 1
     * err : null
     */

    private int errno;
    private Object err;
    private List<RsmEntity> rsm;

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public void setErr(Object err) {
        this.err = err;
    }

    public void setRsm(List<RsmEntity> rsm) {
        this.rsm = rsm;
    }

    public int getErrno() {
        return errno;
    }

    public Object getErr() {
        return err;
    }

    public List<RsmEntity> getRsm() {
        return rsm;
    }

    public static class RsmEntity {
        /**
         * id : 2
         * answer_id : 291
         * uid : 3
         * message : 恩恩
         * time : 1447899049
         * user_info : {"uid":3,"user_name":"BugFree","signature":"绝望中新生，迟疑中上涨，欢乐中死亡，企盼中下跌。","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/03_avatar_mid.jpg"}
         */

        private int id;
        private int answer_id;
        private int uid;
        private String message;
        private int time;
        private UserInfoEntity user_info;

        public void setId(int id) {
            this.id = id;
        }

        public void setAnswer_id(int answer_id) {
            this.answer_id = answer_id;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public void setUser_info(UserInfoEntity user_info) {
            this.user_info = user_info;
        }

        public int getId() {
            return id;
        }

        public int getAnswer_id() {
            return answer_id;
        }

        public int getUid() {
            return uid;
        }

        public String getMessage() {
            return message;
        }

        public int getTime() {
            return time;
        }

        public UserInfoEntity getUser_info() {
            return user_info;
        }

        public static class UserInfoEntity {
            /**
             * uid : 3
             * user_name : BugFree
             * signature : 绝望中新生，迟疑中上涨，欢乐中死亡，企盼中下跌。
             * avatar_file : http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/03_avatar_mid.jpg
             */

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
