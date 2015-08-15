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
     * total_rows : 18
     * rows : [{"id":"30","detail":"testhuaweiphoto[attach]267[/attach] [attach]268[/attach] [attach]269[/attach] [attach]270[/attach] [attach]271[/attach] [attach]272[/attach] ","title":"testhuaweiphoto","add_time":"1434893174"},{"id":"29","detail":"huaweic8801","title":"huaweic8801","add_time":"1434892468"},{"id":"28","detail":"删除图片删除图片[attach]265[/attach] [attach]266[/attach] ","title":"删除图片删除图片","add_time":"1434854734"},{"id":"27","detail":"poiuyp[attach]234[/attach] [attach]235[/attach] ","title":"poiuytp","add_time":"1434798314"},{"id":"26","detail":"qwrtuioo[attach]233[/attach] ","title":"qwrtuio","add_time":"1434797638"},{"id":"25","detail":"hhhhmmfmkf","title":"jcjfkkglg","add_time":"1434796709"},{"id":"24","detail":"fjfjjfjfnfnfn","title":"teteyegdhdh","add_time":"1434796346"},{"id":"23","detail":"手机发布手机发布[attach]151[/attach] [attach]152[/attach] [attach]153[/attach] [attach]154[/attach] [attach]155[/attach] [attach]156[/attach] ","title":"手机发布手机发布","add_time":"1434723315"},{"id":"22","detail":"testtest[attach]151[/attach] [attach]152[/attach] ","title":"testtest","add_time":"1434722836"},{"id":"21","detail":"tetstest","title":"testtest","add_time":"1434722315"}]
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
         * id : 30
         * detail : testhuaweiphoto[attach]267[/attach] [attach]268[/attach] [attach]269[/attach] [attach]270[/attach] [attach]271[/attach] [attach]272[/attach]
         * title : testhuaweiphoto
         * add_time : 1434893174
         */
        private String id;
        private String detail;
        private String title;
        private String add_time;

        public void setId(String id) {
            this.id = id;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getId() {
            return id;
        }

        public String getDetail() {
            return detail;
        }

        public String getTitle() {
            return title;
        }

        public String getAdd_time() {
            return add_time;
        }
    }
}
