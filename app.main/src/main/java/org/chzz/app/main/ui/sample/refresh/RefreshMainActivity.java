package org.chzz.app.main.ui.sample.refresh;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import org.chzz.adapter.CHZZOnRVItemClickListener;
import org.chzz.adapter.CHZZViewHolderHelper;
import org.chzz.app.main.R;
import org.chzz.app.main.adapter.NormalRecyclerViewAdapter;
import org.chzz.app.main.engine.FillDataListener;
import org.chzz.app.main.model.bean.BaseModel;
import org.chzz.app.main.ui.activity.BaseActivity;
import org.chzz.app.main.utlis.ConstantValues;
import org.chzz.app.main.utlis.ThreadUtil;
import org.chzz.app.main.utlis.ToastUtils;
import org.chzz.refresh.CHZZRefreshLayout;
import org.chzz.refresh.CHZZStickinessRefreshViewHolder;

/**
 * ============================================================
 * 版权 ：深圳市医友智能技术有限公司 版权所有 (c)   2016/4/18
 * 作者:copy   xiaoxinxing12@qq.com
 * 版本 ：1.0
 * 创建日期 ： 2016/4/18--19:25
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/
public class RefreshMainActivity extends BaseActivity implements CHZZRefreshLayout.BGARefreshLayoutDelegate, FillDataListener, CHZZOnRVItemClickListener {
    protected NormalRecyclerViewAdapter mAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.sample_activity_refresh_main);
        mRefreshLayout = getViewById(R.id.rl_recyclerview_refresh);
        mDataRv = getViewById(R.id.rv_recyclerview_data);
    }

    @Override
    protected void setListener() {
        //设置下拉刷新上拉加载更多代理
        mRefreshLayout.setDelegate(this);
        mAdapter = new NormalRecyclerViewAdapter(mDataRv, R.layout.item_task_list, this);
        //自定头
       // View headerView = View.inflate(mApp, R.layout.item_appraisal_top, null);
        //mRefreshLayout.setCustomHeaderView(headerView, true);
        //列表数据
        CHZZStickinessRefreshViewHolder mainRefreshViewHolder = new CHZZStickinessRefreshViewHolder(mApp, true);
        //刷新头背景色
        mainRefreshViewHolder.setStickinessColor(R.color.colorPrimary);
        //刷新图标
        mainRefreshViewHolder.setRotateImage(R.mipmap.bga_refresh_stickiness);
        //设置刷新头
        mRefreshLayout.setRefreshViewHolder(mainRefreshViewHolder);
        //item分割线
        //mDataRv.addItemDecoration(new Divider(mApp));
        //布局管理器
        mDataRv.setLayoutManager(new GridLayoutManager(mApp, 1, GridLayoutManager.VERTICAL, false));
        mDataRv.setAdapter(mAdapter);
        mAdapter.setOnRVItemClickListener(this);

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
    public void onBGARefreshLayoutBeginRefreshing(CHZZRefreshLayout refreshLayout) {
        showLoadingDialog();
        if (mIsNetworkEnabled) {
            ThreadUtil.runInUIThread(new Runnable() {
                @Override
                public void run() {
                    mRefreshLayout.endRefreshing();
                    onUserVisible();
                }
            }, ConstantValues.LOADING_DURATION);
        }
        // 模拟网络可用不可用
        //mIsNetworkEnabled = !mIsNetworkEnabled;
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(CHZZRefreshLayout refreshLayout) {
        Log.i(TAG, "开始加载更多");
        //showLoadingDialog();
        if (mIsNetworkEnabled) {
            // 测试数据
            ThreadUtil.runInUIThread(new Runnable() {
                @Override
                public void run() {
                    mRefreshLayout.endLoadingMore();
                    ToastUtils.showInfo(RefreshMainActivity.this, "暂无更多数据");
                    // dismissLoadingDialog();
                }
            }, ConstantValues.LOADING_MORE);
            return true;
        } else {
            ToastUtils.showInfo(RefreshMainActivity.this, ConstantValues.FAILUREMSG);
            return false;
        }
    }

    @Override
    public void setFillDataListener(CHZZViewHolderHelper chzzViewHolderHelper, int i, BaseModel t) {
        super.setFillDataListener(chzzViewHolderHelper, i, t);
    }

    @Override
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {

    }
}
