package org.chzz.app.main.model.impl;

import org.chzz.app.main.AppContext;
import org.chzz.app.main.engine.DataCallback;
import org.chzz.app.main.engine.Engine;
import org.chzz.app.main.model.IndexModel;
import org.chzz.app.main.model.bean.RefreshModels;
import org.chzz.app.main.mvp.presenter.BaseListener;
import org.chzz.app.main.presenter.listener.OnDataListener;

/**
 * Created by copy on 2016/4/24.
 */
public class IndexModelImpl implements IndexModel {
    protected Engine mEngine = AppContext.getInstance().getEngine();

    @Override
    public void getHotData(int page, OnDataListener onDataListener, int code) {
        mEngine.loadNewData(page).enqueue(new DataCallback<RefreshModels>(onDataListener, code));
    }
}
