package org.chzz.app.main.mvp.presenter.impl;

import org.chzz.app.main.mvp.model.impl.IndexModelImpl;
import org.chzz.app.main.mvp.presenter.IndexPresenter;
import org.chzz.app.main.mvp.presenter.IndexView;
import org.chzz.app.main.mvp.presenter.OnGetDataListener;

/**
 * ============================================================
 * 版权 ：深圳市医友智能技术有限公司 版权所有 (c)   2016/4/22
 * 作者:copy   xiaoxinxing12@qq.com
 * 版本 ：1.0
 * 创建日期 ： 2016/4/22--17:43
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/
public class IndexPresenterImpl implements IndexPresenter, OnGetDataListener {

    private IndexView indexView;
    private IndexModelImpl indexModel;

    public IndexPresenterImpl(IndexView indexView) {
        this.indexView = indexView;
        this.indexModel = new IndexModelImpl();
    }

    @Override
    public void getData(int mNewPageNumber) {
        indexModel.getData(mNewPageNumber, this);
    }

    @Override
    public void onSuccess(String result) {
        indexView.getData(result);
    }

    @Override
    public void onDestroy() {
        indexView = null;
    }
}
