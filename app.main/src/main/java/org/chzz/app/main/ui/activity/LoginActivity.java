package org.chzz.app.main.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.chzz.app.main.model.bean.LoginEntity;
import org.chzz.app.main.presenter.impl.LoginPresenterImpl;
import org.chzz.app.main.ui.listener.LoginView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by copy on 2016/4/25.
 */
public class LoginActivity extends BaseActivity implements LoginView {
    private LoginPresenterImpl mLoginPresenter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        mLoginPresenter = new LoginPresenterImpl(this);
        Map<String, String> login = new HashMap<>();
        login.put("loginName", "hospital");
        login.put("password", "e10adc3949ba59abbe56e057f20f883e");
        mLoginPresenter.userLogin(login);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void userLogin(LoginEntity bean) {
        Log.i("login", bean.getDesc()+"");
    }
}
