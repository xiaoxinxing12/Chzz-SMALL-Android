package org.chzz.app.main.model;

import org.chzz.app.main.presenter.listener.OnDataListener;

/**
 * Created by copy on 2016/4/24.
 */
public interface IndexModel {

    void getHotData(int page, OnDataListener onDataListener, int code);
}
