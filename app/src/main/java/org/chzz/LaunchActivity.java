package org.chzz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import net.wequick.small.Small;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
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
