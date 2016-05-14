package org.chzz.app.main.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import org.chzz.adapter.CHZZViewHolderHelper;
import org.chzz.app.main.R;
import org.chzz.app.main.adapter.NormalRecyclerViewAdapter;
import org.chzz.app.main.engine.DataEngine;
import org.chzz.app.main.engine.FillDataListener;
import org.chzz.app.main.model.bean.BaseEntity;
import org.chzz.app.main.model.bean.RefreshModels;
import org.chzz.app.main.presenter.IndexPresenter;
import org.chzz.app.main.presenter.impl.IndexPresenterImpl;
import org.chzz.app.main.ui.listener.IndexView;
import org.chzz.app.main.widget.FullyGridLayoutManager;
import org.chzz.refresh.CHZZMeiTuanRefreshViewHolder;
import org.chzz.refresh.CHZZRefreshLayout;

/**
 * ============================================================
 * 版权 ：深圳市医友智能技术有限公司 版权所有 (c)   2016/4/21
 * 作者:copy   xiaoxinxing12@qq.com
 * 版本 ：1.0
 * 创建日期 ： 2016/4/21--14:14
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/
public class IndexFragment extends BaseFragment implements CHZZRefreshLayout.BGARefreshLayoutDelegate, FillDataListener, IndexView {
    private CHZZRefreshLayout mRefreshLayout;
    private LinearLayout mHeaderView;
    private RecyclerView mRvHot;
    private RecyclerView mGvHot;
    private NormalRecyclerViewAdapter mAdapterHot;
    private IndexPresenter mIndexPresenter;
    private IndexView mIndexView;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_index);
        mRefreshLayout = getViewById(R.id.refreshLayout);
        mRvHot = getViewById(R.id.rv_hot_data);
        mGvHot=getViewById(R.id.gv_data);
       mHeaderView = getViewById(R.id.ll_headerView);
        mIndexPresenter = new IndexPresenterImpl(this);
    }

    @Override
    protected void setListener() {
        mRefreshLayout.setDelegate(this);
        mAdapterHot = new NormalRecyclerViewAdapter(mRvHot, R.layout.item_fragment_index, this);

        mRvHot.setAdapter(mAdapterHot);
        mGvHot.setAdapter(mAdapterHot);
    }


    @Override
    protected void processLogic(Bundle savedInstanceState) {
        CHZZMeiTuanRefreshViewHolder meiTuanRefreshViewHolder = new CHZZMeiTuanRefreshViewHolder(mApp, false);
        meiTuanRefreshViewHolder.setPullDownImageResource(R.mipmap.bga_refresh_mt_pull_down);
        meiTuanRefreshViewHolder.setChangeToReleaseRefreshAnimResId(R.anim.bga_refresh_mt_change_to_release_refresh);
        meiTuanRefreshViewHolder.setRefreshingAnimResId(R.anim.bga_refresh_mt_refreshing);
        mRefreshLayout.setRefreshViewHolder(meiTuanRefreshViewHolder);
        mHeaderView.addView(DataEngine.getCustomHeaderView(mApp));
        mRvHot.setLayoutManager(new GridLayoutManager(mApp, 2, FullyGridLayoutManager.VERTICAL, false));
        mGvHot.setLayoutManager(new GridLayoutManager(mApp, 2, FullyGridLayoutManager.VERTICAL, false));
        mIndexPresenter.getHotData(1);

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(CHZZRefreshLayout refreshLayout) {
        mNewPageNumber++;
        if (mNewPageNumber > 0) {
            mRefreshLayout.endRefreshing();
            showToast("没有最新数据了");
            return;
        }
        //mIndexPresenter.getHotData(mNewPageNumber);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(CHZZRefreshLayout refreshLayout) {
        mMorePageNumber++;
        return true;
    }

    @Override
    public void setFillDataListener(CHZZViewHolderHelper chzzViewHolderHelper, int i, BaseEntity baseModel) {
        RefreshModels.DataBean dataBean = (RefreshModels.DataBean) baseModel;

    }

    @Override
    public void hotDataResult(BaseEntity model) {
        RefreshModels models = (RefreshModels) model;
        mAdapterHot.setDatas(models.getData().subList(0,4));
    }

}
