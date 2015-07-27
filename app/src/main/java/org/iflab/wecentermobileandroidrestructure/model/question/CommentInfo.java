package org.iflab.wecentermobileandroidrestructure.model.question;

/**
 * Created by Lyn on 15/7/23.
 */
public class CommentInfo {
    int id;
    int uid;
    String user_name;
    String content;
    Long add_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Long add_time) {
        this.add_time = add_time;
    }

    @Override
    public String toString() {
        return "CommentInfo{" +
                "id=" + id +
                ", uid=" + uid +
                ", user_name='" + user_name + '\'' +
                ", content='" + content + '\'' +
                ", add_time=" + add_time +
                '}';
    }
}
