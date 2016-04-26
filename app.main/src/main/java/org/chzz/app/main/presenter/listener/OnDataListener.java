package org.chzz.app.main.presenter.listener;

import org.chzz.app.main.model.bean.BaseEntity;

/**
 * Created by copy on 2016/4/24.
 */
public interface OnDataListener {
    void result(BaseEntity model, int code);
}
