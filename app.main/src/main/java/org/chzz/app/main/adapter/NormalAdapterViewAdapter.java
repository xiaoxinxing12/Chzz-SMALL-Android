package org.chzz.app.main.adapter;

import android.content.Context;

import org.chzz.adapter.CHZZAdapterViewAdapter;
import org.chzz.adapter.CHZZViewHolderHelper;
import org.chzz.app.main.engine.FillDataListener;
import org.chzz.app.main.model.bean.BaseModel;


/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/5/21 上午12:39
 * 描述:
 */
public class NormalAdapterViewAdapter<T extends BaseModel> extends CHZZAdapterViewAdapter<T> {
    private Context context;
    private FillDataListener iFillDataListener;

    public NormalAdapterViewAdapter(Context context, int itemLayoutId, FillDataListener fillDataListener) {
        super(context, itemLayoutId);
        this.context = context;
        this.iFillDataListener = fillDataListener;
    }

    @Override
    protected void setItemChildListener(CHZZViewHolderHelper viewHolderHelper) {

    }

    @Override
    public void fillData(CHZZViewHolderHelper viewHolderHelper, int position, T t) {
        iFillDataListener.setFillDataListener(viewHolderHelper, position, t);
    }
}