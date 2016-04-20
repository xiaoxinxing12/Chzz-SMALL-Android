package org.chzz.app.main.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.chzz.app.main.R;
import org.chzz.app.main.utlis.UpgradeManager;

public class MainActivity extends AppCompatActivity {
    //再按一次退出程序
    private long exitTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button) findViewById(R.id.but);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpgradeManager u = new UpgradeManager(MainActivity.this);
                u.checkUpgrade(2, "", "");
            }
        });

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
