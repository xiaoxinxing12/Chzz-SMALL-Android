package org.chzz.app.main.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.chzz.app.main.AppContext;
import org.chzz.app.main.engine.Engine;
import org.chzz.app.main.ui.activity.BaseActivity;
import org.chzz.app.main.utlis.ToastUtil;
import org.chzz.refresh.CHZZRefreshLayout;


/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/7/3 上午1:28
 * 描述:
 */
public abstract class BaseFragment extends Fragment {
    protected String TAG;
    protected AppContext mApp;
    protected View mContentView;
    protected BaseActivity mActivity;
    protected Engine mEngine;
    protected final int LOADING_DURATION = 2000;
    //布局
    protected CHZZRefreshLayout mRefreshLayout;
    protected boolean mIsNetworkEnabled = true;
    protected int mNewPageNumber = 0;
    protected int mMorePageNumber = 1;
    //0 默认 1 刷新  2下拉
    protected int mActionCode = 0;


    public void onAttach(Activity activity) {
        super.onAttach(activity);
        TAG = this.getClass().getSimpleName();
        mApp = AppContext.getInstance();
        mActivity = (BaseActivity) activity;
        mEngine = mApp.getEngine();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            onUserVisible();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 避免多次从xml中加载布局文件
        if (mContentView == null) {
            initView(savedInstanceState);
            setListener();
            processLogic(savedInstanceState);
        } else {
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (parent != null) {
                parent.removeView(mContentView);
            }
        }
        return mContentView;
    }

    protected void setContentView(@LayoutRes int layoutResID) {
        mContentView = LayoutInflater.from(mApp).inflate(layoutResID, null);
    }

    /**
     * 初始化View控件
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 给View控件添加事件监听器
     */
    protected abstract void setListener();

    /**
     * 处理业务逻辑，状态恢复等操作
     *
     * @param savedInstanceState
     */
    protected abstract void processLogic(Bundle savedInstanceState);

    /**
     * 当fragment对用户可见时，会调用该方法，可在该方法中懒加载网络数据
     */
    protected abstract void onUserVisible();

    /**
     * 查找View
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) mContentView.findViewById(id);
    }

    protected void showToast(String text) {
        ToastUtil.show(text);
    }

    protected void showLoadingDialog() {
        mActivity.showLoadingDialog();
    }

    protected void dismissLoadingDialog() {
        if (isVisible()) {
            mActivity.dismissLoadingDialog();
        }
    }
}