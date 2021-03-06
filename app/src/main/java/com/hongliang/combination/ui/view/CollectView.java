package com.hongliang.combination.ui.view;


import com.hongliang.combination.model.pojo.ArticleBean;

import java.util.List;

/**
 * user：lqm
 * desc：我的收藏
 */

public interface CollectView {

    void onRefreshSuccess(List<ArticleBean> data);

    void onRefreshFail(String errorString);

    void onLoadMoreSuccess(List<ArticleBean> data);

    void onLoadMoreFail(String errorString);

    void loadComplete();
}
