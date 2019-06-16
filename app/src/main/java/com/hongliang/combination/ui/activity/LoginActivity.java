package com.hongliang.combination.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.hongliang.baselibrary.base.BaseActivity;
import com.hongliang.baselibrary.httputil.AppConst;
import com.hongliang.combination.R;
import com.hongliang.combination.model.pojo.UserBean;
import com.hongliang.combination.ui.presenter.LoginRegistPresenter;
import com.hongliang.combination.ui.view.LoginRegistView;
import com.hongliang.combination.util.PrefUtils;
import com.hongliang.combination.util.T;
import com.hongliang.combination.widget.IconFontTextView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * user：lqm
 * desc：登录注册界面
 */

public class LoginActivity extends BaseActivity<LoginRegistView,LoginRegistPresenter>
        implements LoginRegistView {

    @Bind(R.id.ic_close)
    IconFontTextView icClose;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.btn_regist)
    Button btnRegist;
    @Bind(R.id.btn_login)
    Button btnLogin;

    @Override
    protected LoginRegistPresenter createPresenter() {
        return new LoginRegistPresenter();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.ic_close, R.id.btn_regist, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ic_close:
                finish();
                break;
            case R.id.btn_regist:
                if (TextUtils.isEmpty(etName.getText().toString()) || TextUtils.isEmpty(etPassword.getText().toString())){
                    T.showShort(LoginActivity.this,"用户名和密码不能为空");
                }else if (etName.getText().toString().length() < 6 || etName.getText().toString().length() <6){
                    T.showShort(LoginActivity.this,"用户名和密码长度不能小于6位");
                }else{
                    mPresenter.toRegist(etName.getText().toString(),etPassword.getText().toString());
                }

                break;
            case R.id.btn_login:
                if (TextUtils.isEmpty(etName.getText().toString()) || TextUtils.isEmpty(etPassword.getText().toString())){
                    T.showShort(LoginActivity.this,"用户名和密码不能为空");
                }else if (etName.getText().toString().length() < 6 || etName.getText().toString().length() <6){
                    T.showShort(LoginActivity.this,"用户名和密码长度不能小于6位");
                }else{
                    mPresenter.toLogin(etName.getText().toString(),etPassword.getText().toString());
                }
                break;
        }
    }

    @Override
    public void showProgress(String tipString) {
        showWaitingDialog(tipString);
    }

    @Override
    public void hideProgress() {
        hideWaitingDialog();
    }

    @Override
    public void loginSuccess(UserBean user) {
        PrefUtils.setBoolean(LoginActivity.this,AppConst.IS_LOGIN_KEY,true);
        PrefUtils.setString(LoginActivity.this,AppConst.USERNAME_KEY,etName.getText().toString());
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }

    @Override
    public void registerSuccess(UserBean user) {
        T.showShort(LoginActivity.this,"注册成功");
        PrefUtils.setBoolean(LoginActivity.this, AppConst.IS_LOGIN_KEY,true);
        PrefUtils.setString(LoginActivity.this,AppConst.USERNAME_KEY,etName.getText().toString());
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }

    @Override
    public void loginFail() {
        T.showShort(LoginActivity.this,"登录失败");
        hideWaitingDialog();
    }

    @Override
    public void registerFail() {
        T.showShort(LoginActivity.this,"注册失败");
        hideWaitingDialog();
    }




}
