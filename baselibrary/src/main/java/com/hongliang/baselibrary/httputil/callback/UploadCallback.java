package com.hongliang.baselibrary.httputil.callback;



import com.hongliang.baselibrary.bean.BaseBean;
import com.hongliang.retrofitutils.callback.BaseCallback;

import retrofit2.Call;
import retrofit2.Response;

public abstract class UploadCallback<T extends BaseBean> extends BaseCallback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        //这里可以对其他Code 吗码进行监听，做特殊处理
        if (200 == response.code()) {
            onAfter(call);
            if(null!=response&&null!=response.body()){
                onSuccessful(call, response);
            }else {
                onFail(call, new Throwable("youshu service error"), response);
            }
        } else {
            onFail(call, new Throwable(response.message()), response);
        }
    }
}
