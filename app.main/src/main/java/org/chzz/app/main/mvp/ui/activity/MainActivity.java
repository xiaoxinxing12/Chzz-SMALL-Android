package org.chzz.app.main.mvp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.chzz.app.main.R;
import org.chzz.app.main.mvp.presenter.IndexPresenter;
import org.chzz.app.main.mvp.presenter.IndexView;
import org.chzz.app.main.mvp.presenter.impl.IndexPresenterImpl;
import org.chzz.app.main.utlis.ToastUtil;

/**
 * ============================================================
 * 版权 ：深圳市医友智能技术有限公司 版权所有 (c)   2016/4/22
 * 作者:copy   xiaoxinxing12@qq.com
 * 版本 ：1.0
 * 创建日期 ： 2016/4/22--17:21
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/
public class MainActivity extends BaseActivity implements IndexView {
    private Button mGetData;
    private IndexPresenter indexPresenter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.sample_activity_main);
        mGetData = getViewById(R.id.but);
    }

    @Override
    protected void setListener() {
        mGetData.setOnClickListener(this);
        indexPresenter = new IndexPresenterImpl(this);
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
                indexPresenter.getData(1);
                break;
        }
    }


    @Override
    public void getData(String result) {
        ToastUtil.show(result);
    }
}
