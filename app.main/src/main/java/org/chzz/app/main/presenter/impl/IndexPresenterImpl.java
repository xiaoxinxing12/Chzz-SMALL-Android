package org.chzz.app.main.presenter.impl;

import org.chzz.app.main.model.bean.BaseEntity;
import org.chzz.app.main.model.impl.IndexModelImpl;
import org.chzz.app.main.presenter.IndexPresenter;
import org.chzz.app.main.presenter.listener.OnDataListener;
import org.chzz.app.main.ui.listener.IndexView;

/**
 * Created by copy on 2016/4/24.
 */
public class IndexPresenterImpl implements IndexPresenter, OnDataListener {

    private IndexView mIndexView;
    private IndexModelImpl mIndexModel;

    public IndexPresenterImpl(IndexView indexView) {
        this.mIndexView = indexView;
        mIndexModel = new IndexModelImpl();
    }

    @Override
    public void getHotData(int page) {
        mIndexModel.getHotData(page, this, 0);
    }


    @Override
    public void result(BaseEntity model, int code) {
        switch (code) {
            case 0:
                mIndexView.hotDataResult(model);
                break;
        }
    }
}
