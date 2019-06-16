package com.hongliang.combination.ui.view;


import android.support.design.widget.TabLayout;

import com.hongliang.combination.model.pojo.ArticleBean;
import com.hongliang.combination.ui.adapter.ArticleListAdapter;
import com.hongliang.combination.widget.AutoLinefeedLayout;

import java.util.List;

/**
 * user：lqm
 * desc：分类View
 */

public interface TypeView {

    TabLayout getTabLayout();
    AutoLinefeedLayout getTagLayout();
    ArticleListAdapter getAdapter();

    void getDataError(String message);
    void getRefreshDataSuccess(List<ArticleBean> data);
    void getMoreDataSuccess(List<ArticleBean> data);

}
