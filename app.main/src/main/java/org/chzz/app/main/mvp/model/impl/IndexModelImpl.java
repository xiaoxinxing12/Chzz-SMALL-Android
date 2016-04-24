package org.chzz.app.main.mvp.model.impl;

import org.chzz.app.main.AppContext;
import org.chzz.app.main.engine.DataCallback;
import org.chzz.app.main.engine.Engine;
import org.chzz.app.main.model.bean.RefreshModels;
import org.chzz.app.main.mvp.model.IndexModel;
import org.chzz.app.main.mvp.presenter.BaseListener;
import org.chzz.app.main.mvp.presenter.OnGetDataListener;

/**
 * ============================================================
 * 版权 ：深圳市医友智能技术有限公司 版权所有 (c)   2016/4/22
 * 作者:copy   xiaoxinxing12@qq.com
 * 版本 ：1.0
 * 创建日期 ： 2016/4/22--17:46
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/
public class IndexModelImpl implements IndexModel {
    protected Engine mEngine = AppContext.getInstance().getEngine();
    protected final int LOADING_DURATION = 2000;

    @Override
    public void getData(int mNewPageNumber, final OnGetDataListener onGetDataListener,  int code) {
       // mEngine.loadNewData(mNewPageNumber).enqueue(new DataCallback<RefreshModels>((BaseListener) onGetDataListener,code));
    }


}
