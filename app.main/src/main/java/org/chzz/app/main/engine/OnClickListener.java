package org.chzz.app.main.engine;

import android.view.View;

/**
 * ============================================================
 * 版权 ：深圳市医友智能技术有限公司 版权所有 (c)   2016/4/22
 * 作者:copy   xiaoxinxing12@qq.com
 * 版本 ：1.0
 * 创建日期 ： 2016/4/22--10:04
 * 描述 ：用户非常快的双击按钮 按钮事件相当于二次
 * 怎么控制一个Button俩次点击的时间大于0.5s
 * 修订历史 ：
 * ============================================================
 **/
public abstract class OnClickListener implements View.OnClickListener {
    public static long lastTime;

    public abstract void singleClick(View v);

    @Override
    public void onClick(View v) {
        if (onDoubClick()) {
            return;
        }
        singleClick(v);
    }

    public boolean onDoubClick() {
        boolean flag = false;
        long time = System.currentTimeMillis() - lastTime;

        if (time < 500) {
            flag = true;
        }
        lastTime = System.currentTimeMillis();
        return flag;
    }
}