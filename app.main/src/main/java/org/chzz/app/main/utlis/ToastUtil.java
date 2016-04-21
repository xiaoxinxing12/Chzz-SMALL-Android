package org.chzz.app.main.utlis;

import android.widget.Toast;

import org.chzz.app.main.AppContext;


/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/7/2 10:17
 * 描述:
 */
public class ToastUtil {

    private ToastUtil() {
    }

    public static void show(CharSequence text) {
        if (text.length() < 10) {
            Toast.makeText(AppContext.getInstance(), text, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(AppContext.getInstance(), text, Toast.LENGTH_LONG).show();
        }
    }

    public static void show( int resId) {
        show(AppContext.getInstance().getString(resId));
    }

}