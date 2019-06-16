package com.hongliang.combination;


import com.hongliang.baselibrary.base.ResponseData;
import com.hongliang.baselibrary.bean.BaseBean;
import com.hongliang.baselibrary.httputil.AppConst;
import com.hongliang.combination.model.pojo.BannerBean;
import com.hongliang.combination.model.pojo.HotKeyBean;
import com.hongliang.combination.model.pojoVO.ArticleListVO;
import com.hongliang.combination.model.pojoVO.TypeTagVO;
import com.hongliang.retrofitutils.OkCall;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface Api {


    /**
     * 得追加在Url后面，后台能识别
     *
     * @param post
     * @return
     */
    @POST("user/register")
    Call<BaseBean<LoginBean>> register(@QueryMap Map<String, String> post);


    @POST("user/login")
    Call<BaseBean<LoginBean>> login(@QueryMap Map<String, String> post);


    @POST("user/login")
    Observable<BaseBean<LoginBean>> logins(@QueryMap Map<String, String> post);


    @POST("user/login")
    OkCall<BaseBean<LoginBean>> login2(@QueryMap Map<String, String> post);


    @GET("article/list/{page}/json")
    Call<ResponseData<ArticleListVO>> homeDate(@Path("page") int page);

    @GET(AppConst.BASE_URL + "banner/json")
    Call<ResponseData<List<BannerBean>>> getBanner();


    @GET(AppConst.BASE_URL + "tree/json")
    Call<ResponseData<List<TypeTagVO>>> getTypeTagData();


    @GET(AppConst.BASE_URL + "article/list/{page}/json")
    Call<ResponseData<ArticleListVO>> getTypeDataById(@Path("page") int page, @Query("cid") int cid);


    @GET(AppConst.BASE_URL + "hotkey/json")
    Call<ResponseData<List<HotKeyBean>>> getHotKey();


    @POST(AppConst.BASE_URL + "article/query/{page}/json")
    Call<ResponseData<ArticleListVO>> searchArticle(@Path("page") int page, @Query("k") String key);


}
