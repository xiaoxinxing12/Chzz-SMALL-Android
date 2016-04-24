package org.chzz.app.main.mvp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import org.chzz.app.main.R;
import org.chzz.app.main.adapter.SwipeAdapterViewAdapter;
import org.chzz.app.main.engine.DataEngine;
import org.chzz.app.main.model.bean.RefreshModels;
import org.chzz.app.main.mvp.presenter.IndexPresenter;
import org.chzz.app.main.mvp.presenter.IndexView;
import org.chzz.app.main.mvp.presenter.impl.IndexPresenterImpl;
import org.chzz.app.main.ui.activity.BaseActivity;
import org.chzz.refresh.CHZZMeiTuanRefreshViewHolder;
import org.chzz.refresh.CHZZRefreshLayout;

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
public class MainActivity extends BaseActivity implements CHZZRefreshLayout.BGARefreshLayoutDelegate, IndexView {
    private CHZZRefreshLayout mRefreshLayout;
    private ListView mDataLv;
    private SwipeAdapterViewAdapter mAdapter;
    private IndexPresenter mIndexPresenter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_index);

    }

    @Override
    protected void setListener() {
        mRefreshLayout.setDelegate(this);
        mDataLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL == scrollState) {
                    mAdapter.closeOpenedSwipeItemLayoutWithAnim();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });

        mAdapter = new SwipeAdapterViewAdapter(mApp);

    }

    @Override
    protected void onUserVisible() {
        mIndexPresenter = new IndexPresenterImpl(this);
        mIndexPresenter.getData(1, 0);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        CHZZMeiTuanRefreshViewHolder meiTuanRefreshViewHolder = new CHZZMeiTuanRefreshViewHolder(mApp, true);
        meiTuanRefreshViewHolder.setPullDownImageResource(R.mipmap.bga_refresh_mt_pull_down);
        meiTuanRefreshViewHolder.setChangeToReleaseRefreshAnimResId(R.anim.bga_refresh_mt_change_to_release_refresh);
        meiTuanRefreshViewHolder.setRefreshingAnimResId(R.anim.bga_refresh_mt_refreshing);

        mRefreshLayout.setRefreshViewHolder(meiTuanRefreshViewHolder);
        mRefreshLayout.setCustomHeaderView(DataEngine.getCustomHeaderView(mApp), true);

        mDataLv.setAdapter(mAdapter);
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(CHZZRefreshLayout refreshLayout) {
        mNewPageNumber++;
        if (mNewPageNumber > 3) {
            showToast("没有最新数据了");
            mRefreshLayout.endRefreshing();
            return;
        }
        mIndexPresenter.getData(mNewPageNumber, 1);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(CHZZRefreshLayout refreshLayout) {
        mMorePageNumber++;
        if (mMorePageNumber > 3) {
            showToast("没有最新数据了");
            mRefreshLayout.endLoadingMore();
            return false;
        }
        mIndexPresenter.getData(mMorePageNumber, 2);
        return true;
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void getData(RefreshModels list, int code) {
        switch (code) {
            case 0:
                mAdapter.setDatas(list.getData());
                break;
            case 1:
                mAdapter.addNewDatas(list.getData());
                mRefreshLayout.endRefreshing();
                break;
            case 2:
                mAdapter.addMoreDatas(list.getData());
                mRefreshLayout.endLoadingMore();
                break;
        }
        mAdapter.notifyDataSetChanged();
    }
}
