package com.upic.asn.ui.login;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.upic.asn.R;
import com.upic.asn.api.ApiUtil;
import com.upic.asn.api.RxSubscribe;
import com.upic.asn.model.LoginBean;
import com.upic.asn.model.view.LoginListener;
import com.upic.asn.ui.main.MainActivity;
import com.upic.asn.ui.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ZYF on 2016/11/16.
 */
public class LoginActivity extends BaseActivity implements LoginListener {

    EditText etAccount,etPwd;
    Button btnLogin;
    ProgressBar progressBar;
    Subscription mSubscription;

    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
//        initTitleBar("","登录","注册",0,this);
//        title_bar.setBackgroundColor(getResources().getColor(R.color.common_title_bg));
        etAccount = (EditText) $(R.id.account);
        etPwd = (EditText) $(R.id.password);
        btnLogin = (Button) $(R.id.login);
        progressBar = (ProgressBar) $(R.id.progressbar);
    }


    @Override
    public void initClick() {
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void loginSuccess(String message) {
        if(mSubscription != null){
            mSubscription.unsubscribe();
        }
        startActivity(MainActivity.class);
        dissmissProgressDialog();
        finish();
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFail(String message) {
        if(mSubscription != null){
            mSubscription.unsubscribe();
        }
        dissmissProgressDialog();
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_right:
                Toast.makeText(LoginActivity.this, "注册", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login:
//                doLogin();
                startActivity(MainActivity.class);
                finish();
                break;
        }
    }

    private void doLogin() {
        String account = etAccount.getText().toString();
        if (TextUtils.isEmpty(account)) {
            Toast.makeText(LoginActivity.this, "请输入您的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        String pwd = etPwd.getText().toString();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(LoginActivity.this, "请输入您的密码", Toast.LENGTH_SHORT).show();
            return;
        }
        login(account,pwd,this);
    }

    private void login(String account, String pwd, final LoginListener listener) {
        showProgressDialog();
        //开始一个请求
        Map<String,String> params = new HashMap<String,String>();
        params.put("uname",account);
        params.put("pass",pwd);
        mSubscription = ApiUtil.createApiService().login(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscribe<LoginBean>() {

                    @Override
                    protected void _onNext(LoginBean loginBean) {
                        Log.d("bbb","电话="+loginBean.getState());
                        if(loginBean.getState()==1){
                            listener.loginSuccess("");
                        } else if(loginBean.getState()==2){
                            listener.loginFail("密码错误");
                        } else if(loginBean.getState()==3){
                            listener.loginFail("账号不存在");
                        } else {
                            listener.loginFail("连接服务器失败！");
                        }
                    }
                    @Override
                    protected void _onError(String message) {
                        Log.d("ccc",message.toString());
                        listener.loginFail("登录失败!!!!"+message.toString());
                    }
                });
    }
    public void showProgressDialog() {
        progressBar.setVisibility(View.VISIBLE);
    }
    public void dissmissProgressDialog(){
        progressBar.setVisibility(View.GONE);
    }
}

