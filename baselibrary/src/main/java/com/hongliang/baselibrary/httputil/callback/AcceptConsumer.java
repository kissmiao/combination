package com.hongliang.baselibrary.httputil.callback;


import com.hongliang.baselibrary.bean.BaseBean;

import io.reactivex.functions.Consumer;

public abstract class AcceptConsumer<T> implements Consumer<BaseBean<T>> {

    @Override
    public void accept(BaseBean<T> tBaseBean) throws Exception {
        if (tBaseBean.getErrorCode()==0) {
            onSuccessful(tBaseBean);
        } else {
            onFail(tBaseBean);
        }
    }
    public abstract void onSuccessful(BaseBean<T> tBaseBean);

    public abstract void onFail(BaseBean<T> tBaseBean) ;




}
