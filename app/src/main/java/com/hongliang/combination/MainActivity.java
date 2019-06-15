package com.hongliang.combination;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hongliang.baselibrary.bean.BaseBean;
import com.hongliang.baselibrary.httputil.callback.RequestCallBack;
import com.hongliang.retrofitutils.RetrofitManage;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onLogin();
    }


    private void onLogin() {

        Map<String, String> map = new HashMap();
        map.put("username", "13720232954");
        map.put("password", "123qwe");

        RetrofitManage.getInstents().onCreate(Api.class)
                .login(map)
                .enqueue(new RequestCallBack<BaseBean<LoginBean>>() {
                    @Override
                    public void onSuccessful(Call<BaseBean<LoginBean>> call, Response<BaseBean<LoginBean>> response) {
                        Toast.makeText(MainActivity.this, "登录成功：" + response.body().getData().getUsername(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void onFail(Call<BaseBean<LoginBean>> call, Throwable t, Response<BaseBean<LoginBean>> response) {
                        super.onFail(call, t, response);
                    }

                    @Override
                    protected void onAfter(Call<BaseBean<LoginBean>> call) {
                        super.onAfter(call);
                    }
                });
    }

}
