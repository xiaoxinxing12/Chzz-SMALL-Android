package org.chzz.app.main.model.bean;

/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/5/21 14:53
 * 描述:
 */
public class RefreshEntity extends BaseEntity {
    public String title;
    public String detail;

    public RefreshEntity() {
    }

    public RefreshEntity(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }
}