package com.hongliang.combination.ui.presenter;


import com.hongliang.baselibrary.base.BasePresenter;
import com.hongliang.baselibrary.base.ResponseData;
import com.hongliang.baselibrary.httputil.callback.RequestCallBack;
import com.hongliang.combination.Api;
import com.hongliang.combination.model.pojo.HotKeyBean;
import com.hongliang.combination.model.pojoVO.ArticleListVO;
import com.hongliang.combination.ui.view.SearchView;
import com.hongliang.retrofitutils.RetrofitManage;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * user：lqm
 * desc：搜索
 */

public class SearchPresenter extends BasePresenter<SearchView> {


    private int mCurrentPage;

    //热门搜索
    public void getHotKeyData() {
        RetrofitManage.getInstents().onCreate(Api.class)
                .getHotKey()
                .enqueue(new RequestCallBack<ResponseData<List<HotKeyBean>>>() {
                    @Override
                    public void onSuccessful(Call<ResponseData<List<HotKeyBean>>> call, Response<ResponseData<List<HotKeyBean>>> response) {
                        getView().getHotKeySuccess(response.body().getData());
                    }

                    @Override
                    protected void onFail(Call<ResponseData<List<HotKeyBean>>> call, Throwable t, Response<ResponseData<List<HotKeyBean>>> response) {

                        getView().getHotKeyFail(t.getMessage());
                    }
                });


      /*  WanService.getHotKey()
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<List<HotKeyBean>>() {
                    @Override
                    public void _onNext(List<HotKeyBean> hotKeyBeans) {
                        getView().getHotKeySuccess(hotKeyBeans);
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().getHotKeyFail(errorMessage);
                    }
                });*/
    }

    //搜索
    public void searchData(String key) {
        mCurrentPage = 0;

        RetrofitManage.getInstents().onCreate(Api.class)
                .searchArticle(mCurrentPage, key)
                .enqueue(new RequestCallBack<ResponseData<ArticleListVO>>() {
                    @Override
                    public void onSuccessful(Call<ResponseData<ArticleListVO>> call, Response<ResponseData<ArticleListVO>> response) {
                        getView().searchDataSuccess(response.body().getData().getDatas());
                    }

                    @Override
                    protected void onFail(Call<ResponseData<ArticleListVO>> call, Throwable t, Response<ResponseData<ArticleListVO>> response) {
                        getView().searchDataFail(t.getMessage());
                    }
                });

       /* WanService.searchArticle(mCurrentPage, key)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<ArticleListVO>() {
                    @Override
                    public void _onNext(ArticleListVO articleListVO) {
                        getView().searchDataSuccess(articleListVO.getDatas());
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().searchDataFail(errorMessage);
                    }
                });*/
    }

    //加载更多
    public void getMoreData(String key) {
        mCurrentPage += 1;
        RetrofitManage.getInstents().onCreate(Api.class)
                .searchArticle(mCurrentPage, key)
                .enqueue(new RequestCallBack<ResponseData<ArticleListVO>>() {
                    @Override
                    public void onSuccessful(Call<ResponseData<ArticleListVO>> call, Response<ResponseData<ArticleListVO>> response) {
                        getView().loadMoreDataSuccess(response.body().getData().getDatas());
                    }

                    @Override
                    protected void onFail(Call<ResponseData<ArticleListVO>> call, Throwable t, Response<ResponseData<ArticleListVO>> response) {
                        getView().loadMoreDataFail(t.getMessage());
                    }
                });

        /*WanService.searchArticle(mCurrentPage, key)
            .compose(RxSchedulersHelper.io_main())
            .compose(RxResultHelper.handleResult())
            .subscribe(new RxObserver<ArticleListVO>() {
                @Override
                public void _onNext(ArticleListVO articleListVO) {
                    getView().loadMoreDataSuccess(articleListVO.getDatas());
                }

                @Override
                public void _onError(String errorMessage) {
                    getView().loadMoreDataFail(errorMessage);
                }
            });*/
    }

}
