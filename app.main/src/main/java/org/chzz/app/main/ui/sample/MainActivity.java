package org.chzz.app.main.ui.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.chzz.app.main.R;
import org.chzz.app.main.ui.activity.BaseActivity;
import org.chzz.app.main.ui.sample.refresh.RefreshMainActivity;
import org.chzz.app.main.utlis.UpgradeManager;

public class MainActivity extends BaseActivity {
    //再按一次退出程序
    private long exitTime;
    //chzz刷新
    private TextView mChzzRefresh;
    //更新
    private Button mUpdate;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        mUpdate = getViewById(R.id.but);
        mChzzRefresh = getViewById(R.id.tv_chzzRefresh);



    }

    @Override
    protected void setListener() {
        mUpdate.setOnClickListener(this);
        mChzzRefresh.setOnClickListener(this);
    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but:
                //更新
                UpgradeManager u = new UpgradeManager(MainActivity.this);
                u.checkUpgrade(1, "", "");
                break;
            case R.id.tv_chzzRefresh:
                //刷新
                Intent refresh = new Intent(this, RefreshMainActivity.class);
                startActivity(refresh);
                break;
        }
    }

    /**
     * 记录返回事件
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再次点击即可退出", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
                return true;
            } else if ((System.currentTimeMillis() - exitTime) <= 2000) {
                /**
                 *  1. 任务管理器方法
                 首先要说明该方法运行在Android 1.5 API Level为3以上才可以，同时需要权限
                 <uses-permission android:name=\"android.permission.RESTART_PACKAGES\"></uses-permission>
                 */
                // ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                //ActivityManager am= (ActivityManager) getApplication().getSystemService(Context.ACTIVITY_SERVICE);
                //am.restartPackage(getPackageName());

                /**
                 * 1. Dalvik VM的本地方法
                 * 获取PID
                 */
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }


}
