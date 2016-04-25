package org.chzz.app.main.model.impl;

import org.chzz.app.main.AppContext;
import org.chzz.app.main.engine.DataCallback;
import org.chzz.app.main.engine.Engine;
import org.chzz.app.main.model.LoginModel;
import org.chzz.app.main.model.bean.BaseModel;
import org.chzz.app.main.model.bean.LoginEntity;
import org.chzz.app.main.model.bean.RefreshModels;
import org.chzz.app.main.presenter.listener.OnDataListener;

import java.util.Map;

/**
 * Created by copy on 2016/4/25.
 */
public class LoginModelImpl implements LoginModel {
    protected Engine mEngine = AppContext.getInstance().getEngine();

    @Override
    public void userLogin(Map<String, String> params, OnDataListener onDataListener, int code) {
        mEngine.loadTestData(params).enqueue(new DataCallback<LoginEntity>(LoginEntity.class.getName(), onDataListener, code));
    }
}
