package org.chzz.app.main.model;

import org.chzz.app.main.presenter.listener.OnDataListener;

import java.util.Map;

/**
 * Created by copy on 2016/4/25.
 */
public interface LoginModel {
    void userLogin(Map<String, String> params, OnDataListener onDataListener, int code);
}
