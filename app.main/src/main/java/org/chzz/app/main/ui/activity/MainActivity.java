package org.chzz.app.main.ui.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.chzz.app.main.AppContext;
import org.chzz.app.main.R;
import org.chzz.app.main.adapter.NormalViewPageAdapter;
import org.chzz.app.main.model.TabEntity;
import org.chzz.tablayout.CommonTabLayout;
import org.chzz.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

public class MainActivity extends BaseViewPageActivity {
    //标题
    private String[] mTitles = {"首页", "热门", "分类", "我的"};
    private int[] mLogo = {R.mipmap.logo_index, R.mipmap.logo_hot, R.mipmap.logo_category, R.mipmap.logo_my};
    //默认图标
    private int[] mIconUnselectIds = {R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect, R.mipmap.ic_tab_category_normal, R.mipmap.tab_contact_unselect};
    //选中图标
    private int[] mIconSelectIds = {R.mipmap.tab_home_select, R.mipmap.tab_speech_select, R.mipmap.ic_tab_category_selected, R.mipmap.tab_contact_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    //再按一次退出程序
    private long exitTime;
    //菜单
    private CommonTabLayout mTabLayout;
    //内容
    private ViewPager mViewPage;

    private NormalViewPageAdapter mNormalViewPageAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        mTabLayout = getViewById(R.id.tl_menu);
        mViewPage = getViewById(R.id.vp_content);
        mToolbar = getViewById(R.id.toolbar);
        mTitle = getViewById(R.id.tv_title);
        mToolbarTop = getViewById(R.id.toolbar_top);
        if (setTranslucentStatus)
            mToolbarTop.setVisibility(View.VISIBLE);
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mNormalViewPageAdapter = new NormalViewPageAdapter(getSupportFragmentManager(), mTitles);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void setListener() {
        mViewPage.setAdapter(mNormalViewPageAdapter);
        mTabLayout.setTabData(mTabEntities);
        mTabLayout.setOnTabSelectListener(this);
        mViewPage.addOnPageChangeListener(this);
        setSupportActionBar(mToolbar);
        setTitle("");
        setTitles(0);
        mToolbar.setOnMenuItemClickListener(onMenuItemClick);
        setMenu();
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
    public void onTabSelect(int position) {
        setTitles(position);
        mViewPage.setCurrentItem(position);
    }

    @Override
    public void onPageSelected(int position) {
        mTabLayout.setCurrentTab(position);
        setTitles(position);
    }

    /**
     * 设置标题
     *
     * @param position
     */
    private void setTitles(int position) {
        mToolbar.setLogo(null);
        mTitle.setBackgroundDrawable(getResources().getDrawable(mLogo[position]));
    }

    /**
     *
     */
    private void setMenu() {
        onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String msg = "";
                switch (item.getItemId()) {
                    case R.id.action_edit:
                        msg += "Click edit";
                        break;
                    case R.id.action_share:
                        msg += "Click share";
                        break;
                }

                if (!msg.equals("")) {
                    Toast.makeText(AppContext.getInstance(), msg, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        };
    }

    /**
     * 记录返回事件
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再次点击即可退出", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
                return true;
            } else if ((System.currentTimeMillis() - exitTime) <= 2000) {
                /**
                 *  1. 任务管理器方法
                 首先要说明该方法运行在Android 1.5 API Level为3以上才可以，同时需要权限
                 <uses-permission android:name=\"android.permission.RESTART_PACKAGES\"></uses-permission>
                 */
                // ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                //ActivityManager am= (ActivityManager) getApplication().getSystemService(Context.ACTIVITY_SERVICE);
                //am.restartPackage(getPackageName());

                /**
                 * 1. Dalvik VM的本地方法
                 * 获取PID
                 */
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
