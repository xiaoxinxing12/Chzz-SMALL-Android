package org.chzz.app.main.mvp.presenter;

import org.chzz.app.main.model.bean.RefreshModels;

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
public interface OnGetDataListener extends BaseListener {
    void onSuccess(RefreshModels bean,int code);

}
