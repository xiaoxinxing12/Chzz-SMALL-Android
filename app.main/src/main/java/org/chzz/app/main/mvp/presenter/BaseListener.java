package org.chzz.app.main.mvp.presenter;

import org.chzz.app.main.model.bean.BaseEntity;

/**
 * Created by copy on 2016/4/24.
 */
public interface BaseListener {
    void result(BaseEntity model, int code);
}
