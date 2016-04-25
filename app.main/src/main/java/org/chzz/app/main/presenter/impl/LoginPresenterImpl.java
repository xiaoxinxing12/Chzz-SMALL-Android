package org.chzz.app.main.presenter.impl;

import android.util.Log;

import org.chzz.app.main.model.LoginModel;
import org.chzz.app.main.model.bean.BaseModel;
import org.chzz.app.main.model.bean.LoginEntity;
import org.chzz.app.main.model.impl.LoginModelImpl;
import org.chzz.app.main.presenter.LoginPresenter;
import org.chzz.app.main.presenter.listener.OnDataListener;
import org.chzz.app.main.ui.listener.LoginView;
import org.chzz.app.main.utlis.GsonTools;

import java.util.Map;

/**
 * Created by copy on 2016/4/25.
 */
public class LoginPresenterImpl implements LoginPresenter, OnDataListener {
    private LoginView mLoginView;
    private LoginModel mLoginModel;

    public LoginPresenterImpl(LoginView loginView) {
        this.mLoginView = loginView;
        mLoginModel = new LoginModelImpl();
    }

    @Override
    public void userLogin(Map<String, String> params) {
        mLoginModel.userLogin(params, this, 0);
    }

    @Override
    public void result(BaseModel model, int code) {
        switch (code) {
            case 0:
                LoginEntity bean = (LoginEntity) model;
                mLoginView.userLogin(bean);
                break;
        }
    }
}
