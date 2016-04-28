package org.chzz;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ProgressBar;

import net.wequick.small.Small;

public class LaunchActivity extends Activity {
    private ProgressBar mProgressBar;
    private int time;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time++;

            mProgressBar.setProgress(time);
            if (time < 100)
                mHandler.sendEmptyMessageDelayed(0, 20);
            else
                start();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_progressbar);
        mHandler.sendEmptyMessage(0);
    }

    private void start() {
        Small.setBaseUri("http://chzz.org/");
        Small.preSetUp(getApplication());
        Small.setUp(this, new net.wequick.small.Small.OnCompleteListener() {

            @Override
            public void onComplete() {
                Small.openUri("main", LaunchActivity.this);
                Log.i("main", "onStart()");
                finish();
            }
        });
    }
}
