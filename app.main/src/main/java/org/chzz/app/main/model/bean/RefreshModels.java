package org.chzz.app.main.model.bean;

import java.util.List;

/**
 * Created by copy on 2016/4/24.
 */
public class RefreshModels extends BaseEntity {


    /**
     * detail : 最新描述7
     * title : 最新标题7
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends BaseEntity {
        private String detail;
        private String title;

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
