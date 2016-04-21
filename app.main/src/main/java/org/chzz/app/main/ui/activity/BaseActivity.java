package org.chzz.app.main.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import org.chzz.adapter.CHZZViewHolderHelper;
import org.chzz.app.main.AppContext;
import org.chzz.app.main.R;
import org.chzz.app.main.engine.FillDataListener;
import org.chzz.app.main.model.BaseModel;
import org.chzz.app.main.utlis.SystemBarTintManager;
import org.chzz.refresh.CHZZRefreshLayout;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * ============================================================
 * 版权 ：深圳市医友智能技术有限公司 版权所有 (c)   2016/3/18
 * 作者:copy
 * 版本 ：1.0
 * 创建日期 ： 2016/3/18--19:32
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected boolean mIsNetworkEnabled = true;
    protected int mNewPageNumber = 0;
    protected int mMorePageNumber = 1;
    //再按一次退出程序
    protected long exitTime;
    private SweetAlertDialog mLoadingDialog;
    protected AppContext mApp;
    protected String TAG;
    //标题栏
    protected Toolbar mToolbar;
    //临听
    protected FillDataListener mFillDataListener;
    //是否开始一体化
    protected boolean setTranslucentStatus;
    protected SystemBarTintManager mTintManager;
    //一体化顶部
    private LinearLayout mToolbarTop;
    //数据列表
    protected RecyclerView mDataRv;
    //布局
    protected CHZZRefreshLayout mRefreshLayout;
    protected Toolbar.OnMenuItemClickListener onMenuItemClick ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApp = AppContext.getInstance();
        mToolbarTop = getViewById(R.id.toolbar_top);

        initView(savedInstanceState);
        setListener();
        onUserVisible();
        processLogic(savedInstanceState);

        initTranslucentStatus();
    }

    /**
     * 查找View
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) findViewById(id);
    }

    /**
     * 初始化view
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 给View控件添加事件监听器
     */
    protected abstract void setListener();

    /**
     * 当fragment对用户可见时，会调用该方法，可在该方法中懒加载网络数据
     */
    protected abstract void onUserVisible();

    /**
     * 处理业务逻辑，状态恢复等操作
     *
     * @param savedInstanceState
     */
    protected abstract void processLogic(Bundle savedInstanceState);

    /**
     * 加载数据时show
     */
    public void showLoadingDialog() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
            mLoadingDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.colorPrimary));
            mLoadingDialog.setCancelable(false);
            mLoadingDialog.setTitleText("数据加载中...");
        }
        mLoadingDialog.show();
    }

    /**
     * 加载完数据dismiss
     */
    public void dismissLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    /**
     * 给item设置数据
     *
     * @param chzzViewHolderHelper
     * @param i                    第几项
     * @param
     */

    public void setFillDataListener(CHZZViewHolderHelper chzzViewHolderHelper, int i, BaseModel t) {

    }

    private void initTranslucentStatus() {
        //判断手机版本是否大于19
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION_CODES.M > Build.VERSION.SDK_INT) {
            setTranslucentStatus(true);
        }
        // 创建状态栏的管理实例
        mTintManager = new SystemBarTintManager(this);
        // 激活状态栏设置
        mTintManager.setStatusBarTintEnabled(true);
        // 激活导航栏设置，设置导航背景色
        mTintManager.setStatusBarTintResource(R.color.bg_transparent);
        if (!setTranslucentStatus)
            mToolbarTop.setVisibility(View.GONE);
        //版本小于19隐藏一体化
    }

    /**
     * 公有的设置顶部状态栏一体化
     *
     * @param on 是否开启
     */
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            setTranslucentStatus = true;
            winParams.flags |= bits;
        } else {
            setTranslucentStatus = false;
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }



}
