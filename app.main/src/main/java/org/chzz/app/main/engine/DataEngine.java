package org.chzz.app.main.engine;

import android.content.Context;
import android.view.View;

import org.chzz.app.main.AppContext;
import org.chzz.app.main.R;
import org.chzz.app.main.model.bean.BannerModel;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/5/26 上午1:03
 * 描述:
 */
public class DataEngine {

    public static View getCustomHeaderView(final Context context) {
        View headerView = View.inflate(context, R.layout.view_custom_header, null);
        final BGABanner banner = (BGABanner) headerView.findViewById(R.id.banner);
        final List<View> views = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            views.add(View.inflate(context, R.layout.view_image, null));
        }
        banner.setViews(views);
        AppContext.getInstance().getEngine().getBannerModel().enqueue(new Callback<BannerModel>() {

            @Override
            public void onResponse(Call<BannerModel> call, Response<BannerModel> response) {
                BannerModel bannerModel = response.body();
                for (int i = 0; i < views.size(); i++) {
                    //Glide.with(context).load(bannerModel.imgs.get(i)).placeholder(R.mipmap.holder).error(R.mipmap.holder).dontAnimate().thumbnail(0.1f).into((ImageView) views.get(i));
                }
               // banner.setTips(bannerModel.tips);
            }

            @Override
            public void onFailure(Call<BannerModel> call, Throwable t) {

            }
        });
        return headerView;
    }

}