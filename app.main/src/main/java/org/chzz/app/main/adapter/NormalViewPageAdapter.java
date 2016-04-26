package org.chzz.app.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.chzz.app.main.ui.fragment.HotFragment;
import org.chzz.app.main.ui.fragment.IndexFragment;
import org.chzz.app.main.ui.fragment.UserFragment;

/**
 * ============================================================
 * 版权 ：深圳市医友智能技术有限公司 版权所有 (c)   2016/4/21
 * 作者:copy   xiaoxinxing12@qq.com
 * 版本 ：1.0
 * 创建日期 ： 2016/4/21--14:06
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/
public class NormalViewPageAdapter extends FragmentPagerAdapter {
    private String[] mTitles;

    public NormalViewPageAdapter(FragmentManager fm, String[] title) {

        super(fm);
        this.mTitles = title;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new IndexFragment();
                break;
            case 1:
                fragment = new HotFragment();
                break;
            case 3:
                fragment = new UserFragment();
                break;
            default:
                fragment = new IndexFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
