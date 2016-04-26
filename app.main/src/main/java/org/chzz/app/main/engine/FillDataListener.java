package org.chzz.app.main.engine;

import org.chzz.adapter.CHZZViewHolderHelper;
import org.chzz.app.main.model.bean.BaseEntity;

/**
 * ============================================================
 * 版权 ：深圳市医友智能技术有限公司 版权所有 (c)   2016/3/21
 * 作者:copy
 * 版本 ：1.0
 * 创建日期 ： 2016/3/21--17:36
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/
public interface FillDataListener {
    public void setFillDataListener(CHZZViewHolderHelper chzzViewHolderHelper, int i, BaseEntity baseModel);


}
