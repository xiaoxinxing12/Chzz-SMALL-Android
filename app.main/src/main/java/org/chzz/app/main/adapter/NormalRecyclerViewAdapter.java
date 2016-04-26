package org.chzz.app.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import org.chzz.adapter.CHZZRecyclerViewAdapter;
import org.chzz.adapter.CHZZViewHolderHelper;
import org.chzz.app.main.engine.FillDataListener;
import org.chzz.app.main.model.bean.BaseEntity;

/**
 * ============================================================
 * 版权 ：深圳市医友智能技术有限公司 版权所有 (c)   2016/3/21
 * 作者:copy
 * 版本 ：1.0
 * 创建日期 ： 2016/3/21--16:45
 * 描述 ：通用的RecyclerViewAdapter
 * 修订历史 ：
 * ============================================================
 **/
public class NormalRecyclerViewAdapter<T extends BaseEntity> extends CHZZRecyclerViewAdapter<T> {
    private Context context;
    private FillDataListener iFillDataListener;

    public NormalRecyclerViewAdapter(RecyclerView recyclerView, int itemLayoutId, FillDataListener fillDataListener) {
        super(recyclerView, itemLayoutId);
        this.iFillDataListener = fillDataListener;
    }

    @Override
    protected void setItemChildListener(CHZZViewHolderHelper viewHolderHelper) {
        super.setItemChildListener(viewHolderHelper);
    }

    @Override
    protected void fillData(CHZZViewHolderHelper chzzViewHolderHelper, int i, T t) {
        iFillDataListener.setFillDataListener(chzzViewHolderHelper, i, t);
    }

}
