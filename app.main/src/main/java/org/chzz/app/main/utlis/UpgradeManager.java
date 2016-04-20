package org.chzz.app.main.utlis;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import net.wequick.small.Small;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * ============================================================
 * 版权 ：深圳市医友智能技术有限公司 版权所有 (c)   2016/3/18
 * 作者:copy
 * 版本 ：1.0
 * 创建日期 ： 2016/3/18--14:55
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/
public class UpgradeManager {


    private static class UpgradeInfo {
        public String packageName;
        public String downloadUrl;
        public int mVersions;
    }

    private interface OnResponseListener {
        void onResponse(Object object);
    }

    private interface OnUpgradeListener {
        void onUpgrade();
    }

    private Context mContext;
    private ProgressDialog mProgressDlg;

    public UpgradeManager(Context context) {
        mContext = context;
    }

    public void checkUpgrade(int versions, String packageName, String downloadUrl) {
        if (versions < 0) {
            mProgressDlg= ProgressDialog.show(mContext, "版本检查", "无最新版", true, true );
            return;
        }
        mProgressDlg = ProgressDialog.show(mContext, "Small", "Checking for updates...");
        requestUpgradeInfo(Small.getBundleVersions(), new OnResponseListener() {
            @Override
            public void onResponse(Object object) {
                UpgradeInfo info = (UpgradeInfo) object;
                final net.wequick.small.Bundle bundle =
                        net.wequick.small.Bundle.findByName(info.packageName);
                mProgressDlg.setMessage("Upgrading...");
                upgradeBundle(bundle, info.downloadUrl, bundle.getPatchFile(),
                        new OnUpgradeListener() {
                            @Override
                            public void onUpgrade() {
                                mProgressDlg.dismiss();
                                mProgressDlg = null;
                                Toast.makeText(mContext,
                                        "Upgrade Success! Restart and Click `Call lib.utils` to see changes.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    /**
     * @param versions
     * @param listener
     */
    private void requestUpgradeInfo(Map versions, OnResponseListener listener) {
        // Just for example, you can replace this with a real HTTP request.
        System.out.println(versions);
        UpgradeInfo info = new UpgradeInfo();
        info.packageName = "org.chzz.app.main";
        info.downloadUrl = "http://192.168.1.54:8080/" +
                "liborg_chzz_app_main.so";
        listener.onResponse(info);
    }

    private static class DownloadHandler extends Handler {
        private OnUpgradeListener mListener;

        public DownloadHandler(OnUpgradeListener listener) {
            mListener = listener;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mListener.onUpgrade();
                    break;
            }
        }
    }

    private DownloadHandler mHandler;

    private void upgradeBundle(final net.wequick.small.Bundle bundle,
                               final String urlStr, final File file,
                               final OnUpgradeListener listener) {
        // Just for example, you can do this by OkHttp or something.
        mHandler = new DownloadHandler(listener);
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlStr);
                    HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                    InputStream is = urlConn.getInputStream();
                    // Save
                    OutputStream os = new FileOutputStream(file);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = is.read(buffer)) != -1) {
                        os.write(buffer, 0, length);
                    }
                    os.flush();
                    os.close();
                    is.close();

                    // While you finish downloading patch file, call this
                    bundle.upgrade();

                    Message.obtain(mHandler, 1).sendToTarget();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
