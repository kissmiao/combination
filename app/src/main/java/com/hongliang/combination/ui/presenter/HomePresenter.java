package com.hongliang.combination.ui.presenter;


import com.hongliang.baselibrary.base.BasePresenter;
import com.hongliang.baselibrary.base.ResponseData;
import com.hongliang.baselibrary.httputil.callback.RequestCallBack;
import com.hongliang.combination.Api;
import com.hongliang.combination.model.pojo.BannerBean;
import com.hongliang.combination.model.pojoVO.ArticleListVO;
import com.hongliang.combination.ui.view.HomeView;
import com.hongliang.retrofitutils.RetrofitManage;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * user：lqm
 * desc：首页
 */

public class HomePresenter extends BasePresenter<HomeView> {

    private int mCurrentPage;

    //刷新
    public void getRefreshData() {
        mCurrentPage = 0;

        RetrofitManage.getInstents().onCreate(Api.class)
                .homeDate(mCurrentPage)
                .enqueue(new RequestCallBack<ResponseData<ArticleListVO>>() {
                    @Override
                    public void onBefore() {
                        super.onBefore();
                        getView().showRefreshView(true);
                    }

                    @Override
                    public void onSuccessful(Call<ResponseData<ArticleListVO>> call, Response<ResponseData<ArticleListVO>> response) {
                        getView().getRefreshDataSuccess(response.body().getData().getDatas());
                    }

                    @Override
                    protected void onFail(Call<ResponseData<ArticleListVO>> call, Throwable t, Response<ResponseData<ArticleListVO>> response) {
                        super.onFail(call, t, response);
                        getView().getDataError(t.getMessage());
                    }


                    @Override
                    protected void onAfter(Call<ResponseData<ArticleListVO>> call) {
                        getView().showRefreshView(false);
                    }
                });
    }

    //加载更多
    public void getMoreData() {
        mCurrentPage = mCurrentPage + 1;

        RetrofitManage.getInstents().onCreate(Api.class)
                .homeDate(mCurrentPage)
                .enqueue(new RequestCallBack<ResponseData<ArticleListVO>>() {
                    @Override
                    public void onSuccessful(Call<ResponseData<ArticleListVO>> call, Response<ResponseData<ArticleListVO>> response) {
                        getView().getMoreDataSuccess(response.body().getData().getDatas());
                    }

                    @Override
                    protected void onFail(Call<ResponseData<ArticleListVO>> call, Throwable t, Response<ResponseData<ArticleListVO>> response) {
                        getView().getDataError(t.getMessage());
                    }
                });
    }

    //获取轮播图数据
    public void getBannerData() {

        RetrofitManage.getInstents().onCreate(Api.class)
                .getBanner()
                .enqueue(new RequestCallBack<ResponseData<List<BannerBean>>>() {


                    @Override
                    public void onSuccessful(Call<ResponseData<List<BannerBean>>> call, Response<ResponseData<List<BannerBean>>> response) {


                        getView().getBannerDataSuccess(response.body().getData());
                    }

                    @Override
                    protected void onFail(Call<ResponseData<List<BannerBean>>> call, Throwable t, Response<ResponseData<List<BannerBean>>> response) {
                        super.onFail(call, t, response);
                        getView().getDataError(t.getMessage());
                    }
                });


    }

}