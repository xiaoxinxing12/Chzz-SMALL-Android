package org.chzz.app.main.ui.fragment;

import android.os.Bundle;

import com.bumptech.glide.Glide;

import org.chzz.app.main.R;

import cn.bingoogolapple.imageview.BGAImageView;

/**
 * Created by copy on 2016/4/26.
 */
public class UserFragment extends BaseFragment {
    private BGAImageView mHead;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_user);
        int maxSize = getResources().getDimensionPixelSize(R.dimen.max_size);
        mHead = getViewById(R.id.iv_user_head);
        Glide.with(this).load("http://7xk9dj.com1.z0.glb.clouddn.com/refreshlayout/images/staggered6.png")
                .asBitmap()
                .placeholder(R.mipmap.holder)
                .error(R.mipmap.holder)
                .override(maxSize, maxSize).into(mHead);

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void onUserVisible() {

    }
}
