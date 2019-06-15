package com.hongliang.baselibrary.httputil.callback;



import com.hongliang.baselibrary.bean.BaseBean;
import com.hongliang.retrofitutils.callback.BaseCallback;

import retrofit2.Call;
import retrofit2.Response;

public abstract class RequestCallBack<T> extends BaseCallback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (200 == response.code()) {
            onAfter(call);
            int code = ((BaseBean) response.body()).getErrorCode();
            if (code != 0) {
                onFail(call, new Throwable(((BaseBean) response.body()).getErrorMsg()), response);
            } else {
                onSuccessful(call, response);
            }
        } else {
            onFail(call, new Throwable(response.message()), response);
        }
    }


}
