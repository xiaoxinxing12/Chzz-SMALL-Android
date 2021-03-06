package org.chzz.app.main.adapter;

import android.content.Context;


import org.chzz.adapter.CHZZAdapterViewAdapter;
import org.chzz.adapter.CHZZViewHolderHelper;
import org.chzz.app.main.R;
import org.chzz.app.main.model.bean.BaseEntity;
import org.chzz.app.main.model.bean.RefreshModels;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.swipeitemlayout.BGASwipeItemLayout;


/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/5/21 上午12:39
 * 描述:
 */
public class SwipeAdapterViewAdapter<T extends BaseEntity> extends CHZZAdapterViewAdapter<T> {
    /**
     * 当前处于打开状态的item
     */
    private List<BGASwipeItemLayout> mOpenedSil = new ArrayList<>();

    public SwipeAdapterViewAdapter(Context context) {
        super(context, R.layout.item_swipe);
    }

    @Override
    protected void setItemChildListener(CHZZViewHolderHelper viewHolderHelper) {
        BGASwipeItemLayout swipeItemLayout = viewHolderHelper.getView(R.id.sil_item_swipe_root);
        swipeItemLayout.setDelegate(new BGASwipeItemLayout.BGASwipeItemLayoutDelegate() {
            @Override
            public void onBGASwipeItemLayoutOpened(BGASwipeItemLayout swipeItemLayout) {
                closeOpenedSwipeItemLayoutWithAnim();
                mOpenedSil.add(swipeItemLayout);
            }

            @Override
            public void onBGASwipeItemLayoutClosed(BGASwipeItemLayout swipeItemLayout) {
                mOpenedSil.remove(swipeItemLayout);
            }

            @Override
            public void onBGASwipeItemLayoutStartOpen(BGASwipeItemLayout swipeItemLayout) {
                closeOpenedSwipeItemLayoutWithAnim();
            }
        });
        viewHolderHelper.setItemChildClickListener(R.id.tv_item_swipe_delete);
        viewHolderHelper.setItemChildLongClickListener(R.id.tv_item_swipe_delete);
    }

    @Override
    protected void fillData(CHZZViewHolderHelper viewHolderHelper, int position, T models) {
        RefreshModels.DataBean model = (RefreshModels.DataBean) models;
        viewHolderHelper.setText(R.id.tv_item_swipe_title, model.getTitle()).setText(R.id.tv_item_swipe_detail, model.getDetail()).setText(R.id.et_item_swipe_title, model.getTitle());

    }


    public void closeOpenedSwipeItemLayoutWithAnim() {
        for (BGASwipeItemLayout sil : mOpenedSil) {
            sil.closeWithAnim();
        }
        mOpenedSil.clear();
    }

    public void closeOpenedSwipeItemLayout() {
        for (BGASwipeItemLayout sil : mOpenedSil) {
            sil.close();
        }
        mOpenedSil.clear();
    }

}