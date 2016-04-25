package org.chzz.app.main.engine;



import org.chzz.app.main.model.bean.BannerModel;
import org.chzz.app.main.model.bean.LoginEntity;
import org.chzz.app.main.model.bean.RefreshModel;
import org.chzz.app.main.model.bean.RefreshModels;
import org.chzz.app.main.model.bean.StaggeredModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/9/17 下午2:01
 * 描述:
 */
public interface Engine {

    @FormUrlEncoded
    @POST("srts/system/user/login")
    Call<LoginEntity> loadTestData(@FieldMap Map<String, String> params);
    @GET("refreshlayout/api/defaultdata6.json")
    Call<List<RefreshModel>> loadInitDatas();

    @GET("refreshlayout/newdata{pageNumber}.txt")
    Call<RefreshModels> loadNewData(@Path("pageNumber") int pageNumber);

    @GET("refreshlayout/api/moredata{pageNumber}.json")
    Call<List<RefreshModel>> loadMoreData(@Path("pageNumber") int pageNumber);

    @GET("refreshlayout/api/staggered_default.json")
    Call<List<StaggeredModel>> loadDefaultStaggeredData();

    @GET("refreshlayout/api/staggered_new{pageNumber}.json")
    Call<List<StaggeredModel>> loadNewStaggeredData(@Path("pageNumber") int pageNumber);

    @GET("refreshlayout/api/staggered_more{pageNumber}.json")
    Call<List<StaggeredModel>> loadMoreStaggeredData(@Path("pageNumber") int pageNumber);

    @GET("banner/api/5item.json")
    Call<BannerModel> getBannerModel();
}