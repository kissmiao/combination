package com.hongliang.combination.ui.presenter;


import com.hongliang.baselibrary.base.BasePresenter;
import com.hongliang.combination.model.pojoVO.ArticleListVO;
import com.hongliang.combination.ui.view.CollectView;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * user：lqm
 * desc：我的收藏
 */

public class CollectPresenter extends BasePresenter<CollectView> {

    private int mCurrentPage;

    //刷新获取数据
    public void getRefreshData() {
        mCurrentPage = 0;
       /* WanService.getCollectData(mCurrentPage)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ArticleListVO>() {
                    @Override
                    public void _onNext(ArticleListVO articleListVO) {
                        getView().onRefreshSuccess(articleListVO.getDatas());
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().onRefreshFail(errorMessage);
                    }

                    @Override
                    public void _onComplete() {
                        getView().loadComplete();
                    }
                });*/

    }

    //获取更多数据
    public void getMoreData() {
        mCurrentPage += 1;
       /* WanService.getCollectData(mCurrentPage)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<ArticleListVO>() {
                    @Override
                    public void _onNext(ArticleListVO articleListVO) {
                        getView().onLoadMoreSuccess(articleListVO.getDatas());
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().onLoadMoreFail(errorMessage);
                    }
                });*/
    }
}
